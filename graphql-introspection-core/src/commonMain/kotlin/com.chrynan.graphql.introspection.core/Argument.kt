package com.chrynan.graphql.introspection.core

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Argument(
    @SerialName(value = "name") val name: String,
    @SerialName(value = "description") val description: String? = null,
    @SerialName(value = "isDeprecated") val isDeprecated: Boolean = false,
    @SerialName(value = "deprecationReason") val deprecationReason: String? = null,
    @SerialName(value = "type") val type: TypeRef,
    @SerialName(value = "defaultValue") @Contextual val defaultValue: Any? = null
)
