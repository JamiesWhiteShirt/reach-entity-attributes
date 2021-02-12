package com.jamieswhiteshirt.reachentityattributes;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class ReachEntityAttributes {
    public static final EntityAttribute REACH = make("reach", 0.0, -1024.0, 1024.0);
    public static final EntityAttribute ATTACK_RANGE = make("attack_range", 0.0, -1024.0, 1024.0);

    private ReachEntityAttributes() {
    }

    public static double getReachDistance(final LivingEntity entity, final double baseValue) {
        final EntityAttributeInstance reach = entity.getAttributeInstance(REACH);
        return (reach != null) ? (baseValue + reach.getValue()) : baseValue;
    }

    public static double getSquaredReachDistance(final LivingEntity entity, final double squaredBaseValue) {
        final double reachDistance = getReachDistance(entity, Math.sqrt(squaredBaseValue));
        return reachDistance * reachDistance;
    }

    public static double getAttackRange(final LivingEntity entity, final double baseValue) {
        final EntityAttributeInstance range = entity.getAttributeInstance(ATTACK_RANGE);
        return (range != null) ? (baseValue + range.getValue()) : baseValue;
    }

    public static double getSquaredAttackRange(final LivingEntity entity, final double squaredBaseValue) {
        final double attackRange = getAttackRange(entity, Math.sqrt(squaredBaseValue));
        return attackRange * attackRange;
    }

    private static EntityAttribute make(final String name, final double base, final double min, final double max) {
        final Identifier id = new Identifier("reach-entity-attributes", name);
        final String key = "attribute.name.generic.reach-entity-attributes." + name;
        final EntityAttribute attribute = new ClampedEntityAttribute(key, base, min, max).setTracked(true);
        return Registry.register(Registry.ATTRIBUTE, id, attribute);
    }
}
