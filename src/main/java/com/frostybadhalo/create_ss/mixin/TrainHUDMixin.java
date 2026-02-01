package com.frostybadhalo.create_ss.mixin;

import com.simibubi.create.content.contraptions.actors.trainControls.ControlsHandler;
import com.simibubi.create.content.trains.TrainHUD;
import com.simibubi.create.content.trains.entity.CarriageContraptionEntity;
import com.simibubi.create.content.trains.entity.Train;
import com.simibubi.create.foundation.gui.AllGuiTextures;
import net.createmod.catnip.animation.LerpedFloat;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*
@Mixin(TrainHUD.class)
public abstract class TrainHUDMixin {

    // Shadow para acessar o LerpedFloat interno
    @Shadow(remap = false) private static LerpedFloat displayedThrottle;

    // Intercepta a chamada ao blit que desenha a barra de throttle
    @Inject(method = "renderOverlay", at = @At("HEAD") , remap = false)
    private void redirectThrottleBar(GuiGraphics graphics, ResourceLocation location,
                                     int x, int y, int z, int u, int v,
                                     int width, int height, int texWidth, int texHeight) {
        double throttleVal = displayedThrottle.getValue(0);

        // Se for a textura da barra de throttle e estiver em ré, não desenha
        if (throttleVal < 0 && location.equals(AllGuiTextures.TRAIN_HUD_THROTTLE.location)) {
            return;
        }

        // Caso contrário, desenha normalmente
        graphics.blit(location, x, y, z, u, v, width, height, texWidth, texHeight);
    }

}
*/




