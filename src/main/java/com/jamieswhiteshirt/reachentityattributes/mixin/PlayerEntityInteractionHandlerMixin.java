package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.server.network.ServerPlayNetworkHandler$1")
abstract class PlayerEntityInteractionHandlerMixin implements PlayerInteractEntityC2SPacket.Handler {
    @Shadow(aliases = "field_28963") @Final private ServerPlayNetworkHandler field_28963;
    @Shadow(aliases = "field_28962") @Final private Entity field_28962;

    @Inject(method = "attack()V", at = @At("HEAD"), require = 1, allow = 1, cancellable = true)
    private void ensureWithinAttackRange(final CallbackInfo ci) {
        if (!ReachEntityAttributes.isWithinAttackRange(this.field_28963.player, this.field_28962)) {
            ci.cancel();
        }
    }
}
