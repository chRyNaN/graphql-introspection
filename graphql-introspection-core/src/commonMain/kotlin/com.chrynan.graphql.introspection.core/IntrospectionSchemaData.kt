package com.chrynan.graphql.introspection.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The data component of a successful result of a GraphQL Introspection Query.
 */
@Serializable
data class IntrospectionSchemaData(
    @SerialName(value = "__schema") val introspectionSchema: IntrospectionSchema
)
