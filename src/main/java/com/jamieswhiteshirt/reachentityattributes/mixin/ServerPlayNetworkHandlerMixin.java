package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(ServerPlayNetworkHandler.class)
abstract class ServerPlayNetworkHandlerMixin implements ServerPlayPacketListener {
    @Shadow public ServerPlayerEntity player;

    /**
     * Prevents players from interacting with entities that are too far away.
     *
     * <p>Attack range is further checked in {@link PlayerEntityInteractionHandlerMixin}.
     */
    @ModifyExpressionValue(method = "onPlayerInteractEntity", at = @At(value = "CONSTANT", args = "doubleValue=36.0"))
    private double getActualAttackRange(final double attackRange, final PlayerInteractEntityC2SPacket packet) {
        return ReachEntityAttributes.getSquaredReachDistance(this.player, attackRange);
    }

    @ModifyExpressionValue(method = "onPlayerInteractBlock", at = @At(value = "CONSTANT", args = "doubleValue=64.0", ordinal = 0))
    private double getActualReachDistance(final double reachDistance) {
        return ReachEntityAttributes.getSquaredReachDistance(this.player, reachDistance);
    }
}
