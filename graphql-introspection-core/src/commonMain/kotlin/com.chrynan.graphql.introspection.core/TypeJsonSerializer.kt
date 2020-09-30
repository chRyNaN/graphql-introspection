@file:Suppress("unused")

package com.chrynan.graphql.introspection.core

import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.nullable
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
        val json = jsonDecoder.json
        val jsonObject = jsonDecoder.decodeJsonElement().jsonObject

        val kindTypeName = jsonObject.getValue("kind").jsonPrimitive.content

        // Note we deliberately handle deserializing each kind in a custom manner because there seems to be an issue
        // when delegating to each types default serializer. This might have something to do with the "kind" property
        // but it's very difficult to find any information about the issue.
        return when (val kind = Kind.fromTypeName(kindTypeName)) {
            Kind.SCALAR -> getScalar(jsonObject)
            Kind.OBJECT -> getObject(json, jsonObject)
            Kind.INPUT_OBJECT -> getInputObject(json, jsonObject)
            Kind.INTERFACE -> getInterface(json, jsonObject)
            Kind.ENUM -> getEnum(json, jsonObject)
            Kind.UNION -> getUnion(json, jsonObject)
            else -> throw SerializationException("Unexpected Type Kind was encountered. kind = $kind")
        }
    }

    private fun getScalar(jsonObject: JsonObject): Type.Scalar {
        val name = jsonObject.getValue("name").jsonPrimitive.content
        val description = jsonObject["description"]?.jsonPrimitive?.contentOrNull

        return Type.Scalar(
            name = name,
            description = description
        )
    }

    private fun getObject(json: Json, jsonObject: JsonObject): Type.Object {
        val name = jsonObject.getValue("name").jsonPrimitive.content
        val description = jsonObject["description"]?.jsonPrimitive?.contentOrNull
        val interfaces = jsonObject["interfaces"].decodeNullableList(json, TypeRef.serializer())
        val fields = jsonObject["fields"].decodeNullableList(json, Field.serializer())

        return Type.Object(
            name = name,
            description = description,
            interfaces = interfaces,
            fields = fields
        )
    }

    private fun getInputObject(json: Json, jsonObject: JsonObject): Type.InputObject {
        val name = jsonObject.getValue("name").jsonPrimitive.content
        val description = jsonObject["description"]?.jsonPrimitive?.contentOrNull
        val inputFields = jsonObject["inputFields"].decodeNullableList(json, InputField.serializer())

        return Type.InputObject(
            name = name,
            description = description,
            inputFields = inputFields
        )
    }

    private fun getInterface(json: Json, jsonObject: JsonObject): Type.Interface {
        val name = jsonObject.getValue("name").jsonPrimitive.content
        val description = jsonObject["description"]?.jsonPrimitive?.contentOrNull
        val possibleTypes = jsonObject["possibleTypes"].decodeNullableList(json, TypeRef.serializer())
        val interfaces = jsonObject["interfaces"].decodeNullableList(json, TypeRef.serializer())
        val fields = jsonObject["fields"].decodeNullableList(json, Field.serializer())

        return Type.Interface(
            name = name,
            description = description,
            possibleTypes = possibleTypes,
            interfaces = interfaces,
            fields = fields
        )
    }

    private fun getEnum(json: Json, jsonObject: JsonObject): Type.Enum {
        val name = jsonObject.getValue("name").jsonPrimitive.content
        val description = jsonObject["description"]?.jsonPrimitive?.contentOrNull
        val enumValues = jsonObject["enumValues"].decodeNullableList(json, Type.Enum.Value.serializer())

        return Type.Enum(
            name = name,
            description = description,
            enumValues = enumValues
        )
    }

    private fun getUnion(json: Json, jsonObject: JsonObject): Type.Union {
        val name = jsonObject.getValue("name").jsonPrimitive.content
        val description = jsonObject["description"]?.jsonPrimitive?.contentOrNull
        val possibleTypes = jsonObject["possibleTypes"].decodeNullableList(json, TypeRef.serializer())
        val fields = jsonObject["fields"].decodeNullableList(json, Field.serializer())

        return Type.Union(
            name = name,
            description = description,
            possibleTypes = possibleTypes,
            fields = fields
        )
    }

    private fun <T> JsonElement?.decodeNullableList(json: Json, listItemSerializer: KSerializer<T>): List<T> {
        if (this == null) return emptyList()
        if (this is JsonNull) return emptyList()

        val list = json.decodeFromJsonElement(ListSerializer(listItemSerializer).nullable, jsonArray)

        return list ?: emptyList()
    }
}
