package net.rusticpotatoes.bountifulseals.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;

public class SnowClumpsBlock extends PinkPetalsBlock {
    public SnowClumpsBlock(Properties p_273335_) {
        super(p_273335_);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader p_272968_, BlockPos p_273762_, BlockState p_273662_) {
        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level p_272604_, RandomSource p_273609_, BlockPos p_272988_, BlockState p_273231_) {
        return false;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return (state.isCollisionShapeFullBlock(level, pos));
    }

    @Override
    protected boolean canBeReplaced(BlockState state, Fluid fluid) {
        return true;
    }
}
