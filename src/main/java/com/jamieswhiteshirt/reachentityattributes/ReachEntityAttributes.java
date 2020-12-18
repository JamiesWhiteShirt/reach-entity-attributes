package com.jamieswhiteshirt.reachentityattributes;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ReachEntityAttributes {
    public static final EntityAttribute REACH = register("reach", new ClampedEntityAttribute("attribute.name.generic.reach-entity-attributes.reach", 0.0D, -1024.0D, 1024.0D)).setTracked(true);
    public static final EntityAttribute ATTACK_RANGE = register("attack_range", new ClampedEntityAttribute("attribute.name.generic.reach-entity-attributes.attack_range", 0.0D, -1024.0D, 1024.0D)).setTracked(true);

    public static double getReachDistance(LivingEntity entity, double baseValue) {
        return baseValue + entity.getAttributeInstance(REACH).getValue();
    }

    public static double getSquaredReachDistance(LivingEntity entity, double squaredBaseValue) {
        double baseReachDistance = Math.sqrt(squaredBaseValue);
        double value = baseReachDistance + entity.getAttributeInstance(REACH).getValue();
        return value * value;
    }

    public static double getAttackRange(LivingEntity entity, double baseValue) {
        return baseValue + entity.getAttributeInstance(ATTACK_RANGE).getValue();
    }

    public static double getSquaredAttackRange(LivingEntity entity, double squaredBaseValue) {
        double baseValue = Math.sqrt(squaredBaseValue);
        double value = baseValue + entity.getAttributeInstance(ATTACK_RANGE).getValue();
        return value * value;
    }

    private static EntityAttribute register(String name, EntityAttribute attribute) {
        return Registry.register(Registry.ATTRIBUTE, new Identifier("reach-entity-attributes", name), attribute);
    }
}
