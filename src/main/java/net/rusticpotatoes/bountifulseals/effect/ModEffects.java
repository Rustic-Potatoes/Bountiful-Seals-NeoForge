package net.rusticpotatoes.bountifulseals.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rusticpotatoes.bountifulseals.BountifulSeals;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, BountifulSeals.MOD_ID);

    public static final Holder<MobEffect> SILLINESS = MOB_EFFECTS.register("silliness",
            () -> new BaseEffect(MobEffectCategory.NEUTRAL, 0x82edda)
                    .addAttributeModifier(Attributes.SCALE,
                            ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "silliness_scale"), -0.75D,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    .addAttributeModifier(Attributes.JUMP_STRENGTH,
                            ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "silliness_jump_strength"), -0.1D,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED,
                            ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "silliness_movement_speed"), -0.25D,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    .addAttributeModifier(Attributes.STEP_HEIGHT,
                            ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "silliness_step_height"), -0.75D,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    .addAttributeModifier(Attributes.GRAVITY,
                            ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "silliness_gravity"),-0.1D,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    .addAttributeModifier(Attributes.SAFE_FALL_DISTANCE,
                            ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "silliness_safe_fall_distance"),-0.5D,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    );

    public static final Holder<MobEffect> TOMS_FOOLERY = MOB_EFFECTS.register("toms_foolery",
            () -> new BaseEffect(MobEffectCategory.NEUTRAL, 0x117596)
                    .addAttributeModifier(Attributes.SCALE,
                            ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "silliness_scale"), 1D,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    .addAttributeModifier(Attributes.JUMP_STRENGTH,
                            ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "silliness_jump_strength"), 0.5D,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED,
                            ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "silliness_movement_speed"), 0.25D,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    .addAttributeModifier(Attributes.STEP_HEIGHT,
                            ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "silliness_step_height"), 1D,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    .addAttributeModifier(Attributes.GRAVITY,
                            ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "silliness_gravity"),0.25D,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    .addAttributeModifier(Attributes.SAFE_FALL_DISTANCE,
                            ResourceLocation.fromNamespaceAndPath(BountifulSeals.MOD_ID, "silliness_safe_fall_distance"), 1D,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    );

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
