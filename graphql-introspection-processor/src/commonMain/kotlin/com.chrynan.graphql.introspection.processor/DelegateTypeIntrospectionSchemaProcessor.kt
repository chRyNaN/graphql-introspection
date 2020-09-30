@file:Suppress("MemberVisibilityCanBePrivate")

package com.chrynan.graphql.introspection.processor

import com.chrynan.graphql.introspection.core.IntrospectionSchema
import com.chrynan.graphql.introspection.core.typeMap

/**
 * A base implementation of the [IntrospectionSchemaProcessor] that runs the provided [typeProcessors] for every call
 * to [process] and returns their results.
 */
class DelegateTypeIntrospectionSchemaProcessor<R>(
    private val typeProcessors: Collection<IntrospectionSchemaTypeProcessor<R>>
) : IntrospectionSchemaProcessor<Map<String, List<R>>> {

    override val id: String
        get() = "${toString()}:${hashCode()}"

    override fun process(schema: IntrospectionSchema): Map<String, List<R>> {
        val typeMap = schema.typeMap

        return typeProcessors.associate { it.id to schema.types.map { type -> it.process(type, typeMap) } }
    }

    override fun toString(): String = "DelegateTypeIntrospectionSchemaProcessor:$typeProcessors"

    override fun hashCode(): Int = typeProcessors.hashCode()

    override fun equals(other: Any?): Boolean {
        if (other == null) return false

        if (other !is DelegateTypeIntrospectionSchemaProcessor<*>) return false

        return typeProcessors == other.typeProcessors
    }
}
