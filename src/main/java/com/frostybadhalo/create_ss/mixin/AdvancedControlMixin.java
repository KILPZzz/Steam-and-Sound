package com.frostybadhalo.create_ss.mixin;


import com.frostybadhalo.create_ss.blocks.AdvancedControlsBlock;
import com.frostybadhalo.create_ss.gui.AdvancedHUDPacket;
import com.frostybadhalo.create_ss.network.SSPackets;
import com.simibubi.create.AllPackets;
import com.simibubi.create.content.trains.TrainHUDUpdatePacket;
import com.simibubi.create.content.trains.entity.Carriage;
import com.simibubi.create.content.trains.entity.CarriageContraptionEntity;
import com.simibubi.create.content.trains.entity.Train;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.UUID;
/*
@Mixin(CarriageContraptionEntity.class)
public abstract class AdvancedControlMixin {


    double navDistanceTotal = 0;
    int hudPacketCooldown = 0;

    @Inject(method = "control", at = @At("HEAD"), cancellable = true, remap = false)
    private void injectControl(BlockPos controlsLocalPos, Collection<Integer> heldControls, Player player, CallbackInfoReturnable<Boolean> cir) {

        //System.out.println("TENTANDO MIXIONG DE OVERLAY");

        CarriageContraptionEntity self = (CarriageContraptionEntity)(Object)this;

        UUID controlling = self.getControllingPlayer().orElse(null);
        if (controlling == null || !controlling.equals(player.getUUID())) {
            cir.setReturnValue(false);
            cir.cancel();
            return;
        }



        // pega o bloco de controle
        BlockState state = self.getContraption().getBlocks().get(controlsLocalPos).state();

        // só intercepta se for o bloco avançado
        if (state.getBlock() instanceof AdvancedControlsBlock) {

            if (hudPacketCooldown-- <= 0 && player instanceof ServerPlayer sp) {
                //System.out.println("TENTANDO MIXIONG DE OVERLA2323Y");
                SSPackets.getChannel().send(PacketDistributor.PLAYER.with(() -> sp), new AdvancedHUDPacket(self.getCarriage().train));
                hudPacketCooldown = 5;
            }

            Train train = self.getCarriage().train;
            if (train == null) return;

            // espaço = freio
            if (heldControls.contains(4)) {
                train.targetSpeed = 0;
                train.manualTick = true;
                self.setDeltaMovement(Vec3.ZERO);
                cir.setReturnValue(true);
                cir.cancel();
                return;
            }

            // W/S = acelera/freia
            double topSpeed = train.maxSpeed();
            double throttle = 0.0;
            if (heldControls.contains(0)) throttle = 1.0;
            if (heldControls.contains(1)) throttle = -1.0;

            train.targetSpeed = Mth.clamp(throttle * topSpeed, -topSpeed, topSpeed);
            train.manualTick = true;
            train.approachTargetSpeed(1);

            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}



 */



