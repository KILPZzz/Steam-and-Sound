package com.frostybadhalo.create_ss.blocks;

import com.frostybadhalo.create_ss.entity.AdvancedControllerBlockEntity;
import com.frostybadhalo.create_ss.registry.SSShapes;
import com.simibubi.create.content.contraptions.ContraptionWorld;

import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.foundation.block.ProperWaterloggedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


public class AdvancedControlsBlock extends HorizontalDirectionalBlock implements IWrenchable, EntityBlock, ProperWaterloggedBlock {

    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    public static final BooleanProperty VIRTUAL = BooleanProperty.create("virtual");

    //--------- CONSTRUTOR ----------//
    public AdvancedControlsBlock(Properties props) {
        super(props);
        registerDefaultState(defaultBlockState().setValue(OPEN, false)
                .setValue(WATERLOGGED, false)
                .setValue(VIRTUAL, false));
    }

    //--------- STATE DO BLOCK | DECORAÇÃO  ----------//
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder.add(FACING, OPEN, WATERLOGGED, VIRTUAL));
    }

    //--------- TIPO DE FORMA ----------//
    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel,
                                  BlockPos pCurrentPos, BlockPos pNeighborPos) {
        System.out.println(" Mudando Shape para.. ");
        updateWater(pLevel, pState, pCurrentPos);
        return pState.setValue(OPEN, pLevel instanceof ContraptionWorld);
    }



    @Override
    public FluidState getFluidState(BlockState state) {
        return fluidState(state);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AdvancedControllerBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SSShapes.ADV_CONTROLS.get(pState.getValue(FACING));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SSShapes.ADV_CONTROLS_COLLISION.get(pState.getValue(FACING));
    }

    //---------  ----------//
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState state = withWater(super.getStateForPlacement(pContext), pContext);
        Direction horizontalDirection = pContext.getHorizontalDirection();
        Player player = pContext.getPlayer();

        state = state.setValue(FACING, horizontalDirection.getOpposite());
        if (player != null && player.isShiftKeyDown())
            state = state.setValue(FACING, horizontalDirection);

        return state;
    }

}
