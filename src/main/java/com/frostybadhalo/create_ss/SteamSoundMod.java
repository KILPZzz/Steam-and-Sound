package com.frostybadhalo.create_ss;

import com.frostybadhalo.create_ss.advcontrols.AdvancedControlsHandler;
//import com.frostybadhalo.create_ss.advcontrols.AdvancedControlsServerHandler;
import com.frostybadhalo.create_ss.advcontrols.AdvancedControlsServerHandler;
import com.frostybadhalo.create_ss.blocks.SCMSoundBehaviour;
import com.frostybadhalo.create_ss.network.SSPackets;
import com.frostybadhalo.create_ss.registry.SSBlockEntities;
import com.frostybadhalo.create_ss.visuals.SSModSounds;
import com.simibubi.create.CreateClient;
import com.simibubi.create.api.behaviour.movement.MovementBehaviour;
import com.frostybadhalo.create_ss.registry.*;
import com.simibubi.create.content.contraptions.actors.trainControls.ControlsHandler;
import net.createmod.catnip.animation.AnimationTickHolder;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.resources.ResourceLocation;
import com.simibubi.create.foundation.data.CreateRegistrate;

@Mod(SteamSoundMod.MOD_ID)
public class SteamSoundMod {
    public static final String MOD_ID = "create_ss";
    public static final String MOD_NAME = "Create: Steam & Sound";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static CreateRegistrate REGISTRATE;

    public SteamSoundMod() {
        REGISTRATE = CreateRegistrate.create(MOD_ID);
        onCtor();


        FMLJavaModLoadingContext.get().getModEventBus().addListener(SteamSoundMod::init);
    }

    public static void onCtor() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        REGISTRATE.registerEventListeners(modEventBus);

        SSBlocks.TABS.register(modEventBus);
        SSBlocks.register();
        SSModSounds.SOUND_EVENTS.register(modEventBus);
        SSBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        SSPartialModels.init();
        //SSPackets.registerPackets();
        modEventBus.addListener(SteamSoundMod::commonSetup);
    }

    public static void init(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

           // MovementBehaviour.REGISTRY.register(
            //        SSBlocks.SMOKE_CONTROL_MODULE_BLOCK.get(),
            //        new SCMSoundBehaviour()
            //);

            //MovementBehaviour.REGISTRY.register(
                //    SSBlocks.ADVANCED_CONTROLLER_BLOCK.get(),
                  //  new com.simibubi.create.content.contraptions.actors.trainControls.ControlsMovementBehaviour()
           // );


            //SSNetwork.registerPackets();
        });
    }

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(SSPackets::registerPackets);
    }

    @SubscribeEvent
    public static void onUnloadWorld(LevelEvent.Unload event) {
        if (!event.getLevel()
                .isClientSide())
            return;
       // AdvancedControlsHandler.levelUnloaded(event.getLevel());
    }

    @SubscribeEvent
    public static void onServerWorldTick(TickEvent.LevelTickEvent event) {
       //AdvancedControlsServerHandler.tick(event.level);
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static CreateRegistrate registrate() {
        return REGISTRATE;
    }
}
