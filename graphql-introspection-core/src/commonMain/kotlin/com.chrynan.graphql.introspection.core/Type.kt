@file:Suppress("unused")

package com.chrynan.graphql.introspection.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class Type(@SerialName(value = "kind") val kind: Kind) {

    @SerialName(value = "name")
    abstract val name: String

    @SerialName(value = "description")
    abstract val description: String?

    @Serializable
    data class Scalar(
        override val name: String,
        override val description: String? = null
    ) : Type(Kind.SCALAR)

    @Serializable
    data class Object(
        override val name: String,
        override val description: String? = null,
        @SerialName(value = "fields") val fields: List<Field>? = null,
        @SerialName(value = "interfaces") val interfaces: List<TypeRef> = emptyList()
    ) : Type(Kind.OBJECT)

    @Serializable
    data class Interface(
        override val name: String,
        override val description: String? = null,
        @SerialName(value = "fields") val fields: List<Field>? = null,
        @SerialName(value = "possibleTypes") val possibleTypes: List<TypeRef>? = null
    ) : Type(Kind.INTERFACE)

    @Serializable
    data class Union(
        override val name: String,
        override val description: String? = null,
        @SerialName(value = "fields") val fields: List<Field>? = null,
        @SerialName(value = "possibleTypes") val possibleTypes: List<TypeRef>? = null
    ) : Type(Kind.UNION)

    @Serializable
    data class Enum(
        override val name: String,
        override val description: String? = null,
        @SerialName(value = "enumValues") val enumValues: List<Value> = emptyList()
    ) : Type(Kind.ENUM) {

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
    ) : Type(Kind.INPUT_OBJECT)
}
