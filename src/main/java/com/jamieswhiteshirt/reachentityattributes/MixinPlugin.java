package com.jamieswhiteshirt.reachentityattributes;

import java.util.List;
import java.util.Set;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import net.fabricmc.loader.api.FabricLoader;

public class MixinPlugin implements IMixinConfigPlugin {
    private static final String FORGIFIED_MIXIN_PACKAGE = "com.jamieswhiteshirt.reachentityattributes.mixin.forgified";
    @Override
    public void onLoad(String mixinPackage) { }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {

        switch (mixinClassName) {
            case "com.jamieswhiteshirt.reachentityattributes.mixin.LivingEntityMixin":
                return true;
            default:
        }

        return mixinClassName.startsWith(FORGIFIED_MIXIN_PACKAGE) == FabricLoader.getInstance().isModLoaded("connectormod");
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) { }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) { }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) { }
}
