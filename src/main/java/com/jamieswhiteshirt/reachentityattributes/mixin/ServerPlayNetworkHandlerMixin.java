package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandlerMixin {
    @ModifyConstant(
        method = "onPlayerInteractEntity(Lnet/minecraft/network/packet/c2s/play/PlayerInteractEntityC2SPacket;)V",
        constant = {
            @Constant(doubleValue = 36.0D)
        }
    )
    private double modifyAttackRange(double value, PlayerInteractEntityC2SPacket packet) {
        if (packet.getType() == PlayerInteractEntityC2SPacket.InteractionType.ATTACK) {
            return ReachEntityAttributes.getSquaredAttackRange(((ServerPlayNetworkHandler) (Object) this).player, value);
        }
        // INTERACT, INTERACT_AT
        return ReachEntityAttributes.getSquaredReachDistance(((ServerPlayNetworkHandler) (Object) this).player, value);
    }

    @ModifyConstant(
        method = "onPlayerInteractBlock(Lnet/minecraft/network/packet/c2s/play/PlayerInteractBlockC2SPacket;)V",
        constant = {
            @Constant(doubleValue = 64.0D)
        }
    )
    private double modifyReachDistance(double value) {
        return ReachEntityAttributes.getSquaredReachDistance(((ServerPlayNetworkHandler) (Object) this).player, value);
    }
}
