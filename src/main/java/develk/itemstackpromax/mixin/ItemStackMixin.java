package develk.itemstackpromax.mixin;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import develk.itemstackpromax.Config;
import net.minecraft.component.ComponentChanges;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.dynamic.Codecs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Mutable
    @Shadow
    @Final
    public static Codec<ItemStack> CODEC;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void overrideITEM_CODEC(CallbackInfo ci) {
        CODEC = Codec.lazyInitialized(
                () -> RecordCodecBuilder.create(
                        instance -> instance.group(
                                        Item.ENTRY_CODEC.fieldOf("id").forGetter(ItemStack::getRegistryEntry),
                                        Codecs.rangedInt(1, Integer.MAX_VALUE).fieldOf("count").orElse(1).forGetter(ItemStack::getCount),
                                        ComponentChanges.CODEC.optionalFieldOf("components", ComponentChanges.EMPTY).forGetter(ItemStack::getComponentChanges)
                                )
                                .apply(instance, ItemStack::new)
                )
        );
    }

    @Inject(method = "getMaxCount", at = @At("HEAD"), cancellable = true)
    private void overrideMaxCount(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(Config.GLOBAL_MAX);
    }
}
