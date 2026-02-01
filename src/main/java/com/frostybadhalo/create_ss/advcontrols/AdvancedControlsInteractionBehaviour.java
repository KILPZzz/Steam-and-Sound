package com.frostybadhalo.create_ss.advcontrols;

import com.google.common.base.Objects;
import com.simibubi.create.AllItems;
import com.simibubi.create.api.behaviour.interaction.MovingInteractionBehaviour;
import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

import java.util.UUID;

public class AdvancedControlsInteractionBehaviour extends MovingInteractionBehaviour {

    @Override
    public boolean handlePlayerInteraction(Player player, InteractionHand hand, BlockPos localPos, AbstractContraptionEntity entity) {

        System.out.println("\n Interagiu com o controlador  ");

        if (AllItems.WRENCH.isIn(player.getItemInHand(hand))) return false;

        UUID current = entity.getControllingPlayer().orElse(null);

        if (current != null) { System.out.println(" Status: Player -> " + current + "\n" ); } else { System.out.println(" Status: Ninguem Controlando \n" ); }

        if (current != null) {
            entity.stopControlling(localPos);

            if (Objects.equal(current, player.getUUID()))
                return true;
        }

        if (!entity.startControlling(localPos, player))
            return false;

        entity.setControllingPlayer(player.getUUID());

        if (player.level().isClientSide) {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                    () -> () -> AdvancedControlsHandler.startControlling(entity, localPos));
        }

        return true;
    }
}
