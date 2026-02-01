package com.frostybadhalo.create_ss.registry;

import com.frostybadhalo.create_ss.SteamSoundMod;

import com.frostybadhalo.create_ss.blocks.AdvancedControlsBlock;
import com.frostybadhalo.create_ss.advcontrols.AdvancedControlsInteractionBehaviour;
import com.frostybadhalo.create_ss.render.AdvancedControlsMovementBehaviour;
import com.frostybadhalo.create_ss.blocks.SCMBlock;

import com.simibubi.create.api.behaviour.interaction.MovingInteractionBehaviour;
import com.simibubi.create.api.behaviour.movement.MovementBehaviour;

import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class SSBlocks {
    public static final CreateRegistrate REGISTRATE = SteamSoundMod.registrate();

    public static final ResourceKey<CreativeModeTab> CREATE_SS_TAB_KEY =
            ResourceKey.create(Registries.CREATIVE_MODE_TAB,
                    new ResourceLocation(SteamSoundMod.MOD_ID, "create_ss_tab"));

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SteamSoundMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> CREATE_SS_TAB = TABS.register("create_ss_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.create_ss.create_ss_tab"))
                    .icon(() -> new ItemStack(SSBlocks.SMOKE_CONTROL_MODULE_BLOCK.get()))
                    .build());

    // Aqui ficam só os blocos novos
    public static final BlockEntry<SCMBlock> SMOKE_CONTROL_MODULE_BLOCK =
            REGISTRATE.block("scmb", SCMBlock::new)
                    .properties(p -> p.strength(1.0f).noOcclusion())
                    .item() // cria o item do bloco
                    .tab(CREATE_SS_TAB_KEY)// define a aba criativa
                    .build()
                    .register();



    public static final BlockEntry<AdvancedControlsBlock> ADVANCED_CONTROLLER_BLOCK =
            REGISTRATE.block("advanced_controller", AdvancedControlsBlock::new)
                    .properties(p -> p.strength(3.0f).noOcclusion())
                    .onRegister(MovingInteractionBehaviour.interactionBehaviour(new AdvancedControlsInteractionBehaviour()))
                    .onRegister(MovementBehaviour.movementBehaviour(new AdvancedControlsMovementBehaviour()))

                    .blockstate((c, p) -> p.horizontalBlock(c.get(),
                            s -> AssetLookup.partialBaseModel(c, p,
                                    s.getValue(AdvancedControlsBlock.VIRTUAL) ? "virtual" :
                                            s.getValue(AdvancedControlsBlock.OPEN) ? "open" : "closed")))
                    .item()
                    .tab(CREATE_SS_TAB_KEY)
                    .build()
                    .register();



    public static void register() {
        SteamSoundMod.LOGGER.info("Registered custom blocks for " + SteamSoundMod.MOD_NAME);
    }
}
