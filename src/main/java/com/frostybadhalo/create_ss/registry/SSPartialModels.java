package com.frostybadhalo.create_ss.registry;


import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.minecraft.resources.ResourceLocation;
import com.frostybadhalo.create_ss.SteamSoundMod;

public class SSPartialModels {

    public static final PartialModel ADV_CONTROLLER_COVER =
            PartialModel.of(new ResourceLocation(SteamSoundMod.MOD_ID, "block/advcontroller/train/cover"));

    public static final PartialModel ADV_CONTROLLER_LEVER =
            PartialModel.of(new ResourceLocation(SteamSoundMod.MOD_ID, "block/advcontroller/train/lever"));


    public static void init() {

    }
}
