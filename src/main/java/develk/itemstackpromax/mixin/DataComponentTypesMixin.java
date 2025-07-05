package develk.itemstackpromax.mixin;

import develk.itemstackpromax.Config;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DataComponentTypes.class)
public class DataComponentTypesMixin {
    @Mutable
    @Shadow
    @Final
    public static ComponentMap DEFAULT_ITEM_COMPONENTS;
    @Shadow
    @Final
    public static ComponentType<Integer> MAX_STACK_SIZE;
    @Shadow
    @Final
    public static ComponentType<LoreComponent> LORE;
    @Shadow
    @Final
    public static ComponentType<ItemEnchantmentsComponent> ENCHANTMENTS;
    @Shadow
    @Final
    public static ComponentType<Integer> REPAIR_COST;
    @Shadow
    @Final
    public static ComponentType<AttributeModifiersComponent> ATTRIBUTE_MODIFIERS;
    @Shadow
    @Final
    public static ComponentType<Rarity> RARITY;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void overrideMaxStackSize(CallbackInfo ci) {
        DEFAULT_ITEM_COMPONENTS = ComponentMap.builder()
                .add(MAX_STACK_SIZE, Config.GLOBAL_MAX)
                .add(LORE, LoreComponent.DEFAULT)
                .add(ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT)
                .add(REPAIR_COST, 0)
                .add(ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT)
                .add(RARITY, Rarity.COMMON)
                .build();
    }
}
