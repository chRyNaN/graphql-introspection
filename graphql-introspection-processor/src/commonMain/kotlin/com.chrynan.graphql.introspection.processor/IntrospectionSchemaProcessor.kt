@file:Suppress("unused")

package com.chrynan.graphql.introspection.processor

import com.chrynan.graphql.introspection.core.IntrospectionSchema

/**
 * An interface for a class that processes an [IntrospectionSchema] and performs some task dependent on the
 * [IntrospectionSchema].
 */
interface IntrospectionSchemaProcessor<R> {

    /**
     * The unique name identifier for this [IntrospectionSchemaProcessor].
     */
    val id: String

    /**
     * Processes the provided [schema]. Depending on the implementation, this can invoke different actions including
     * generating code. Returns the result [R] of the processing.
     */
    fun process(schema: IntrospectionSchema): R

    /**
     * A convenience operator function for calling [process].
     */
    operator fun invoke(schema: IntrospectionSchema): R = process(schema)
}
