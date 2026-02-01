package com.frostybadhalo.create_ss.registry;

import net.createmod.catnip.math.VoxelShaper;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SSShapes {
    public static final VoxelShaper ADV_CONTROLS =
        shape(0, 0, 6, 16, 16, 16)
            .add(0, 0, 4, 16, 2, 16)
            .forHorizontal(Direction.NORTH);

    public static final VoxelShaper ADV_CONTROLS_COLLISION =
        shape(0, 0, 6, 16, 16, 16)
            .forHorizontal(Direction.NORTH);

    private static Builder shape(double x1, double y1, double z1, double x2, double y2, double z2) {
        return new Builder(Block.box(x1, y1, z1, x2, y2, z2));
    }

    public static class Builder {
        private VoxelShape shape;
        public Builder(VoxelShape shape) { this.shape = shape; }
        public Builder add(double x1, double y1, double z1, double x2, double y2, double z2) {
            this.shape = Shapes.or(this.shape, Block.box(x1, y1, z1, x2, y2, z2));
            return this;
        }
        public VoxelShaper forHorizontal(Direction dir) {
            return net.createmod.catnip.math.VoxelShaper.forHorizontal(shape, dir);
        }
    }
}
