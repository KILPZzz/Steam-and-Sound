package com.frostybadhalo.create_ss.mixin;

import com.frostybadhalo.create_ss.advcontrols.AdvancedControlsHandler;

import com.frostybadhalo.create_ss.advcontrols.AdvancedControlsStopPacket;
import com.frostybadhalo.create_ss.network.SSPackets;
import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import com.simibubi.create.content.contraptions.actors.trainControls.ControlsHandler;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


/*
@Mixin(AbstractContraptionEntity.class)
public abstract class AbstractContraptionEntityMixin {

    @Inject(method = "stopControlling", at = @At("HEAD"), remap = false)
    private void injectStopControlling(BlockPos controlsLocalPos, CallbackInfo ci) {
        AbstractContraptionEntity self = (AbstractContraptionEntity)(Object)this;

        System.out.println("Mixin stopControlling chamado | entityId=" + self.getId() + " pos=" + controlsLocalPos);
        SSPackets.getChannel().sendToServer(new AdvancedControlsStopPacket(self.getId(), controlsLocalPos));
        ControlsHandler.stopControlling();

    }

}*/


