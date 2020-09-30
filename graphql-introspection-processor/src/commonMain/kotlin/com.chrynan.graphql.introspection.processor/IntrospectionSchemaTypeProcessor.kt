@file:Suppress("unused")

package com.chrynan.graphql.introspection.processor

import com.chrynan.graphql.introspection.core.Type

/**
 * Processes a [Type] in the [process] function and performs some operation, such as, generating a Kotlin class from
 * the type. This differs from the [IntrospectionSchemaProcessor] interface in that this interface operates on a single
 * [Type] at a time.
 */
interface IntrospectionSchemaTypeProcessor<R> {

    /**
     * The unique name identifier for this [IntrospectionSchemaProcessor].
     */
    val id: String

    /**
     * Processes the provided [type] using the provided [typeMap] which represents all of the available types for a
     * schema.
     */
    fun process(type: Type, typeMap: Map<String, Type>): R

    /**
     * A convenience operator function for calling [process].
     */
    operator fun invoke(type: Type, typeMap: Map<String, Type>): R = process(type, typeMap)
}
