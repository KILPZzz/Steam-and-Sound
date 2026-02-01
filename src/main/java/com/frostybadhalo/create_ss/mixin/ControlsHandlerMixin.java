package com.frostybadhalo.create_ss.mixin;

import com.frostybadhalo.create_ss.advcontrols.AdvancedControlsHandler;
import com.frostybadhalo.create_ss.api.AdvancedControlsState;
import com.simibubi.create.content.contraptions.actors.trainControls.ControlsHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/*
@Mixin(ControlsHandler.class)
public class ControlsHandlerMixin {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true , remap = false)
    private static void redirectControls(CallbackInfo ci) {
        if (AdvancedControlsState.ENABLED) {
            System.out.println(" ControlHandlerMixin | Ativado ");
            AdvancedControlsHandler.tick();
            ci.cancel(); // bloqueia o Create
        }
    }
}
*/