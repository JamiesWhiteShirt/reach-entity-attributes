package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PlayerEntity.class)
abstract class PlayerEntityMixin extends LivingEntity {
    PlayerEntityMixin(final EntityType<? extends LivingEntity> type, final World world) {
        super(type, world);
    }

    @ModifyExpressionValue(method = "attack", at = @At(value = "CONSTANT", args = "doubleValue=9.0"))
    private double getActualAttackRange(final double attackRange) {
        return ReachEntityAttributes.getSquaredAttackRange(this, attackRange);
    }
}
