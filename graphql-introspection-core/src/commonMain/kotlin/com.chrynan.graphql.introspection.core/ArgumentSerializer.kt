package com.chrynan.graphql.introspection.core

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@OptIn(ExperimentalSerializationApi::class)
object ArgumentSerializer : KSerializer<Argument> {

    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor("Argument") {
            element<String>("name")
            element<String?>("description")
            element<Boolean>("isDeprecated")
            element<String?>("deprecationReason")
            element<TypeRef>("type")
            element("defaultValue", DEFAULT_VALUE_DESCRIPTOR)
        }

    override fun serialize(encoder: Encoder, value: Argument) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.name)
            encodeNullableSerializableElement(descriptor, 1, String.serializer(), value.description)
            encodeBooleanElement(descriptor, 2, value.isDeprecated)
            encodeNullableSerializableElement(descriptor, 3, String.serializer(), value.deprecationReason)
            encodeSerializableElement(descriptor, 4, TypeRef.serializer(), value.type)
            encodeSerializableElement(descriptor, 5, getDefaultValueSerializer(value.defaultValue), value.defaultValue)
        }
    }

    override fun deserialize(decoder: Decoder): Argument =
        decoder.decodeStructure(descriptor) {
            var name = ""
            var description: String? = null
            var isDeprecated = false
            var deprecationReason: String? = null
            var typeRef: TypeRef? = null
            var defaultValue: DefaultValue? = null

            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> name = decodeStringElement(descriptor, 0)
                    1 -> description = decodeStringElement(descriptor, 1)
                    2 -> isDeprecated = decodeBooleanElement(descriptor, 2)
                    3 -> deprecationReason = decodeStringElement(descriptor, 3)
                    4 -> typeRef = decodeSerializableElement(descriptor, 4, TypeRef.serializer())
                    5 -> defaultValue = decodeSerializableElement(descriptor, 5, getSerializerFor(typeRef!!))
                    CompositeDecoder.DECODE_DONE -> break
                    else -> throw SerializationException("Encountered an unexpected index when deserializing an Argument. index = $index")
                }
            }

            Argument(
                name = name,
                description = description,
                isDeprecated = isDeprecated,
                deprecationReason = deprecationReason,
                type = typeRef ?: throw SerializationException("Missing type property when deserializing an Argument."),
                defaultValue = defaultValue
            )
        }
}
