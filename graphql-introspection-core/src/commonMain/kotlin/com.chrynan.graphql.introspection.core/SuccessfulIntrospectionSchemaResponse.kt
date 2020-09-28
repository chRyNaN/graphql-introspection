@file:Suppress("unused")

package com.chrynan.graphql.introspection.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

/**
 * The result of a successful Introspection Query of a GraphQL Schema. Note that there is no errors property, this is
 * because this should represent a successful result. Any errors will be excluded and if there is a partial response,
 * an exception may be thrown when serializing this class from JSON. It might be preferable to use the platform or
 * library specific version of a GraphQL Response instead of this class, so that errors can be properly evaluated.
 */
@Serializable
data class SuccessfulIntrospectionSchemaResponse(
    @SerialName(value = "data") val data: IntrospectionSchemaData
) {

    companion object {

        /**
         * Retrieves a [SuccessfulIntrospectionSchemaResponse] instance from the provided JSON String [input] using the
         * provided [json] object for deserializing. This will throw a [SerializationException] if an error occurred
         * while parsing the provided JSON String [input].
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
                classDiscriminator = Type.TYPE_FIELD_NAME
                isLenient = true
                encodeDefaults = true
            }
        ): SuccessfulIntrospectionSchemaResponse = json.decodeFromString(serializer(), input)
    }

    /**
     * Converts this [SuccessfulIntrospectionSchemaResponse] to a JSON formatted [String] using the provided [json]
     * object.
     *
     * @param [json] The [Json] class used to deserialize this class instance. This defaults to an appropriately
     * defined [Json] instance needed for the parsing. If you override this value, be sure to set the
     * classDiscriminator value to [Type.TYPE_FIELD_NAME] and it's recommended to have isLenient set to true. Also,
     * it's recommended to have encodeDefaults set to true.
     */
    fun toJsonString(
        json: Json = Json {
            classDiscriminator = Type.TYPE_FIELD_NAME
            isLenient = true
            encodeDefaults = true
        }
    ): String = json.encodeToString(serializer(), this)
}
