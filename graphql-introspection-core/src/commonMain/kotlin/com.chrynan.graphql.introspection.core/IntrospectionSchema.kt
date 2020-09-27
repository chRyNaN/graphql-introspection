@file:Suppress("unused")

package com.chrynan.graphql.introspection.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/**
 * Represents the schema.json file as a Kotlin Type.
 */
@Serializable
data class IntrospectionSchema(
    @SerialName(value = "queryType") val queryType: QueryType? = null,
    @SerialName(value = "mutationType") val mutationType: MutationType? = null,
    @SerialName(value = "subscriptionType") val subscriptionType: SubscriptionType? = null,
    @SerialName(value = "types") val types: List<Type> = emptyList(),
    @SerialName(value = "directives") val directives: List<Directive> = emptyList()
) {

    companion object {

        fun fromJsonString(
            input: String,
            json: Json = Json { classDiscriminator = Type.TYPE_FIELD_NAME }
        ): IntrospectionSchema =
            json.decodeFromString(serializer(), input)
    }

    fun toJsonString(json: Json = Json): String = json.encodeToString(serializer(), this)
}
