@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.chrynan.graphql.introspection.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = TypeRefJsonSerializer::class)
data class TypeRef(
    @SerialName(value = "kind") val kind: Kind,
    @SerialName(value = "name") val name: String? = "",
    @SerialName(value = "ofType") val ofType: TypeRef? = null
) {

    /**
     * Retrieves the nested [TypeRef] of this [TypeRef]. For instance, if this is a wrapped [TypeRef], such as, a
     * [Kind.LIST], then it would be a TypeRef of a TypeRef. This property gets the inner-most [TypeRef]. For example,
     * if this class represented a [TypeRef] of [Kind.LIST] of [Kind.NON_NULL] of a [Kind.SCALAR] ([String!]), then
     * this [rawType] property would return the [Kind.SCALAR] [TypeRef] (String).
     */
    val rawType: TypeRef = ofType?.rawType ?: this
}
