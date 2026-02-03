package net.rusticpotatoes.bountifulseals.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidType;
import net.rusticpotatoes.bountifulseals.entity.ModEntities;
import net.rusticpotatoes.bountifulseals.tag.ModTags;
import org.jetbrains.annotations.Nullable;


public class HarpSealEntity extends Animal {
    public static final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public static AttributeSupplier.Builder createAttribute() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.FOLLOW_RANGE, 20)
                .add(Attributes.MOVEMENT_SPEED, 0.17F);

    }

    private void setupAnimationsStates() {
            if(this.idleAnimationTimeout <= 0) {
                this.idleAnimationTimeout = 80;
                idleAnimationState.start(this.tickCount);
            } else {
                --this.idleAnimationTimeout;
            }
    }

    public static boolean checkHarpSealSpawnRules(EntityType<HarpSealEntity> harpSeal, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        Holder<Biome> holder = level.getBiome(pos);
        if (!holder.is(Biomes.DEEP_FROZEN_OCEAN) && !holder.is(Biomes.FROZEN_OCEAN) && !holder.is(Biomes.ICE_SPIKES)) {
            return level.getBlockState(pos.below()).is(ModTags.Blocks.HARP_SEAL_SPAWNABLE_ON);
        } else {
            return level.getBlockState(pos.below()).is(Blocks.ICE);
        }
    }

    public HarpSealEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0, stack -> stack.is(ModTags.Items.HARP_SEAL_FOODS), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(ModTags.Items.HARP_SEAL_FOODS);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return ModEntities.HARP_SEAL.get().create(level);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            this.setupAnimationsStates();
        }
    }


    @Override
    public float getAgeScale() {
        return this.isBaby() ? 0.6F : 1.0F;
    }

}
