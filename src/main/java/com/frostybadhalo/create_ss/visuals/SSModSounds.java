package com.frostybadhalo.create_ss.visuals;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SSModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
        DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "create_ss");

    public static final RegistryObject<SoundEvent> CHUG = SOUND_EVENTS.register("chug",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("create_ss", "chug"))
    );
}
