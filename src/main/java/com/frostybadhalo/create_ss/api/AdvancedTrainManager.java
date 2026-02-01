package com.frostybadhalo.create_ss.api;

import com.simibubi.create.content.trains.entity.Train;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AdvancedTrainManager {
    private static final Map<UUID, AdvancedTrainData> dataMap = new HashMap<>();

    public static AdvancedTrainData getData(Train train) {
        return dataMap.computeIfAbsent(train.id, id -> new AdvancedTrainData());
    }
}
