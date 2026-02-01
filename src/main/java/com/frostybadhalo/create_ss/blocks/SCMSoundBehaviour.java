// blocks/SCMSoundBehaviour.java
package com.frostybadhalo.create_ss.blocks;

import com.simibubi.create.api.behaviour.movement.MovementBehaviour;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import com.frostybadhalo.create_ss.visuals.SSModSounds;
import com.weido.create_bb.blocks.BBBogieBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;

public class SCMSoundBehaviour implements MovementBehaviour {

    // Customization: min/max pitch and min/max interval (in ticks)
    public static final float MIN_PITCH = 0.95f;
    public static final float MAX_PITCH = 1.25f;
    // 1.5 seconds (30 ticks) at slowest, 0.5 seconds (10 ticks) at fastest
    public static final double MIN_INTERVAL = 2.5; // fastest: 0.5s (10 ticks)
    public static final double MAX_INTERVAL = 38.5; // slowest: 1.5s (30 ticks)
    public static final double MIN_SPEED = 100.0;
    public static final double MAX_SPEED = 1100.0;

    // Dados temporários para cada contexto
    private static class SoundData {
        long lastSoundTime = 0;
        double distanceAccumulator = 0;
        float pitch = 1.0f;

        void reset() {
            distanceAccumulator = 0;
        }
    }

    @Override
    public void tick(MovementContext context) {
        if (context.world == null || context.position == null)
            return;

        // Only play sound if block is moving
        boolean isMoving = context.motion != null && context.motion.lengthSqr() > 0.001;
        if (!isMoving)
            return;

        Level level = context.world;
        BlockPos pos = BlockPos.containing(context.position);
        SoundData data = context.temporaryData instanceof SoundData ? (SoundData) context.temporaryData : null;
        long now = level.getGameTime();
        if (data == null) {
            data = new SoundData();
            context.temporaryData = data;
        }

        // Use Create's animation speed logic
        float animationSpeed = Math.abs(context.getAnimationSpeed());
        // Clamp animationSpeed to [MIN_SPEED, MAX_SPEED]
        float clampedSpeed = Math.max((float)MIN_SPEED, Math.min((float)MAX_SPEED, animationSpeed));
        // Map speed to interval: higher speed = lower interval
        float t = (clampedSpeed - (float)MIN_SPEED) / ((float)MAX_SPEED - (float)MIN_SPEED); // 0 (slow) to 1 (fast)
        double interval = MAX_INTERVAL - t * (MAX_INTERVAL - MIN_INTERVAL);
        float pitch = MIN_PITCH + t * (MAX_PITCH - MIN_PITCH);

        // Debug output for tuning
        if (level.isClientSide && now % 20 == 0) {
            System.out.printf("[SCMSoundBehaviour] animationSpeed=%.2f, t=%.2f, interval=%.2f, pitch=%.2f\n", animationSpeed, t, interval, pitch);
        }

        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof BBBogieBlockEntity bogey) {
            float angle = bogey.getVirtualAngle(0);
            if (Math.abs(angle - 90f) < 0.5f) {
                System.out.println("Roda está a ~90°");
            }
        }


        if ( now - data.lastSoundTime >= interval ) {
            data.lastSoundTime = now;
            if (!level.isClientSide) {
                level.playSound(null, pos, SSModSounds.CHUG.get(), SoundSource.BLOCKS, 1.0F, pitch);
            }
        }
    }

    @Override
    public void startMoving(MovementContext context) {
        // Inicializar quando começa a mover
        context.temporaryData = new SoundData();
        System.out.println("SCMSoundBehaviour: Iniciando movimento");
    }

    @Override
    public void onSpeedChanged(MovementContext context, net.minecraft.world.phys.Vec3 oldMotion, Vec3 motion) {
        // Ajustar quando a velocidade muda
        boolean wasStopped = Mth.equal(oldMotion.lengthSqr(), 0);
        boolean isStopped = Mth.equal(motion.lengthSqr(), 0);

        if (wasStopped && !isStopped) {
            // Começou a mover
            startMoving(context);
        }
    }
}

