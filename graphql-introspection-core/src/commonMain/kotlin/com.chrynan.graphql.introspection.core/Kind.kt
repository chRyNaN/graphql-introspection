@file:Suppress("unused")

package com.chrynan.graphql.introspection.core

import kotlinx.serialization.Serializable

@Serializable
enum class Kind {

    ENUM,
    INTERFACE,
    OBJECT,
    INPUT_OBJECT,
    SCALAR,
    NON_NULL,
    LIST,
    UNION
}
