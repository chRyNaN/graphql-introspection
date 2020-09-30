package com.chrynan.graphql.introspection.core

import kotlinx.serialization.*
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
class TypeRefJsonSerializer : KSerializer<TypeRef> {

    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor("TypeRef") {
            element<Kind>("kind")
            element<String?>("name")
            element<JsonElement?>("ofType")
        }

    override fun serialize(encoder: Encoder, value: TypeRef) {
        val composite = encoder.beginStructure(descriptor)
        composite.encodeSerializableElement(descriptor, 0, Kind.serializer(), value.kind)
        composite.encodeNullableSerializableElement(descriptor, 1, String.serializer().nullable, value.name)
        composite.encodeNullableSerializableElement(
            descriptor,
            2,
            JsonElement.serializer().nullable,
            value.ofType?.toJsonObject()
        )
        composite.endStructure(descriptor)
    }

    override fun deserialize(decoder: Decoder): TypeRef {
        val jsonDecoder =
            decoder as? JsonDecoder ?: throw SerializationException("Expected Json Decoder for ${decoder}.")
        val jsonObject = jsonDecoder.decodeJsonElement().jsonObject

        return getTypeRefFromJsonObject(jsonObject)
    }

    private fun getTypeRefFromJsonObject(jsonObject: JsonObject): TypeRef {
        val kind = Kind.fromTypeName(jsonObject.getValue("kind").jsonPrimitive.content, true)

        val name = jsonObject["name"]?.jsonPrimitive?.content

        val ofTypeJsonElement = jsonObject["ofType"]
        val ofType = if (ofTypeJsonElement == null || ofTypeJsonElement is JsonNull) null else getTypeRefFromJsonObject(
            ofTypeJsonElement.jsonObject
        )

        return TypeRef(
            kind = kind ?: Kind.SCALAR,
            name = name,
            ofType = ofType
        )
    }

    private fun TypeRef.toJsonObject(): JsonObject =
        buildJsonObject {
            put("kind", kind.toString())
            put("name", name)

            if (ofType != null) {
                putTypeRef("ofType", ofType)
            }
        }

    private fun JsonObjectBuilder.putTypeRef(wrapperKey: String, typeRef: TypeRef) {
        putJsonObject(wrapperKey) {
            put("kind", typeRef.kind.toString())
            put("name", typeRef.name)

            if (typeRef.ofType != null) {
                putTypeRef("ofType", typeRef.ofType)
            }
        }
    }
}
