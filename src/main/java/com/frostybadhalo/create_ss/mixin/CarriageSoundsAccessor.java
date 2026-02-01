package com.frostybadhalo.create_ss.mixin;

import com.simibubi.create.content.trains.entity.CarriageContraptionEntity;
import com.simibubi.create.content.trains.entity.CarriageSounds;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CarriageSounds.class)
public interface CarriageSoundsAccessor {
    @Accessor(value = "entity", remap = false)
    CarriageContraptionEntity getEntity();
}

