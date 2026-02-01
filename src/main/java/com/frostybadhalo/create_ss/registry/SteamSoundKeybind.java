package com.frostybadhalo.create_ss.registry;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SteamSoundKeybind {

    public static KeyMapping KEY_ACCELERATE;
    public static KeyMapping KEY_DECELERATE;

    @SubscribeEvent
    public static void onRegisterKeys(RegisterKeyMappingsEvent event) {

        KEY_ACCELERATE = new KeyMapping(
                "key.create_ss.accelerate", InputConstants.KEY_R, "key.categories.create"
        );

        KEY_DECELERATE = new KeyMapping(
                "key.create_ss.decelerate", InputConstants.KEY_F, "key.categories.create"
        );

        event.register(KEY_ACCELERATE);
        event.register(KEY_DECELERATE);

    }
}
