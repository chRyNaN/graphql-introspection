package com.chrynan.graphql.introspection.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MutationType(@SerialName(value = "name") val name: String)
