package com.frostybadhalo.create_ss.advcontrols;

import com.frostybadhalo.create_ss.advcontrols.AdvancedControllerInputPacket;
import com.frostybadhalo.create_ss.advcontrols.AdvancedControlsStopPacket;
import com.frostybadhalo.create_ss.network.SSPackets;
import com.frostybadhalo.create_ss.registry.SteamSoundKeybind;
import com.simibubi.create.AllPackets;
import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import com.simibubi.create.content.contraptions.actors.trainControls.ControlsHandler;
import com.simibubi.create.content.contraptions.actors.trainControls.ControlsStopControllingPacket;
import com.simibubi.create.foundation.utility.ControlsUtil;
import com.simibubi.create.foundation.utility.CreateLang;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashSet;
import java.util.Vector;

import com.mojang.blaze3d.platform.InputConstants;

public class AdvancedControlsHandler {



    private static WeakReference<AbstractContraptionEntity> entityRef = new WeakReference<>(null);
    private static BlockPos controlsPos;
    private static int packetCooldown;


    public static void startControlling(AbstractContraptionEntity entity, BlockPos pos) {

        ControlsHandler.startControlling(entity, pos);

        //Minecraft.getInstance().player.displayClientMessage(
       //         CreateLang.translateDirect("contraption.controls.start_controlling", entity.getContraptionName()), true);
    }

    //public static void stopControlling() {

    //    System.out.println("Handler.stopControlling | entityRef=" + entityRef.get() + " controlsPos=" + controlsPos);


    //    entityRef = new WeakReference<>(null);
    //    controlsPos = null;
   //     currentlyPressed.clear();
    //    packetCooldown = 0;



     //   Minecraft.getInstance().player.displayClientMessage(
    //            CreateLang.translateDirect("contraption.controls.stop_controlling"), true);
    //}

    public static void tick() {
        AbstractContraptionEntity entity = entityRef.get();
        if (entity == null) return;

        if (packetCooldown > 0) packetCooldown--;

        // ESC ou SHIFT -> sair
        if (Minecraft.getInstance().player.isShiftKeyDown() || InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_ESCAPE)) {
            BlockPos pos = controlsPos;
            AllPackets.getChannel().sendToServer(new ControlsStopControllingPacket());
            ControlsHandler.stopControlling();
            return;
        }

        Vector<KeyMapping> controls = ControlsUtil.getControls();
        Collection<Integer> pressedKeys = new HashSet<>();
        for (int i = 0; i < controls.size(); i++) {
            if (ControlsUtil.isActuallyPressed(controls.get(i)))
                pressedKeys.add(i);
        }

        if (SteamSoundKeybind.KEY_ACCELERATE.isDown()) { pressedKeys.add(6); }
        if (SteamSoundKeybind.KEY_DECELERATE.isDown()) { pressedKeys.add(7); }

        if (!pressedKeys.isEmpty() && packetCooldown == 0) {
            SSPackets.getChannel().sendToServer(
                    new AdvancedControllerInputPacket(pressedKeys, true, entity.getId(), controlsPos, false)
            );
            packetCooldown = ControlsHandler.PACKET_RATE;
        }

        ControlsHandler.currentlyPressed = pressedKeys;
        controls.forEach(kb -> kb.setDown(false));
    }

    @Nullable
    public static AbstractContraptionEntity getContraption() {
        return entityRef.get();
    }

    @Nullable
    public static BlockPos getControlsPos() {
        return controlsPos;
    }

}
