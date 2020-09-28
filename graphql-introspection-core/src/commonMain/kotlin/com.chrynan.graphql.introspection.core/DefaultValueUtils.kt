package com.chrynan.graphql.introspection.core

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.json.JsonElement

internal val DEFAULT_VALUE_DESCRIPTOR = PrimitiveSerialDescriptor("DefaultValue", PrimitiveKind.STRING)

@Suppress("UNCHECKED_CAST")
internal fun <T : DefaultValue?> getDefaultValueSerializer(value: T, typeRef: TypeRef? = null): KSerializer<T> =
    when {
        value is Boolean -> Boolean.serializer()
        value is Int -> Int.serializer()
        value is Float -> Float.serializer()
        value is String -> String.serializer()
        value is Enum<*> -> String.serializer()
        value is List<*> && value.isNotEmpty() -> ListSerializer(getDefaultValueSerializer(value[0]))
        typeRef != null && typeRef.kind == Kind.LIST && typeRef.ofType != null -> ListSerializer(
            getSerializerFor(
                typeRef.ofType
            )
        )
        value is JsonElement -> JsonElement.serializer()
        else -> throw SerializationException("Could not obtain Serializer for unexpected default value type of $value")
    } as KSerializer<T>

internal fun getSerializerFor(typeRef: TypeRef): KSerializer<*> =
    when {
        typeRef.kind == Kind.SCALAR && typeRef.name?.toLowerCase() == "boolean" -> Boolean.serializer()
        typeRef.kind == Kind.SCALAR && typeRef.name?.toLowerCase() == "int" -> Int.serializer()
        typeRef.kind == Kind.SCALAR && typeRef.name?.toLowerCase() == "integer" -> Int.serializer()
        typeRef.kind == Kind.SCALAR && typeRef.name?.toLowerCase() == "string" -> String.serializer()
        typeRef.kind == Kind.SCALAR && typeRef.name?.toLowerCase() == "float" -> Float.serializer()
        typeRef.kind == Kind.SCALAR && typeRef.name?.toLowerCase() == "id" -> String.serializer()
        typeRef.kind == Kind.ENUM -> String.serializer()
        typeRef.kind == Kind.LIST && typeRef.ofType != null -> ListSerializer(getSerializerFor(typeRef.ofType))
        typeRef.kind == Kind.NON_NULL && typeRef.ofType != null -> getSerializerFor(typeRef.ofType)
        else -> JsonElement.serializer()
    }
