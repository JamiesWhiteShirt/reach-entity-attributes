package com.jamieswhiteshirt.reachentityattributes.mixin.client;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ClientPlayerInteractionManager.class)
abstract class ClientPlayerInteractionManagerMixin {
    @Unique
    private static final Logger LOGGER = LogManager.getLogger("reach-entity-attributes");

    @Shadow @Final private MinecraftClient client;

    @ModifyConstant(
        method = "getReachDistance()F",
        require = 2, allow = 2, constant = { @Constant(floatValue = 5.0F), @Constant(floatValue = 4.5F) })
    private float getActualReachDistance(final float reachDistance) {
        if (this.client.player != null) {
            // TODO Warn on loss of precision if present?
            return (float) ReachEntityAttributes.getReachDistance(this.client.player, reachDistance);
        }
        return reachDistance;
    }
}
