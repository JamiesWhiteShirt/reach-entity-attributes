package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.server.network.ServerPlayNetworkHandler$1")
public class ServerPlayNetworkHandlerAttackMixin {
    @Shadow(aliases = "field_28963") @Final private ServerPlayNetworkHandler networkHandler;
    @Shadow(aliases = "field_28962") @Final private Entity entity;

    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    private void cancelIfTooFar(CallbackInfo ci) {
        if (ReachEntityAttributes.isOutsideOfAttackRange(this.networkHandler.player, this.entity)) {
            ci.cancel();
        }
    }
}
