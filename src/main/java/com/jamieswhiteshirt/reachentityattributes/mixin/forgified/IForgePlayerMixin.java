package com.jamieswhiteshirt.reachentityattributes.mixin.forgified;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;

import net.minecraft.entity.player.PlayerEntity;

@Pseudo
@Mixin(targets = "net.minecraftforge.common.extensions.IForgePlayer")
public interface IForgePlayerMixin {

    @Inject(method = "getBlockReach()D", at = @At("RETURN"), cancellable = true)
    private void getActualBlockReach(CallbackInfoReturnable<Double> info) {
        info.setReturnValue(ReachEntityAttributes.getReachDistance((PlayerEntity)this, info.getReturnValue()));
    }

    @Inject(method = "getEntityReach()D", at = @At("RETURN"), cancellable = true)
    private void getActualEntityReach(CallbackInfoReturnable<Double> info) {
        info.setReturnValue(ReachEntityAttributes.getAttackRange((PlayerEntity)this, info.getReturnValue()));
    }
}
