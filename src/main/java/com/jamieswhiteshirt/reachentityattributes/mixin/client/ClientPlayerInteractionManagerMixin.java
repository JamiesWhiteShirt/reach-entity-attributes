package com.jamieswhiteshirt.reachentityattributes.mixin.client;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ClientPlayerInteractionManager.class)
public abstract class ClientPlayerInteractionManagerMixin {
    @Shadow @Final private MinecraftClient client;

    @ModifyConstant(
        method = "getReachDistance()F",
        constant = {
            @Constant(floatValue = 5.0F),
            @Constant(floatValue = 4.5F)
        }
    )
    private float modifyReachDistance(float value) {
        return (float) ReachEntityAttributes.getReachDistance(client.player, value);
    }
}
