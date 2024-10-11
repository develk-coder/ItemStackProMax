package develk.itemstackpromax.util;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.kyori.adventure.platform.fabric.FabricServerAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Formatter;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import net.minecraft.text.Text;

public class Colors {
    public static volatile FabricServerAudiences audiences;
    
    public static MiniMessage miniMessage() {
        return MiniMessage.builder()
            .tags(TagResolver.builder()
                .resolver(StandardTags.color())
                .resolver(StandardTags.decorations())
                .resolver(StandardTags.font())
                .resolver(StandardTags.gradient())
                .resolver(StandardTags.rainbow())
                .resolver(StandardTags.transition())
                .resolver(StandardTags.translatable())
                .resolver(StandardTags.reset())
                .build()
            ).build();
    }
    
    public static FabricServerAudiences getAudience() {
        if (audiences == null) return null;
        return audiences;
    }
    
    public static Component toComponent(String value) {
        return miniMessage().deserialize(value);
    }
    
    public static Text toText(String miniMessage) {
        if (getAudience() == null) return null;
        return getAudience().toNative(toComponent(miniMessage));
    }
    
    public static Text toText(String miniMessage, Number n1) {
        if (getAudience() == null) return null;
        return getAudience().toNative(miniMessage().deserialize(miniMessage,
            Formatter.number("n1", n1)
        ));
    }
    
    public static Text toText(String miniMessage, Number n1, Number n2) {
        if (getAudience() == null) return null;
        return getAudience().toNative(miniMessage().deserialize(miniMessage,
            Formatter.number("n1", n1),
            Formatter.number("n2", n2)
        ));
    }
    
    public static Text toText(String miniMessage, Number n1, Number n2, Number n3) {
        if (getAudience() == null) return null;
        return getAudience().toNative(miniMessage().deserialize(miniMessage,
            Formatter.number("n1", n1),
            Formatter.number("n2", n2),
            Formatter.number("n3", n3)
        ));
    }
    
    public static void init() {
        ServerLifecycleEvents.SERVER_STARTING.register(server -> audiences = FabricServerAudiences.of(server));
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> audiences = null);
    }
}
