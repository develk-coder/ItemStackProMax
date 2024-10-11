package develk.itemstackpromax.mixin;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.ComponentChanges;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.dynamic.Codecs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Mutable @Shadow @Final public static Codec<ItemStack> CODEC;
    
    @Shadow @Final public static Codec<RegistryEntry<Item>> ITEM_CODEC;
    
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void clinit(CallbackInfo ci) {
        CODEC = Codec.lazyInitialized(
            () -> RecordCodecBuilder.create(
                instance -> instance.group(
                    ITEM_CODEC.fieldOf("id").forGetter(ItemStack::getRegistryEntry),
                    Codecs.rangedInt(Integer.MIN_VALUE, Integer.MAX_VALUE).fieldOf("count").orElse(1).forGetter(ItemStack::getCount),
                    ComponentChanges.CODEC.optionalFieldOf("components", ComponentChanges.EMPTY).forGetter(ItemStack::getComponentChanges)
                ).apply(instance, ItemStack::new)
            )
        );
    }
}
