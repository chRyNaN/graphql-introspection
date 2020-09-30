package com.chrynan.graphql.introspection.processor

import com.chrynan.graphql.introspection.core.IntrospectionSchema

/**
 * An implementation of [IntrospectionSchemaProcessor] that delegates to multiple [processors] and aggregates their
 * results.
 */
class DelegateIntrospectionSchemaProcessor<R>(
    private val processors: List<IntrospectionSchemaProcessor<R>>
) : IntrospectionSchemaProcessor<Map<String, R>> {

    override val id: String
        get() = "${toString()}:${hashCode()}"

    override fun process(schema: IntrospectionSchema): Map<String, R> =
        processors.associate { it.id to it.process(schema) }

    override fun toString(): String = "DelegateIntrospectionSchemaProcessor:$processors"

    override fun hashCode(): Int = processors.hashCode()

    override fun equals(other: Any?): Boolean {
        if (other == null) return false

        if (other !is DelegateIntrospectionSchemaProcessor<*>) return false

        return processors == other.processors
    }
}
