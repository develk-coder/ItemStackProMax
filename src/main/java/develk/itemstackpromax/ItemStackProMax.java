package develk.itemstackpromax;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemStackProMax implements ModInitializer {
    public static final String MODID = "itemstackpromax";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
    public static Config CONFIG = Config.load();

    private static int executeGet(CommandContext<ServerCommandSource> context) {
        context.getSource().sendFeedback(() -> Text.literal("global_max: " + Config.GLOBAL_MAX), false);
        return 1;
    }

    private static int executeSet(CommandContext<ServerCommandSource> context) {
        int value = IntegerArgumentType.getInteger(context, "global_max");
        if (value >= 1) {
            int old_global_max = Config.GLOBAL_MAX;
            CONFIG.global_max = value;
            CONFIG.reload();
            context.getSource().sendFeedback(() -> Text.literal("global_max: " + old_global_max + " -> " + Config.GLOBAL_MAX), false);
            return 1;
        }
        context.getSource().sendFeedback(() -> Text.literal("Numbers must be in the range of [1, 2,147,483,647]").formatted(Formatting.RED), false);
        return 0;
    }

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("ispm")
                    .executes(context -> {
                        context.getSource().sendFeedback(() -> Text.literal("ItemStackProMax Version: 1.0.5"), false);
                        return 1;
                    })
                    .then(CommandManager.literal("get")
                            .executes(ItemStackProMax::executeGet))
                    .then(CommandManager.literal("set")
                            .then(CommandManager.argument("global_max", IntegerArgumentType.integer())
                                    .requires(source -> source.hasPermissionLevel(1))
                                    .executes(ItemStackProMax::executeSet)
                            )
                            .requires(source -> source.hasPermissionLevel(1)))
            );
        });
        LOGGER.info("ItemStackProMax!");
    }
}