package develk.itemstackpromax.mixin.client;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(DrawContext.class)
public class DrawContextMixin {
    @ModifyVariable(
            method = "drawStackCount(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V",
            at = @At("HEAD"),
            argsOnly = true
    )
    public String itemStackTextStringFormat(String original, TextRenderer textRenderer, ItemStack stack, int x, int y, @Nullable String countOverride) {
        int stackCount = stack.getCount();

        if (stackCount >= 1000000000) {
            double value = stackCount / 1000000000.0;
            return formatNumber(value) + "§6B";
        } else if (stackCount >= 1000000) {
            double value = stackCount / 1000000.0;
            return formatNumber(value) + "§3M";
        } else if (stackCount >= 1000) {
            double value = stackCount / 1000.0;
            return formatNumber(value) + "§9K";
        } else {
            return original;
        }
    }

    @Unique
    private String formatNumber(double value) {
        if (value == (int) value || value > 100D) {
            return String.valueOf((int) value);
        }
        if (String.format("%.1f", value).charAt(String.format("%.1f", value).indexOf('.') + 1) == '0') {
            return String.valueOf((int) value);
        }
        return String.format("%.1f", value);
    }
}
