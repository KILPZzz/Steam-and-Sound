package com.frostybadhalo.create_ss.old_mixin;


import com.frostybadhalo.create_ss.mixin.CarriageSoundsAccessor;
import com.frostybadhalo.create_ss.registry.SSBlocks;
import com.simibubi.create.content.trains.entity.CarriageContraptionEntity;
import com.simibubi.create.content.trains.entity.CarriageSounds;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

/*
@Mixin(CarriageSounds.class)
public class MixinCarriageSounds {

    @ModifyArg(method = "tick", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/AllSoundEvents$SoundEntry;playAt(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/phys/Vec3;FFZ)V"), index = 2, remap = false)
    private float muteSteamVolume(float volume) {
        CarriageContraptionEntity entity = ((CarriageSoundsAccessor) (Object) this).getEntity();
        if (entity.getContraption() != null) {
            for (StructureTemplate.StructureBlockInfo info : entity.getContraption().getBlocks().values()) {
                if (info.state().is(SSBlocks.SMOKE_CONTROL_MODULE_BLOCK.get())) {
                    return 0.0F;
                }
            }
        }
        return volume;
    }

}

*/