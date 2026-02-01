package com.frostybadhalo.create_ss.advcontrols;

import com.frostybadhalo.create_ss.advcontrols.AdvancedControlsHandler;
import com.simibubi.create.content.contraptions.actors.trainControls.ControlsHandler;
import com.simibubi.create.foundation.networking.SimplePacketBase;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent.Context;

public class AdvancedControlsStopPacket extends SimplePacketBase {

    private final int entityId;
    private final BlockPos controllerPos;

    public AdvancedControlsStopPacket(int entityId, BlockPos controllerPos) {
        this.entityId = entityId;
        this.controllerPos = controllerPos;
    }

    public AdvancedControlsStopPacket(FriendlyByteBuf buf) {
        this.entityId = buf.readInt();
        this.controllerPos = buf.readBlockPos();
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeInt(entityId);
        buf.writeBlockPos(controllerPos);
    }

    @Override
    public boolean handle( Context context ) {

        System.out.println("STOP PACKET | recebido no servidor");
        context.enqueueWork(() -> {

            var player = context.getSender();
            System.out.println("STOP PACKET | context sender=" + player);

            if (player != null) {
                System.out.println("STOP PACKET | desmontando player=" + player.getName().getString());
                player.stopRiding();
            }
            ControlsHandler.stopControlling();
        });
        return true;
    }


}
