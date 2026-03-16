package net.rusticpotatoes.bountifulseals.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.rusticpotatoes.bountifulseals.Log;
import net.rusticpotatoes.bountifulseals.entity.ModEntities;
import net.rusticpotatoes.bountifulseals.item.ModItems;
import net.rusticpotatoes.bountifulseals.tag.ModTags;
import org.jetbrains.annotations.Nullable;


public class HarpSealEntity extends Animal {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState boopAnimationState = new AnimationState();

    private static final EntityDataAccessor<Integer> DATA_BOOP_TIMEOUT;
    private int idleAnimTimeout = 0;
   // private int boopAnimTimeout = 0;

    public static AttributeSupplier.Builder createAttribute() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.FOLLOW_RANGE, 20)
                .add(Attributes.MOVEMENT_SPEED, 0.17F);

    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_BOOP_TIMEOUT, 0);
    }

    public int getBoopTimeout() {
        return (Integer) this.entityData.get(DATA_BOOP_TIMEOUT);
    }

    public void setBoopTimeout(int state) {
        this.entityData.set(DATA_BOOP_TIMEOUT, state, true);
    }

    private void PlayBoopAnim() {
        if (this.getBoopTimeout() <= 0) {
            this.setBoopTimeout(30);
        }
      //  Log.info(getBoopTimeout());
    }

    private boolean canPlayBoopAnim() {
        return this.getBoopTimeout() <= 0 &&!this.isBaby();
    }

    private void setupAnimationsStates() {
        if (this.idleAnimTimeout <= 0) {
            this.idleAnimTimeout = 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimTimeout;
        }
     //   Log.info(this.getBoopTimeout());
        // Log.info("worked" + getBoopTimeout());
        if (this.getBoopTimeout() == 30) {
            this.boopAnimationState.start(this.tickCount);
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
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (hand == InteractionHand.MAIN_HAND) {

            if (player.isHolding(ModItems.HARP_SEAL_SPAWN_EGG.get())) return InteractionResult.PASS;

            if (!this.level().isClientSide()) {
                if (canPlayBoopAnim()) {
                    PlayBoopAnim();
                    if (player.isHolding(ModItems.SUGAR_CRYSTAL.get())) {
                        player.setItemInHand(hand, new ItemStack(player.getItemInHand(hand).getItem(), player.getItemInHand(hand).getCount() - 1));
                        ItemStack stack = new ItemStack(ModItems.SILLINESS_EXTRACT.get(), 1);
                        this.level().addFreshEntity(new ItemEntity(this.level(),
                                this.blockPosition().getX() + 0.5, this.blockPosition().getY() + 0.5, this.blockPosition().getZ() + 0.5, stack));
                        return InteractionResult.SUCCESS;
                    } else {
                        return InteractionResult.SUCCESS_NO_ITEM_USED;
                    }
                }
            } else {
                if (canPlayBoopAnim()) {
                    if (player.isHolding(ModItems.SUGAR_CRYSTAL.get())) {
                        this.addParticlesAroundSelf(ParticleTypes.HAPPY_VILLAGER);
                    }
                    return InteractionResult.SUCCESS;
                }

            }
        }
        return InteractionResult.PASS;
    }

    protected void addParticlesAroundSelf(ParticleOptions particleOption) {
        for(int i = 0; i < 5; ++i) {
            double d0 = this.random.nextGaussian() * 0.02;
            double d1 = this.random.nextGaussian() * 0.02;
            double d2 = this.random.nextGaussian() * 0.02;
            this.level().addParticle(particleOption, this.getRandomX((double)1.0F), this.getRandomY() + (double)1.0F - 1, this.getRandomZ((double)1.0F), d0, d1, d2);
        }

    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            this.setupAnimationsStates();
        } else {
            if (this.getBoopTimeout() > 0) {
                this.setBoopTimeout(this.getBoopTimeout() - 1);
            } else {
                this.setBoopTimeout(0);
            }
        }
    }


    @Override
    public float getAgeScale() {
        return this.isBaby() ? 0.6F : 1.0F;
    }

    static {
        DATA_BOOP_TIMEOUT = SynchedEntityData.defineId(HarpSealEntity.class, EntityDataSerializers.INT);
    }
}
