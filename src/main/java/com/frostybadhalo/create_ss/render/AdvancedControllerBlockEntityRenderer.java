package com.frostybadhalo.create_ss.render;

import com.frostybadhalo.create_ss.blocks.AdvancedControlsBlock;
import com.frostybadhalo.create_ss.entity.AdvancedControllerBlockEntity;
import com.frostybadhalo.create_ss.registry.SSPartialModels;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.createmod.catnip.math.AngleHelper;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;

public class AdvancedControllerBlockEntityRenderer implements BlockEntityRenderer<AdvancedControllerBlockEntity> {

    public AdvancedControllerBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(AdvancedControllerBlockEntity be, float partialTicks, PoseStack ms,
                       MultiBufferSource buffer, int light, int overlay) {

        BlockState state = be.getBlockState();
        Direction facing = state.getValue(AdvancedControlsBlock.FACING);

        // Renderiza o cover customizado
        SuperByteBuffer cover = CachedBuffers.partial(SSPartialModels.ADV_CONTROLLER_COVER, state);
        cover.transform(ms)
                .center()
                .rotateYDegrees(AngleHelper.horizontalAngle(facing))
                .uncenter()
                .renderInto(ms, buffer.getBuffer(RenderType.cutout()));


        // Renderiza o lever customizado
        SuperByteBuffer lever = CachedBuffers.partial(SSPartialModels.ADV_CONTROLLER_LEVER, state);
        lever.transform(ms)
                .center()
                .rotateYDegrees(AngleHelper.horizontalAngle(facing))
                .uncenter()
                .renderInto(ms, buffer.getBuffer(RenderType.solid()));

    }
}
