package com.jamieswhiteshirt.reachentityattributes.mixin.client;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ClientPlayerInteractionManager.class)
abstract class ClientPlayerInteractionManagerMixin {
    @Shadow @Final private MinecraftClient client;

    @ModifyExpressionValue(method = "getReachDistance", at = @At(value = "CONSTANT", args = "floatValue=5.0"))
    private float getActualReachDistance0(float reachDistance) {
        if (this.client.player != null) {
            // TODO Warn on loss of precision if present?
            return (float) ReachEntityAttributes.getReachDistance(this.client.player, reachDistance);
        }
        return reachDistance;
    }

    @ModifyExpressionValue(method = "getReachDistance", at = @At(value = "CONSTANT", args = "floatValue=4.5"))
    private float getActualReachDistance1(float reachDistance) {
        if (this.client.player != null) {
            // TODO Warn on loss of precision if present?
            return (float) ReachEntityAttributes.getReachDistance(this.client.player, reachDistance);
        }
        return reachDistance;
    }
}
