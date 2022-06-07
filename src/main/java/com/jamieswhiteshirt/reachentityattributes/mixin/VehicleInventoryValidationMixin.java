package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.entity.vehicle.StorageMinecartEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = {
    StorageMinecartEntity.class,
    ChestBoatEntity.class
})
public abstract class VehicleInventoryValidationMixin extends Entity {

    public VehicleInventoryValidationMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "canPlayerUse", at = @At("RETURN"), cancellable = true)
    private void canPlayerUseWithActualReachDistance(PlayerEntity playerEntity, CallbackInfoReturnable<Boolean> cir) {
        double reachDistance = ReachEntityAttributes.getReachDistance(playerEntity, 8.0);
        boolean canUse = !isRemoved() && this.getPos().isInRange(playerEntity.getPos(), reachDistance);
        if(canUse != cir.getReturnValue()) {
            cir.setReturnValue(canUse);
        }
    }
}