@file:Suppress("unused")

package com.chrynan.graphql.introspection.core

import kotlinx.serialization.Serializable

@Serializable
enum class Kind(val typeName: String) {

    ENUM(typeName = "ENUM"),
    INTERFACE(typeName = "INTERFACE"),
    OBJECT(typeName = "OBJECT"),
    INPUT_OBJECT(typeName = "INPUT_OBJECT"),
    SCALAR(typeName = "SCALAR"),
    NON_NULL(typeName = "NON_NULL"),
    LIST(typeName = "LIST"),
    UNION(typeName = "UNION");

    companion object {

        fun fromTypeName(typeName: String, ignoreCase: Boolean = false): Kind? =
            values().firstOrNull {
                if (ignoreCase) {
                    it.typeName.toLowerCase() == typeName.toLowerCase()
                } else {
                    it.typeName == typeName
                }
            }
    }
}
