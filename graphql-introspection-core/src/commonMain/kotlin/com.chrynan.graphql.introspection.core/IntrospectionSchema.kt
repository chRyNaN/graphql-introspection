@file:Suppress("unused")

package com.chrynan.graphql.introspection.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.SerializationException

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

        /**
         * Retrieves an [IntrospectionSchema] instance from the provided JSON String [input] using the provided [json]
         * object for deserializing. This will throw a [SerializationException] if an error occurred while parsing the
         * provided JSON String [input].
         *
         * @param [input] The properly formatted JSON [String] of the [IntrospectionSchema] object.
         * @param [json] The [Json] class used to deserialize the provided [input]. This defaults to an appropriately
         * defined [Json] instance needed for the parsing. If you override this value, be sure to set the
         * classDiscriminator value to [Type.TYPE_FIELD_NAME] and it's recommended to have isLenient set to true.
         */
        fun fromJsonString(
            input: String,
            json: Json = Json {
                classDiscriminator = Type.TYPE_FIELD_NAME
                isLenient = true
            }
        ): IntrospectionSchema =
            json.decodeFromString(serializer(), input)
    }

    /**
     * Converts this [IntrospectionSchema] to a JSON formatted [String] using the provided [json] object.
     *
     * @param [json] The [Json] class used to deserialize the provided [input]. This defaults to an appropriately
     * defined [Json] instance needed for the parsing. If you override this value, be sure to set the
     * classDiscriminator value to [Type.TYPE_FIELD_NAME] and it's recommended to have isLenient set to true.
     */
    fun toJsonString(
        json: Json = Json {
            classDiscriminator = Type.TYPE_FIELD_NAME
            isLenient = true
        }
    ): String = json.encodeToString(serializer(), this)
}
