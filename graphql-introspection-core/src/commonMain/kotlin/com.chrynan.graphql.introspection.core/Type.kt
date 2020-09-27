@file:Suppress("unused")

package com.chrynan.graphql.introspection.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
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
    @SerialName(value = "SCALAR") // Refers to the kind property
    data class Scalar(
        override val name: String,
        override val description: String? = null
    ) : Type() {

        override val kind: Kind = Kind.SCALAR
    }

    @Serializable
    @SerialName(value = "OBJECT") // Refers to the kind property
    data class Object(
        override val name: String,
        override val description: String? = null,
        @SerialName(value = "fields") val fields: List<Field>? = null,
        @SerialName(value = "interfaces") val interfaces: List<TypeRef> = emptyList()
    ) : Type() {

        override val kind: Kind = Kind.OBJECT
    }

    @Serializable
    @SerialName(value = "INTERFACE") // Refers to the kind property
    data class Interface(
        override val name: String,
        override val description: String? = null,
        @SerialName(value = "fields") val fields: List<Field>? = null,
        @SerialName(value = "possibleTypes") val possibleTypes: List<TypeRef>? = null,
        @SerialName(value = "interfaces") val interfaces: List<TypeRef> = emptyList()
    ) : Type() {

        override val kind: Kind = Kind.INTERFACE
    }

    @Serializable
    @SerialName(value = "UNION") // Refers to the kind property
    data class Union(
        override val name: String,
        override val description: String? = null,
        @SerialName(value = "fields") val fields: List<Field>? = null,
        @SerialName(value = "possibleTypes") val possibleTypes: List<TypeRef>? = null
    ) : Type() {

        override val kind: Kind = Kind.UNION
    }

    @Serializable
    @SerialName(value = "ENUM") // Refers to the kind property
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
    @SerialName(value = "INPUT_OBJECT") // Refers to the kind property
    data class InputObject(
        override val name: String,
        override val description: String? = null,
        @SerialName(value = "inputFields") val inputFields: List<InputField> = emptyList()
    ) : Type() {

        override val kind: Kind = Kind.INPUT_OBJECT
    }
}
