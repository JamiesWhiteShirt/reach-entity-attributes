package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Tickable;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(ChestBlockEntity.class)
abstract class ChestBlockEntityMixin extends LootableContainerBlockEntity implements Tickable {
    ChestBlockEntityMixin(final BlockEntityType<?> type) {
        super(type);
    }

    @ModifyVariable(
        method = "countViewers(Lnet/minecraft/world/World;Lnet/minecraft/block/entity/LockableContainerBlockEntity;III)I",
        require = 1, allow = 1,
        at = @At(
            shift = Shift.BEFORE, value = "INVOKE", opcode = Opcodes.INVOKEVIRTUAL,
            target = "Ljava/util/List;iterator()Ljava/util/Iterator;"))
    private static List<PlayerEntity> getPlayersAccountingForReach(final List<PlayerEntity> players, final World world, final LockableContainerBlockEntity inventory, final int x, final int y, final int z) {
        return ReachEntityAttributes.getPlayersWithinReach(world, x, y, z, 5.0);
    }
}
