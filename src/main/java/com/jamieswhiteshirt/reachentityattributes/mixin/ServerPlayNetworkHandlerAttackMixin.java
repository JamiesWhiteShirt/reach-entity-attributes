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
    @SuppressWarnings("ShadowTarget")   // synthetic field "ServerPlayNetworkHandler.this"
    @Shadow
    @Final
    private ServerPlayNetworkHandler field_28963;
    @SuppressWarnings("ShadowTarget")   // synthetic field "entity"
    @Shadow
    @Final
    private Entity field_28962;

    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    private void cancelIfTooFar(CallbackInfo ci) {
        if (this.field_28963.player.squaredDistanceTo(this.field_28962) > ReachEntityAttributes.getSquaredAttackRange(this.field_28963.player, 64)) {
            ci.cancel();
        }
    }
}
