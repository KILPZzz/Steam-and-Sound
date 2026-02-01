package com.frostybadhalo.create_ss.render;

import java.util.Collection;

import com.frostybadhalo.create_ss.advcontrols.AdvancedControlsHandler;
import com.frostybadhalo.create_ss.blocks.AdvancedControlsBlock;
import com.simibubi.create.api.behaviour.movement.MovementBehaviour;
import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import com.simibubi.create.content.contraptions.actors.trainControls.ControlsHandler;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import com.simibubi.create.content.contraptions.render.ContraptionMatrices;
import com.simibubi.create.content.trains.entity.CarriageContraptionEntity;
import com.simibubi.create.foundation.virtualWorld.VirtualRenderWorld;

import net.createmod.catnip.animation.AnimationTickHolder;
import net.createmod.catnip.animation.LerpedFloat;
import net.createmod.catnip.animation.LerpedFloat.Chaser;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate.StructureBlockInfo;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

// VAI RODAR QUERENDO OU NAO!
public class AdvancedControlsMovementBehaviour implements MovementBehaviour {

	static class LeverAngles {
		LerpedFloat steering = LerpedFloat.linear();
		LerpedFloat speed = LerpedFloat.linear();
		LerpedFloat equipAnimation = LerpedFloat.linear();
	}

	@Override
	public ItemStack canBeDisabledVia(MovementContext context) {
		return null;
	}

	@Override
	public void stopMoving(MovementContext context) {
		context.contraption.entity.stopControlling(context.localPos);
		MovementBehaviour.super.stopMoving(context);
	}

	@Override
	public void tick(MovementContext context) {
		if (!(context.temporaryData instanceof LeverAngles))
			context.temporaryData = new LeverAngles();

		LeverAngles a = (LeverAngles) context.temporaryData;
		a.steering.tickChaser();
		a.speed.tickChaser();
		a.equipAnimation.tickChaser();
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void renderInContraption(MovementContext context, VirtualRenderWorld renderWorld,
									ContraptionMatrices matrices, MultiBufferSource buffer) {
		if (!(context.temporaryData instanceof AdvancedControlsMovementBehaviour.LeverAngles angles))
			return;

		AbstractContraptionEntity entity = context.contraption.entity;
		if (!(entity instanceof CarriageContraptionEntity cce))
			return;

		StructureBlockInfo info = context.contraption.getBlocks()
				.get(context.localPos);
		Direction initialOrientation = cce.getInitialOrientation()
				.getCounterClockWise();
		boolean inverted = false;
		if (info != null && info.state().hasProperty(AdvancedControlsBlock.FACING))
			inverted = !info.state().getValue(AdvancedControlsBlock.FACING)
					.equals(initialOrientation);

		if (AdvancedControlsHandler.getContraption() == entity && AdvancedControlsHandler.getControlsPos() != null
				&& AdvancedControlsHandler.getControlsPos().equals(context.localPos)) {
			Collection<Integer> pressed = ControlsHandler.currentlyPressed;
			angles.equipAnimation.chase(1, .2f, Chaser.EXP);
			angles.steering.chase((pressed.contains(3) ? 1 : 0) + (pressed.contains(2) ? -1 : 0), 0.2f, Chaser.EXP);
			float f = cce.movingBackwards ^ inverted ? -1 : 1;
			angles.speed.chase(Math.min(context.motion.length(), 0.5f) * f, 0.2f, Chaser.EXP);

		} else {
			angles.equipAnimation.chase(0, .2f, Chaser.EXP);
			angles.steering.chase(0, 0, Chaser.EXP);
			angles.speed.chase(0, 0, Chaser.EXP);
		}

		float pt = AnimationTickHolder.getPartialTicks(context.world);
		AdvancedControlsRenderer.render(context, renderWorld, matrices, buffer, angles.equipAnimation.getValue(pt),
				angles.speed.getValue(pt), angles.steering.getValue(pt));
	}

}
