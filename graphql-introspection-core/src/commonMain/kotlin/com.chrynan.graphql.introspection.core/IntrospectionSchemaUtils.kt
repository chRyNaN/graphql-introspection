@file:Suppress("unused")

package com.chrynan.graphql.introspection.core

/**
 * Retrieves a [Map] where the keys are the [Type.name] and the values are the [Type], for all of the [Type]s that
 * belong to this [IntrospectionSchema].
 */
val IntrospectionSchema.typeMap: Map<String, Type>
    get() = types.associateBy { it.name }
