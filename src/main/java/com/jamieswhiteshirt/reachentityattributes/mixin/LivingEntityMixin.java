package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> entityType_1, World world_1) {
        super(entityType_1, world_1);
    }

    @Inject(
        method = "initAttributes()V",
        at = @At("TAIL")
    )
    private void initAttributes(CallbackInfo ci) {
        ((LivingEntity) (Object) this).getAttributeContainer().register(ReachEntityAttributes.REACH);
        ((LivingEntity) (Object) this).getAttributeContainer().register(ReachEntityAttributes.ATTACK_RANGE);
    }
}
