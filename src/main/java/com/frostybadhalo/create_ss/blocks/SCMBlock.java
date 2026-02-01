package com.frostybadhalo.create_ss.blocks;

import com.simibubi.create.api.behaviour.movement.MovementBehaviour;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

import com.frostybadhalo.create_ss.visuals.SSModSounds;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.sounds.SoundSource;


public class SCMBlock extends Block implements IWrenchable {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public SCMBlock(Properties props) {
        super(props);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(POWERED, false));
        MovementBehaviour.movementBehaviour(new SCMSoundBehaviour()).accept(this);
    }
    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, level, pos, block, fromPos, isMoving);
        if (!level.isClientSide) {
            boolean powered = level.hasNeighborSignal(pos);
            boolean wasPowered = state.getValue(POWERED);
            if (powered && !wasPowered) {
                // Play sound when powered
                level.setBlock(pos, state.setValue(POWERED, true), 3);
                level.playSound(null, pos, SSModSounds.CHUG.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
            } else if (!powered && wasPowered) {
                level.setBlock(pos, state.setValue(POWERED, false), 3);
            }
        }
    }



    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        System.out.println("SCMBlock placed at " + pos);

        // Verificar se o MovementBehaviour foi registrado
        MovementBehaviour behaviour = MovementBehaviour.REGISTRY.get(this);
        if (behaviour != null) {
            System.out.println("MovementBehaviour IS registered for SCMBlock");
        } else {
            System.out.println("ERROR: MovementBehaviour NOT registered for SCMBlock");
        }
    }


    // ANGULO DO BLOCO NO CHAO
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(POWERED, false);
    }

}
