package com.frostybadhalo.create_ss.mixin;

import com.frostybadhalo.create_ss.blocks.AdvancedControlsBlock;
import com.llamalad7.mixinextras.lib.apache.commons.tuple.Pair;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.trains.entity.CarriageContraption;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate.StructureBlockInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CarriageContraption.class)
public abstract class CarriageContraptionMixin {

    @Shadow private boolean forwardControls;
    @Shadow private boolean backwardControls;
    @Shadow private boolean sidewaysControls;
    @Shadow private Direction assemblyDirection;

    @Inject(method = "capture", at = @At("TAIL"), remap = false)
    private void injectCapture(Level world, BlockPos pos, CallbackInfoReturnable<Pair<StructureBlockInfo, BlockEntity>> cir) {
        BlockState state = world.getBlockState(pos);

        if (state.getBlock() instanceof AdvancedControlsBlock) {

            Direction facing = state.getValue(AdvancedControlsBlock.FACING);
            if (facing.getAxis() != assemblyDirection.getAxis()) {
                sidewaysControls = true;
            } else {
                boolean forwards = facing == assemblyDirection;
                if (forwards)
                    forwardControls = true;
                else
                    backwardControls = true;
            }


        }
    }

}

