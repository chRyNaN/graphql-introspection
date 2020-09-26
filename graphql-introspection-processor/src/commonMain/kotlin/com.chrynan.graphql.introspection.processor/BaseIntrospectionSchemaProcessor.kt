@file:Suppress("MemberVisibilityCanBePrivate")

package com.chrynan.graphql.introspection.processor

import com.chrynan.graphql.introspection.core.IntrospectionSchema
import com.chrynan.graphql.introspection.core.typeMap

/**
 * A base implementation of the [IntrospectionSchemaProcessor] that runs the provided [typeProcessors] for every call
 * to [process] and returns their results.
 */
open class BaseIntrospectionSchemaProcessor<R>(
    protected val typeProcessors: Collection<IntrospectionSchemaTypeProcessor<R>>
) : IntrospectionSchemaProcessor<List<List<R>>> {

    override fun process(schema: IntrospectionSchema): List<List<R>> {
        val typeMap = schema.typeMap

        return schema.types.map {
            typeProcessors.map { processor -> processor.process(it, typeMap) }
        }
    }
}
