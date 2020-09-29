package com.chrynan.graphql.introspection.core

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.json.JsonElement

internal val DEFAULT_VALUE_DESCRIPTOR = PrimitiveSerialDescriptor("DefaultValue", PrimitiveKind.STRING)

@Suppress("UNCHECKED_CAST")
internal fun <T : DefaultValue?> getDefaultValueSerializer(value: T, typeRef: TypeRef? = null): KSerializer<T> =
    when {
        value is Boolean -> Boolean.serializer().nullable
        value is Int -> Int.serializer().nullable
        value is Float -> Float.serializer().nullable
        value is String -> String.serializer().nullable
        value is Enum<*> -> String.serializer().nullable
        value is List<*> && value.isNotEmpty() -> ListSerializer(getDefaultValueSerializer(value[0])).nullable
        typeRef != null && typeRef.kind == Kind.LIST && typeRef.ofType != null -> ListSerializer(
            getSerializerFor(
                typeRef.ofType
            )
        ).nullable
        value is JsonElement -> JsonElement.serializer().nullable
        else -> throw SerializationException("Could not obtain Serializer for unexpected default value type of $value")
    } as KSerializer<T>

internal fun getSerializerFor(typeRef: TypeRef): KSerializer<*> =
    when {
        typeRef.kind == Kind.SCALAR && typeRef.name?.toLowerCase() == "boolean" -> Boolean.serializer().nullable
        typeRef.kind == Kind.SCALAR && typeRef.name?.toLowerCase() == "int" -> Int.serializer().nullable
        typeRef.kind == Kind.SCALAR && typeRef.name?.toLowerCase() == "integer" -> Int.serializer().nullable
        typeRef.kind == Kind.SCALAR && typeRef.name?.toLowerCase() == "string" -> String.serializer().nullable
        typeRef.kind == Kind.SCALAR && typeRef.name?.toLowerCase() == "float" -> Float.serializer().nullable
        typeRef.kind == Kind.SCALAR && typeRef.name?.toLowerCase() == "id" -> String.serializer().nullable
        typeRef.kind == Kind.SCALAR -> String.serializer().nullable
        typeRef.kind == Kind.ENUM -> String.serializer().nullable
        typeRef.kind == Kind.LIST && typeRef.ofType != null -> ListSerializer(getSerializerFor(typeRef.ofType)).nullable
        typeRef.kind == Kind.NON_NULL && typeRef.ofType != null -> getSerializerFor(typeRef.ofType)
        else -> JsonElement.serializer().nullable
    }
