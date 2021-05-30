package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.entity.Entity;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
abstract class ServerPlayNetworkHandlerMixin implements ServerPlayPacketListener {
    @Shadow public ServerPlayerEntity player;

    /**
     * Prevents players from interacting with entities that are too far away.
     *
     * <p>Attack range is further checked in {@link ServerPlayNetworkHandlerAttackMixin}.
     */
    @ModifyConstant(
        method = "onPlayerInteractEntity(Lnet/minecraft/network/packet/c2s/play/PlayerInteractEntityC2SPacket;)V",
        require = 2, allow = 2, constant = @Constant(doubleValue = 36.0))
    private double getActualAttackRange(final double attackRange, final PlayerInteractEntityC2SPacket packet) {
        return ReachEntityAttributes.getSquaredReachDistance(this.player, attackRange);
    }

    @ModifyConstant(
        method = "onPlayerInteractBlock(Lnet/minecraft/network/packet/c2s/play/PlayerInteractBlockC2SPacket;)V",
        require = 1, allow = 1, constant = @Constant(doubleValue = 64.0))
    private double getActualReachDistance(final double reachDistance) {
        return ReachEntityAttributes.getSquaredReachDistance(this.player, reachDistance);
    }
}
