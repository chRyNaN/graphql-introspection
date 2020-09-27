@file:Suppress("unused")

package com.chrynan.graphql.introspection.core

import kotlinx.serialization.Serializable

@Serializable
enum class DirectiveLocation(val typeName: String) {

    SCHEMA(typeName = "SCHEMA"),
    QUERY(typeName = "QUERY"),
    MUTATION(typeName = "MUTATION"),
    SUBSCRIPTION(typeName = "SUBSCRIPTION"),
    SCALAR(typeName = "SCALAR"),
    OBJECT(typeName = "OBJECT"),
    FIELD_DEFINITION(typeName = "FIELD_DEFINITION"),
    ARGUMENT_DEFINITION(typeName = "ARGUMENT_DEFINITION"),
    INTERFACE(typeName = "INTERFACE"),
    UNION(typeName = "UNION"),
    ENUM(typeName = "ENUM"),
    ENUM_VALUE(typeName = "ENUM_VALUE"),
    INPUT_OBJECT(typeName = "INPUT_OBJECT"),
    INPUT_FIELD_DEFINITION(typeName = "INPUT_FIELD_DEFINITION"),
    FIELD(typeName = "FIELD"),
    FRAGMENT_DEFINITION(typeName = "FRAGMENT_DEFINITION"),
    FRAGMENT_SPREAD(typeName = "FRAGMENT"),
    INLINE_FRAGMENT(typeName = "INLINE_FRAGMENT"),
    VARIABLE_DEFINITION(typeName = "VARIABLE_DEFINITION");

    companion object {

        fun fromTypeName(typeName: String, ignoreCase: Boolean = false): DirectiveLocation? =
            values().firstOrNull {
                if (ignoreCase) {
                    it.typeName.toLowerCase() == typeName.toLowerCase()
                } else {
                    it.typeName == typeName
                }
            }
    }
}
