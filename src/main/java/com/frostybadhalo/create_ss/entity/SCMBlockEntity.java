// entity/SCMBlockEntity.java - APENAS BlockEntity
package com.frostybadhalo.create_ss.entity;

import com.frostybadhalo.create_ss.registry.SSBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SCMBlockEntity extends BlockEntity {

    // Valores configuráveis
    private float minPitch = 0.45f;
    private float maxPitch = 1.05f;
    private float baseDistance = 2.5f;
    private float minDistance = 2.0f;

    public SCMBlockEntity(BlockPos pos, BlockState state) {
        super(SSBlockEntities.SCMBlockEntity.get(), pos, state);
    }

    // Getters e Setters
    public float getMinPitch() { return minPitch; }
    public void setMinPitch(float value) {
        this.minPitch = value;
        setChanged();
    }
    // ... outros getters/setters

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putFloat("minPitch", minPitch);
        tag.putFloat("maxPitch", maxPitch);
        tag.putFloat("baseDistance", baseDistance);
        tag.putFloat("minDistance", minDistance);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        minPitch = tag.getFloat("minPitch");
        maxPitch = tag.getFloat("maxPitch");
        baseDistance = tag.getFloat("baseDistance");
        minDistance = tag.getFloat("minDistance");
    }
}