package com.epherical.croptopia.config;

import com.epherical.epherolib.libs.org.spongepowered.configurate.ConfigurationNode;
import com.epherical.epherolib.libs.org.spongepowered.configurate.serialize.SerializationException;
import com.epherical.epherolib.libs.org.spongepowered.configurate.serialize.TypeSerializer;
import net.minecraft.ResourceLocationException;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Serializes an {@link ResourceLocation} to a configuration object.
 *
 * <p>When identifiers are output, they are given in the canonical
 * string format.
 *
 * <p>When identifiers are read, they are accepted in the formats of:
 * <ul>
 *     <li>
 *         A list of either one or two elements. If the list is two elements,
 *         the first is the namespace and the second is a path. If the list is
 *         one element, that element is parsed as a string (see below).
 *     </li>
 *     <li>A string, in standard <pre>[&lt;namespace&gt;:]&lt;path&gt;</pre>
 *     format, where the default namespace is <pre>minecraft</pre></li>
 * </ul>
 */
public final class IdentifierSerializer implements TypeSerializer<ResourceLocation> {

    //private static final String NAMESPACE_MINECRAFT = "minecraft";
    public static final IdentifierSerializer INSTANCE = new IdentifierSerializer();

    @Override
    public ResourceLocation deserialize(final @NotNull Type type, final @NotNull ConfigurationNode value) throws SerializationException {
        return fromNode(value);
    }

    @Override
    public void serialize(final @NotNull Type type, final @Nullable ResourceLocation obj, final @NotNull ConfigurationNode value) {
        toNode(obj, value);
    }

    static ResourceLocation fromNode(final ConfigurationNode node) throws SerializationException {
        if (node.virtual()) {
            return null;
        }
        if (node.isList()) {
            final List<? extends ConfigurationNode> children = node.childrenList();
            switch (children.size()) {
                case 2:
                    final String key = children.get(0).getString();
                    final String value = children.get(1).getString();
                    if (key != null && value != null) {
                        return createIdentifier(key, value);
                    }
                    throw listAcceptedFormats();
                case 1:
                    final String combined = children.get(0).getString();
                    if (combined != null) {
                        return createIdentifier(combined);
                    }
                    throw listAcceptedFormats();
                default:
                    throw listAcceptedFormats();

            }
        } else {
            final String val = node.getString();
            if (val == null) {
                throw listAcceptedFormats();
            }
            return ResourceLocation.parse(val);
        }
    }

    static ResourceLocation createIdentifier(final String key, final String value) throws SerializationException {
        try {
            return ResourceLocation.fromNamespaceAndPath(key, value);
        } catch (final ResourceLocationException ex) {
            throw new SerializationException(ex);
        }
    }

    static ResourceLocation createIdentifier(final String data) throws SerializationException {
        try {
            return ResourceLocation.parse(data);
        } catch (final ResourceLocationException ex) {
            throw new SerializationException(ex.getMessage());
        }
    }

    private static SerializationException listAcceptedFormats() {
        return new SerializationException("The provided item must be in [<namespace>:]<path> format");
    }

    static void toNode(final ResourceLocation ident, final ConfigurationNode node) {
        if (ident == null) {
            node.raw(null);
        } else {
            node.raw(ident.toString());
        }
    }

}
