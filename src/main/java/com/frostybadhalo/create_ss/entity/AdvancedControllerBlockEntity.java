package com.frostybadhalo.create_ss.entity;

import com.frostybadhalo.create_ss.registry.SSBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AdvancedControllerBlockEntity extends BlockEntity {

    private int targetSpeed = 0;

    public AdvancedControllerBlockEntity(BlockPos pos, BlockState state) {
        super(SSBlockEntities.ADVANCED_CONTROLLER.get(), pos, state);
    }

    public int getTargetSpeed() {
        return targetSpeed;
    }

    public void setTargetSpeed(int speed) {
        this.targetSpeed = speed;
        setChanged();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("TargetSpeed", targetSpeed);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        targetSpeed = tag.getInt("TargetSpeed");
    }
}
