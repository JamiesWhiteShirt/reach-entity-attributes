package com.jamieswhiteshirt.reachentityattributes.mixin.client;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.resource.SynchronousResourceReloader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GameRenderer.class)
abstract class GameRendererMixin implements SynchronousResourceReloader/*, AutoCloseable*/ {
    @Shadow @Final private MinecraftClient client;

    @ModifyExpressionValue(method = "updateTargetedEntity", at = @At(value = "CONSTANT", args = "doubleValue=6.0", ordinal = 0))
    private double getActualReachDistance(final double reachDistance) {
        if (this.client.player != null) {
            return ReachEntityAttributes.getReachDistance(this.client.player, reachDistance);
        }
        return reachDistance;
    }

    @ModifyExpressionValue(method = "updateTargetedEntity", at = @At(value = "CONSTANT", args = "doubleValue=3.0"))
    private double getActualAttackRange0(final double attackRange) {
        if (this.client.player != null) {
            return ReachEntityAttributes.getAttackRange(this.client.player, attackRange);
        }
        return attackRange;
    }

    @ModifyExpressionValue(method = "updateTargetedEntity", at = @At(value = "CONSTANT", args = "doubleValue=9.0"))
    private double getActualAttackRange1(final double attackRange) {
        if (this.client.player != null) {
            return ReachEntityAttributes.getSquaredAttackRange(this.client.player, attackRange);
        }
        return attackRange;
    }
}
