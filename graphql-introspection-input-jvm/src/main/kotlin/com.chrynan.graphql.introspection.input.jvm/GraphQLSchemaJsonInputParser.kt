@file:Suppress("unused")

package com.chrynan.graphql.introspection.input.jvm

import com.chrynan.graphql.introspection.core.IntrospectionSchema
import com.chrynan.graphql.introspection.core.IntrospectionSchemaData
import com.chrynan.graphql.introspection.core.SuccessfulIntrospectionSchemaResponse
import java.io.File

class GraphQLSchemaJsonInputParser {

    operator fun invoke(schemaFile: File): IntrospectionSchema {
        val json = schemaFile.readText()

        return try {
            SuccessfulIntrospectionSchemaResponse.fromJsonString(json).data.introspectionSchema
        } catch (e: Exception) {
            try {
                IntrospectionSchemaData.fromJsonString(json).introspectionSchema
            } catch (e: Exception) {
                try {
                    IntrospectionSchema.fromJsonString(json)
                } catch (e: Exception) {
                    throw InvalidGraphQLSchemaFormat(
                        message = "Encountered exception while parsing the Introspection Schema.",
                        exception = e
                    )
                }
            }
        }
    }
}
