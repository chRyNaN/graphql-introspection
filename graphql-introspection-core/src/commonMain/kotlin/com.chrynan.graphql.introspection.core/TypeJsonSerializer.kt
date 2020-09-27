@file:Suppress("unused")

package com.chrynan.graphql.introspection.core

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

object TypeJsonSerializer : KSerializer<Type> {

    @OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor
        get() = buildSerialDescriptor("Type", PolymorphicKind.SEALED) {
            element<Type.Scalar>("Scalar")
            element<Type.Object>("Object")
            element<Type.Interface>("Interface")
            element<Type.Union>("Union")
            element<Type.Enum>("Enum")
            element<Type.InputObject>("InputObject")
        }

    override fun serialize(encoder: Encoder, value: Type) =
        when (value) {
            is Type.Union -> encoder.encodeSerializableValue(Type.Union.serializer(), value)
            is Type.Object -> encoder.encodeSerializableValue(Type.Object.serializer(), value)
            is Type.Interface -> encoder.encodeSerializableValue(Type.Interface.serializer(), value)
            is Type.InputObject -> encoder.encodeSerializableValue(Type.InputObject.serializer(), value)
            is Type.Enum -> encoder.encodeSerializableValue(Type.Enum.serializer(), value)
            is Type.Scalar -> encoder.encodeSerializableValue(Type.Scalar.serializer(), value)
        }

    override fun deserialize(decoder: Decoder): Type {
        val jsonDecoder =
            decoder as? JsonDecoder ?: throw SerializationException("Expected Json Decoder for ${decoder}.")
        val jsonObject = jsonDecoder.decodeJsonElement() as? JsonObject
            ?: throw SerializationException("Expected Json Object for ${jsonDecoder.decodeJsonElement()}.")

        val kindTypeName = jsonObject["kind"]?.jsonPrimitive?.content
            ?: throw SerializationException("Was expecting kind field in Json.")

        return when (val kind = Kind.fromTypeName(kindTypeName)) {
            Kind.SCALAR -> decoder.decodeSerializableValue(Type.Scalar.serializer())
            Kind.OBJECT -> decoder.decodeSerializableValue(Type.Object.serializer())
            Kind.INPUT_OBJECT -> decoder.decodeSerializableValue(Type.InputObject.serializer())
            Kind.INTERFACE -> decoder.decodeSerializableValue(Type.Interface.serializer())
            Kind.ENUM -> decoder.decodeSerializableValue(Type.Enum.serializer())
            Kind.UNION -> decoder.decodeSerializableValue(Type.Union.serializer())
            else -> throw SerializationException("Unexpected Type Kind was encountered. kind = $kind")
        }
    }
}
