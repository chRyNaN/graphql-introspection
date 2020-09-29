@file:Suppress("unused")

package com.chrynan.graphql.introspection.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = TypeJsonSerializer::class)
sealed class Type {

    companion object {

        const val TYPE_FIELD_NAME = "kind"
    }

    @SerialName(value = "kind")
    abstract val kind: Kind

    @SerialName(value = "name")
    abstract val name: String

    @SerialName(value = "description")
    abstract val description: String?

    @Serializable
    data class Scalar(
        override val name: String,
        override val description: String? = null
    ) : Type() {

        override val kind: Kind = Kind.SCALAR
    }

    @Serializable
    data class Object(
        override val name: String,
        override val description: String? = null,
        @SerialName(value = "interfaces") val interfaces: List<TypeRef> = emptyList(),
        @SerialName(value = "fields") val fields: List<Field> = emptyList()
    ) : Type() {

        override val kind: Kind = Kind.OBJECT
    }

    @Serializable
    data class Interface(
        override val name: String,
        override val description: String? = null,
        @SerialName(value = "possibleTypes") val possibleTypes: List<TypeRef>? = null,
        @SerialName(value = "interfaces") val interfaces: List<TypeRef> = emptyList(),
        @SerialName(value = "fields") val fields: List<Field> = emptyList()
    ) : Type() {

        override val kind: Kind = Kind.INTERFACE
    }

    @Serializable
    data class Union(
        override val name: String,
        override val description: String? = null,
        @SerialName(value = "possibleTypes") val possibleTypes: List<TypeRef>? = null,
        @SerialName(value = "fields") val fields: List<Field> = emptyList()
    ) : Type() {

        override val kind: Kind = Kind.UNION
    }

    @Serializable
    data class Enum(
        override val name: String,
        override val description: String? = null,
        @SerialName(value = "enumValues") val enumValues: List<Value> = emptyList()
    ) : Type() {

        override val kind: Kind = Kind.ENUM

        @Serializable
        data class Value(
            @SerialName(value = "name") val name: String,
            @SerialName(value = "description") val description: String? = null,
            @SerialName(value = "isDeprecated") val isDeprecated: Boolean = false,
            @SerialName(value = "deprecationReason") val deprecationReason: String? = null
        )
    }

    @Serializable
    data class InputObject(
        override val name: String,
        override val description: String? = null,
        @SerialName(value = "inputFields") val inputFields: List<InputField> = emptyList()
    ) : Type() {

        override val kind: Kind = Kind.INPUT_OBJECT
    }
}
