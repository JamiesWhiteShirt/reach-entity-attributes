package com.jamieswhiteshirt.reachentityattributes;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class ReachEntityAttributes {
    public static final EntityAttribute REACH = make("reach", 0.0, -1024.0, 1024.0);
    public static final EntityAttribute ATTACK_RANGE = make("attack_range", 0.0, -1024.0, 1024.0);

    private ReachEntityAttributes() {
    }

    public static double getReachDistance(final LivingEntity entity, final double baseReachDistance) {
        @Nullable final EntityAttributeInstance reachDistance = entity.getAttributeInstance(REACH);
        return (reachDistance != null) ? (baseReachDistance + reachDistance.getValue()) : baseReachDistance;
    }

    public static double getSquaredReachDistance(final LivingEntity entity, final double sqBaseReachDistance) {
        final double reachDistance = getReachDistance(entity, Math.sqrt(sqBaseReachDistance));
        return reachDistance * reachDistance;
    }

    public static double getAttackRange(final LivingEntity entity, final double baseAttackRange) {
        @Nullable final EntityAttributeInstance attackRange = entity.getAttributeInstance(ATTACK_RANGE);
        return (attackRange != null) ? (baseAttackRange + attackRange.getValue()) : baseAttackRange;
    }

    public static double getSquaredAttackRange(final LivingEntity entity, final double sqBaseAttackRange) {
        final double attackRange = getAttackRange(entity, Math.sqrt(sqBaseAttackRange));
        return attackRange * attackRange;
    }

    private static EntityAttribute make(final String name, final double base, final double min, final double max) {
        final Identifier id = new Identifier("reach-entity-attributes", name);
        final String key = "attribute.name.generic.reach-entity-attributes." + name;
        final EntityAttribute attribute = new ClampedEntityAttribute(key, base, min, max).setTracked(true);
        return Registry.register(Registry.ATTRIBUTE, id, attribute);
    }
}
