@file:Suppress("unused")

package com.chrynan.graphql.introspection.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

/**
 * The data component of a successful result of a GraphQL Introspection Query.
 */
@Serializable
data class IntrospectionSchemaData(
    @SerialName(value = "__schema") val introspectionSchema: IntrospectionSchema
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
         * classDiscriminator value to [Type.TYPE_FIELD_NAME] and it's recommended to have isLenient set to true. Also,
         * it's recommended to have encodeDefaults set to true.
         */
        fun fromJsonString(
            input: String,
            json: Json = Json {
                isLenient = true
                encodeDefaults = true
                ignoreUnknownKeys = true
            }
        ): IntrospectionSchemaData = json.decodeFromString(serializer(), input)
    }

    /**
     * Converts this [IntrospectionSchemaData] to a JSON formatted [String] using the provided [json] object.
     *
     * @param [json] The [Json] class used to deserialize this class instance. This defaults to an appropriately
     * defined [Json] instance needed for the parsing. If you override this value, be sure to set the
     * classDiscriminator value to [Type.TYPE_FIELD_NAME] and it's recommended to have isLenient set to true. Also,
     * it's recommended to have encodeDefaults set to true.
     */
    fun toJsonString(
        json: Json = Json {
            isLenient = true
            encodeDefaults = true
            ignoreUnknownKeys = true
        }
    ): String = json.encodeToString(serializer(), this)
}
