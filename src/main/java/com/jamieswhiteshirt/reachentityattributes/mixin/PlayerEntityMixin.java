package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType_1, World world_1) {
        super(entityType_1, world_1);
    }

    @ModifyConstant(
        method = "attack(Lnet/minecraft/entity/Entity;)V",
        constant = @Constant(doubleValue = 9.0D)
    )
    private double modifyAttackRange(double value) {
        return ReachEntityAttributes.getSquaredAttackRange(this, value);
    }
}
