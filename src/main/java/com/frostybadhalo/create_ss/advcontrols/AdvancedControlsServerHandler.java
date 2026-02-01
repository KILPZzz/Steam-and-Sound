package com.frostybadhalo.create_ss.advcontrols;

import com.simibubi.create.content.trains.entity.CarriageContraptionEntity;
import com.simibubi.create.content.trains.entity.Train;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

import java.util.*;

public class AdvancedControlsServerHandler {



}

/*
	// Estados por trem (UUID único)
	private static final Map<UUID, ControlState> states = new HashMap<>();

	private static ControlState getState(Train train) {
		return states.computeIfAbsent(train.id, t -> new ControlState());
	}



	private static final int W_KEY = 0; // acelerar
	private static final int S_KEY = 1; // frear
	private static final int R_KEY = 6; // reversor frente
	private static final int F_KEY = 7; // reversor ré
	private static final int SPACE_KEY = 4; // freio de emergência

	public static void applyAcceleration(CarriageContraptionEntity cce, Collection<Integer> keys, boolean press) {
		Train train = cce.getCarriage().train;
		if (train == null || train.id == null) {
			System.out.println("[ADV] Trem inválido ou sem ID, ignorando controle.");
			return; }

		UUID controlling = cce.getControllingPlayer().orElse(null);
		if (controlling == null || !controlling.equals(player.getUUID())) {
			return;
		}
		ControlState state = getState(train);

		// --- Freio de emergência ---
		if (keys.contains(SPACE_KEY)) {
			train.targetSpeed = 0;
			train.manualTick = true;
			cce.setDeltaMovement(Vec3.ZERO);
			System.out.println("[ADV] Freio de emergência acionado (train=" + train.id + ")");
			return;
		}

		// --- Reversor ---
		if (keys.contains(R_KEY) && press) {
			state.reversor = 1;
			state.throttle = 0.0;
			System.out.println("[ADV] Reversor frente (train=" + train.id + ")");
		}
		if (keys.contains(F_KEY) && press) {
			state.reversor = -1;
			state.throttle = 0.0;
			System.out.println("[ADV] Reversor ré (train=" + train.id + ")");
		}

		// --- Neutro ---
		if (state.reversor == 0) {
			train.targetSpeed = 0;
			train.manualTick = true;
			cce.setDeltaMovement(Vec3.ZERO);
			return;
		}

		// --- Throttle simples ---
		if (keys.contains(W_KEY)) {
			state.throttle = Math.min(1.0, state.throttle + 0.05);
		}
		if (keys.contains(S_KEY)) {
			state.throttle = Math.max(0.0, state.throttle - 0.05);
		}

		// --- Calcula velocidade alvo ---
		double topSpeed = train.maxSpeed();
		double targetSpeed = state.reversor * state.throttle * topSpeed;

		train.targetSpeed = Mth.clamp(targetSpeed, -topSpeed, topSpeed);
		train.manualTick = true;
		train.approachTargetSpeed(1);

		// Debug
		System.out.printf("[ADV] train=%s throttle=%.2f targetSpeed=%.2f speed=%.2f%n",
				train.id, state.throttle, train.targetSpeed, train.speed);
	}

	// Getters para HUD
	public static int getReversor(Train train) { return getState(train).reversor; }
	public static double getThrottle(Train train) { return getState(train).throttle; }

	// Classe interna para guardar estado
	private static class ControlState {
		int reversor = 0;
		double throttle = 0.0;
	}
}
*/