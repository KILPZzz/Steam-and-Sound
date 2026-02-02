package com.frostybadhalo.create_ss;

import com.frostybadhalo.create_ss.advcontrols.AdvancedControlsHandler;
import com.frostybadhalo.create_ss.gui.ADVTrainHUD;
import com.frostybadhalo.create_ss.registry.SSBlockEntities;
import com.frostybadhalo.create_ss.render.AdvancedControllerBlockEntityRenderer;
import com.simibubi.create.CreateClient;
import com.simibubi.create.content.contraptions.actors.trainControls.ControlsHandler;
import com.simibubi.create.content.contraptions.elevator.ElevatorControlsHandler;
import com.simibubi.create.content.equipment.armor.RemainingAirOverlay;
import com.simibubi.create.content.kinetics.fan.AirCurrent;
import com.simibubi.create.content.redstone.link.controller.LinkedControllerClientHandler;
import com.simibubi.create.content.trains.TrainHUD;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static net.createmod.ponder.PonderClient.isGameActive;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEvents {


    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            ADVTrainHUD.tick();
            AdvancedControlsHandler.tick();
        }
    }

    @SubscribeEvent public static void onClientSetup(FMLClientSetupEvent event) {
        BlockEntityRenderers.register( SSBlockEntities.ADVANCED_CONTROLLER.get(),
                AdvancedControllerBlockEntityRenderer::new
        );
    }

    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAbove(VanillaGuiOverlay.EXPERIENCE_BAR.id(), "adv_train_hud", ADVTrainHUD.OVERLAY);
    }


    public static void onTick(TickEvent.ClientTickEvent event) {
        if (!isGameActive())
            return;

        Level world = Minecraft.getInstance().level;
        if (event.phase == TickEvent.Phase.START) {

           // AdvancedControlsHandler.tick();

            return;
        }
    }



    //input event

    @SubscribeEvent
    public static void onMouseScrolled(InputEvent.MouseScrollingEvent event) {
        if (Minecraft.getInstance().screen != null)
            return;

        double delta = event.getScrollDelta();
        boolean cancelled = ADVTrainHUD.onScroll(delta);

        event.setCanceled(cancelled);
    }
}
