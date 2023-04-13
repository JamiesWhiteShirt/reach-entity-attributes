package com.jamieswhiteshirt.reachentityattributes;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@ParametersAreNonnullByDefault
public final class ReachEntityAttributes implements ModInitializer {
    public static final String MOD_ID = "reach-entity-attributes";

    public static final EntityAttribute REACH = make("reach", 0.0, -1024.0, 1024.0);
    public static final EntityAttribute ATTACK_RANGE = make("attack_range", 0.0, -1024.0, 1024.0);

    public static double getReachDistance(final LivingEntity entity, final double baseReachDistance) {
        @Nullable final var reachDistance = entity.getAttributeInstance(REACH);
        return (reachDistance != null) ? (baseReachDistance + reachDistance.getValue()) : baseReachDistance;
    }

    public static double getSquaredReachDistance(final LivingEntity entity, final double sqBaseReachDistance) {
        final var reachDistance = getReachDistance(entity, Math.sqrt(sqBaseReachDistance));
        return reachDistance * reachDistance;
    }

    public static double getAttackRange(final LivingEntity entity, final double baseAttackRange) {
        @Nullable final var attackRange = entity.getAttributeInstance(ATTACK_RANGE);
        return (attackRange != null) ? (baseAttackRange + attackRange.getValue()) : baseAttackRange;
    }

    public static double getSquaredAttackRange(final LivingEntity entity, final double sqBaseAttackRange) {
        final var attackRange = getAttackRange(entity, Math.sqrt(sqBaseAttackRange));
        return attackRange * attackRange;
    }

    public static List<PlayerEntity> getPlayersWithinReach(final World world, final int x, final int y, final int z, final double baseReachDistance) {
        return getPlayersWithinReach(player -> true, world, x, y, z, baseReachDistance);
    }

    public static List<PlayerEntity> getPlayersWithinReach(final Predicate<PlayerEntity> viewerPredicate, final World world, final int x, final int y, final int z, final double baseReachDistance) {
        final List<PlayerEntity> playersWithinReach = new ArrayList<>(0);
        for (final PlayerEntity player : world.getPlayers()) {
            if (viewerPredicate.test(player)) {
                final var reach = getReachDistance(player, baseReachDistance);
                final var dx = (x + 0.5) - player.getX();
                final var dy = (y + 0.5) - player.getEyeY();
                final var dz = (z + 0.5) - player.getZ();
                if (((dx * dx) + (dy * dy) + (dz * dz)) <= (reach * reach)) {
                    playersWithinReach.add(player);
                }
            }
        }
        return playersWithinReach;
    }

    public static boolean isWithinAttackRange(final PlayerEntity player, final Entity entity) {
        return player.squaredDistanceTo(entity) <= getSquaredAttackRange(player, 64.0);
    }

    private static EntityAttribute make(final String name, final double base, final double min, final double max) {
        return new ClampedEntityAttribute("attribute.name.generic." + MOD_ID + '.' + name, base, min, max).setTracked(true);
    }

    @Override
    public void onInitialize() {
        Registry.register(Registries.ATTRIBUTE, new Identifier(MOD_ID, "reach"), REACH);
        Registry.register(Registries.ATTRIBUTE, new Identifier(MOD_ID, "attack_range"), ATTACK_RANGE);
    }
}
