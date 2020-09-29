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

    @SerialName(value = "possibleTypes")
    abstract val possibleTypes: List<TypeRef>?

    @SerialName(value = "enumValues")
    abstract val enumValues: List<Enum.Value>?

    @SerialName(value = "inputFields")
    abstract val inputFields: List<InputField>?

    @SerialName(value = "interfaces")
    abstract val interfaces: List<TypeRef>?

    @SerialName(value = "fields")
    abstract val fields: List<Field>?


    @Serializable
    @SerialName(value = "SCALAR") // Refers to the kind property
    data class Scalar(
        override val name: String,
        override val description: String? = null
    ) : Type() {

        override val kind: Kind = Kind.SCALAR

        override val possibleTypes: List<TypeRef>? = null

        override val enumValues: List<Enum.Value>? = null

        override val inputFields: List<InputField>? = null

        override val interfaces: List<TypeRef>? = null

        override val fields: List<Field>? = null
    }

    @Serializable
    @SerialName(value = "OBJECT") // Refers to the kind property
    data class Object(
        override val name: String,
        override val description: String? = null,
        override val interfaces: List<TypeRef> = emptyList(),
        override val fields: List<Field>? = null
    ) : Type() {

        override val kind: Kind = Kind.OBJECT

        override val possibleTypes: List<TypeRef>? = null

        override val enumValues: List<Enum.Value>? = null

        override val inputFields: List<InputField>? = null
    }

    @Serializable
    @SerialName(value = "INTERFACE") // Refers to the kind property
    data class Interface(
        override val name: String,
        override val description: String? = null,
        override val possibleTypes: List<TypeRef>? = null,
        override val interfaces: List<TypeRef> = emptyList(),
        override val fields: List<Field>? = null
    ) : Type() {

        override val kind: Kind = Kind.INTERFACE

        override val enumValues: List<Enum.Value>? = null

        override val inputFields: List<InputField>? = null
    }

    @Serializable
    @SerialName(value = "UNION") // Refers to the kind property
    data class Union(
        override val name: String,
        override val description: String? = null,
        override val possibleTypes: List<TypeRef>? = null,
        override val fields: List<Field>? = null
    ) : Type() {

        override val kind: Kind = Kind.UNION

        override val enumValues: List<Enum.Value>? = null

        override val inputFields: List<InputField>? = null

        override val interfaces: List<TypeRef>? = null
    }

    @Serializable
    @SerialName(value = "ENUM") // Refers to the kind property
    data class Enum(
        override val name: String,
        override val description: String? = null,
        override val enumValues: List<Value> = emptyList()
    ) : Type() {

        override val kind: Kind = Kind.ENUM

        override val possibleTypes: List<TypeRef>? = null

        override val inputFields: List<InputField>? = null

        override val interfaces: List<TypeRef>? = null

        override val fields: List<Field>? = null

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
        override val inputFields: List<InputField> = emptyList()
    ) : Type() {

        override val kind: Kind = Kind.INPUT_OBJECT

        override val possibleTypes: List<TypeRef>? = null

        override val enumValues: List<Enum.Value>? = null

        override val interfaces: List<TypeRef>? = null

        override val fields: List<Field>? = null
    }
}
