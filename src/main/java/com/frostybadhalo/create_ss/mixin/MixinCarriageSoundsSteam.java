package com.frostybadhalo.create_ss.mixin;

import com.frostybadhalo.create_ss.registry.SSBlocks;
import com.simibubi.create.AllSoundEvents;
import com.simibubi.create.content.trains.entity.CarriageSounds;
import com.simibubi.create.content.trains.entity.CarriageContraptionEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CarriageSounds.class)
public class MixinCarriageSoundsSteam {

    @Redirect(
        method = "tick",
        at = @At(
            value = "INVOKE",
            target = "Lcom/simibubi/create/AllSoundEvents$SoundEntry;playAt(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/phys/Vec3;FFZ)V"
        ),
        remap = false
    )
    private void redirectSteamPlay(AllSoundEvents.SoundEntry instance, Level level, Vec3 pos, float volume, float pitch, boolean distant) {
        CarriageContraptionEntity entity = ((CarriageSoundsAccessor) (Object) this).getEntity();
        if (entity.getContraption() != null) {
            for (StructureTemplate.StructureBlockInfo info : entity.getContraption().getBlocks().values()) {
                BlockState state = info.state();
                if (state.is(SSBlocks.SMOKE_CONTROL_MODULE_BLOCK.get()) && instance == AllSoundEvents.STEAM) {
                    return;
                }
            }
        }
        instance.playAt(level, pos, volume, pitch, distant);
    }
}
