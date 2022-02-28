package me.thonk.croptopia.config;

/*
 * Copyright 2020 zml
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import net.minecraft.util.Identifier;
import net.minecraft.util.InvalidIdentifierException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Serializes an {@link Identifier} to a configuration object.
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
public final class IdentifierSerializer implements TypeSerializer<Identifier> {

    //private static final String NAMESPACE_MINECRAFT = "minecraft";
    public static final IdentifierSerializer INSTANCE = new IdentifierSerializer();

    @Override
    public Identifier deserialize(final @NotNull Type type, final @NotNull ConfigurationNode value) throws SerializationException {
        return fromNode(value);
    }

    @Override
    public void serialize(final @NotNull Type type, final @Nullable Identifier obj, final @NotNull ConfigurationNode value) {
        toNode(obj, value);
    }

    static Identifier fromNode(final ConfigurationNode node) throws SerializationException {
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
            return new Identifier(val);
        }
    }

    static Identifier createIdentifier(final String key, final String value) throws SerializationException {
        try {
            return new Identifier(key, value);
        } catch (final InvalidIdentifierException ex) {
            throw new SerializationException(ex);
        }
    }

    static Identifier createIdentifier(final String data) throws SerializationException {
        try {
            return new Identifier(data);
        } catch (final InvalidIdentifierException ex) {
            throw new SerializationException(ex.getMessage());
        }
    }

    private static SerializationException listAcceptedFormats() {
        return new SerializationException("The provided item must be in [<namespace>:]<path> format");
    }

    static void toNode(final Identifier ident, final ConfigurationNode node) {
        if (ident == null) {
            node.raw(null);
        } else {
            node.raw(ident.toString());
        }
    }

}
