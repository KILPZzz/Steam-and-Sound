package com.frostybadhalo.create_ss.mixin;

import com.frostybadhalo.create_ss.blocks.AdvancedControlsBlock;
import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.content.contraptions.actors.trainControls.ControlsBlock;
import com.simibubi.create.AllTags.AllContraptionTypeTags;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Contraption.class)
public abstract class AdvancedControlsContraptionMixin {

    /**
      Intercepta o method capture e força OPEN = true
      para AdvancedControlsBlock quando a contraption
      tiver a tag OPENS_CONTROLS.

     */

    @ModifyVariable( method = "capture", at = @At("STORE"), ordinal = 0, remap = false )
    private BlockState AdvancedControllerStyle(BlockState blockstate) {
        Contraption self = (Contraption)(Object)this;

        if (blockstate.getBlock() instanceof AdvancedControlsBlock
            && AllContraptionTypeTags.OPENS_CONTROLS.matches(self.getType())) {
            return blockstate.setValue(AdvancedControlsBlock.OPEN, true);
        }

        return blockstate;
    }
}
