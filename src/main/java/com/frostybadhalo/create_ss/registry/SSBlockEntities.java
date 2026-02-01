package com.frostybadhalo.create_ss.registry;


import com.frostybadhalo.create_ss.entity.AdvancedControllerBlockEntity;
import com.frostybadhalo.create_ss.entity.SCMBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SSBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, "create_ss");

    public static final RegistryObject<BlockEntityType<SCMBlockEntity>> SCMBlockEntity  =
        BLOCK_ENTITIES.register("scmb",
                () -> BlockEntityType.Builder.of(
                        SCMBlockEntity::new,
                        SSBlocks.SMOKE_CONTROL_MODULE_BLOCK.get())
                        .build(null));


    public static final RegistryObject<BlockEntityType<AdvancedControllerBlockEntity>> ADVANCED_CONTROLLER =
            BLOCK_ENTITIES.register("advanced_controller",
                () -> BlockEntityType.Builder.of(
                        AdvancedControllerBlockEntity::new,
                        SSBlocks.ADVANCED_CONTROLLER_BLOCK.get())
                        .build(null));


}
