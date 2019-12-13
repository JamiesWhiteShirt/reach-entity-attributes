package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ServerPlayerInteractionManager.class)
public abstract class ServerPlayerInteractionManagerMixin {
    @ModifyConstant(
        method = "processBlockBreakingAction(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/server/network/packet/PlayerActionC2SPacket$Action;Lnet/minecraft/util/math/Direction;I)V",
        constant = @Constant(doubleValue = 36.0D)
    )
    private double modifyReachDistance(double value) {
        return ReachEntityAttributes.getSquaredReachDistance(((ServerPlayerInteractionManager) (Object) this).player, value);
    }
}
