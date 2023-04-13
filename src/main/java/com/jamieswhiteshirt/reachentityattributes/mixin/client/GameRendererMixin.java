package com.jamieswhiteshirt.reachentityattributes.mixin.client;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.resource.SynchronousResourceReloader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GameRenderer.class)
abstract class GameRendererMixin implements SynchronousResourceReloader/*, AutoCloseable*/ {
    @Shadow @Final MinecraftClient client;

    @ModifyConstant(
        method = "updateTargetedEntity(F)V",
        require = 1, allow = 1, constant = @Constant(doubleValue = 6.0))
    private double getActualReachDistance(final double reachDistance) {
        if (this.client.player != null) {
            return ReachEntityAttributes.getReachDistance(this.client.player, reachDistance);
        }
        return reachDistance;
    }

    @ModifyConstant(method = "updateTargetedEntity(F)V", constant = @Constant(doubleValue = 3.0))
    private double getActualAttackRange0(final double attackRange) {
        if (this.client.player != null) {
            return ReachEntityAttributes.getAttackRange(this.client.player, attackRange);
        }
        return attackRange;
    }

    @ModifyConstant(method = "updateTargetedEntity(F)V", constant = @Constant(doubleValue = 9.0))
    private double getActualAttackRange1(final double attackRange) {
        if (this.client.player != null) {
            return ReachEntityAttributes.getSquaredAttackRange(this.client.player, attackRange);
        }
        return attackRange;
    }
}
