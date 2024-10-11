package develk.itemstackpromax.mixin;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(DrawContext.class)
public class DrawContextMixin {
    @ModifyVariable(
        method = "drawItemInSlot(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V",
        at = @At("HEAD"),
        argsOnly = true
    )
    public String itemStackTextStringFormat(String original, TextRenderer textRenderer, ItemStack stack, int x, int y, @Nullable String countOverride) {
        int stackCount = stack.getCount();
        if (stackCount >= 1000000000) {
            stackCount /= 1000000000;
            return stackCount + "§6B";
        } else if (stackCount >= 1000000) {
            stackCount /= 1000000;
            return stackCount + "§3M";
        } else if (stackCount >= 1000) {
            stackCount /= 1000;
            return stackCount + "§9K";
        } else {
            return original;
        }
    }
}
