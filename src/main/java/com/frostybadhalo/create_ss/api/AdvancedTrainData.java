package com.frostybadhalo.create_ss.api;

public class AdvancedTrainData {
    public int direction = 0;       // -1 = ré, 0 = neutro, 1 = frente
    public double throttle = 0.0;   // 0–1
    public double speedLimit = Double.MAX_VALUE;

    public long lastGearChange = 0; // timestamp da última troca
}
