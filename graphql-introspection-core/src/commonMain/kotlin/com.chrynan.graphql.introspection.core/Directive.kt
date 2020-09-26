package com.chrynan.graphql.introspection.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Directive(
    @SerialName(value = "name") val name: String,
    @SerialName(value = "description") val description: String?,
    @SerialName(value = "locations") val locations: List<DirectiveLocation>,
    @SerialName(value = "args") val arguments: List<Argument>
)
