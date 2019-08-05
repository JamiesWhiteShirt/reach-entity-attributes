package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandlerMixin {
    @ModifyConstant(
        method = "onPlayerInteractEntity(Lnet/minecraft/server/network/packet/PlayerInteractEntityC2SPacket;)V",
        constant = {
            @Constant(doubleValue = 36.0D),
            @Constant(doubleValue = 9.0D)
        }
    )
    private double modifyAttackRange(double value) {
        return ReachEntityAttributes.getSquaredAttackRange(((ServerPlayNetworkHandler) (Object) this).player, value);
    }

    @ModifyConstant(
        method = "onPlayerInteractBlock(Lnet/minecraft/server/network/packet/PlayerInteractBlockC2SPacket;)V",
        constant = {
            @Constant(doubleValue = 64.0D)
        }
    )
    private double modifyReachDistance(double value) {
        return ReachEntityAttributes.getSquaredReachDistance(((ServerPlayNetworkHandler) (Object) this).player, value);
    }
}
