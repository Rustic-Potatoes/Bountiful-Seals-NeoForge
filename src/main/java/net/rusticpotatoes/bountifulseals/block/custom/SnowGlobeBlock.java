package net.rusticpotatoes.bountifulseals.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rusticpotatoes.bountifulseals.block.custom.crate.CrateBlock;

import java.lang.constant.ConstantDesc;

public class SnowGlobeBlock extends Block {
    public static final MapCodec<SnowGlobeBlock> CODEC = simpleCodec(SnowGlobeBlock::new);
    protected static final VoxelShape SHAPE = Block.box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0) ;

    public SnowGlobeBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
       // Direction direction = getConnectedDirection(state).getOpposite();
        return Block.canSupportCenter(level, pos.relative(Direction.DOWN), Direction.DOWN.getOpposite());
    }
}
