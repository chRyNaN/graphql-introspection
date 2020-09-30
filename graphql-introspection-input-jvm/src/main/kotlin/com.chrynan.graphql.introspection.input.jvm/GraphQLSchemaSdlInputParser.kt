@file:Suppress("unused")

package com.chrynan.graphql.introspection.input.jvm

import com.chrynan.graphql.introspection.core.*
import graphql.GraphQL
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import graphql.schema.idl.TypeDefinitionRegistry
import java.io.File

class GraphQLSchemaSdlInputParser {

    operator fun invoke(sdlFiles: List<File>): IntrospectionSchema {
        val mockRuntimeWiring = RuntimeWiring.newRuntimeWiring()
            .wiringFactory(MockedWiringFactory())
            .build()
        val schemaParser = SchemaParser()
        val typeRegistry = TypeDefinitionRegistry()

        sdlFiles.onEach { if (!it.isGraphQLFile) throw InvalidGraphQLSdlFile(message = "GraphQL File did not end in correct file suffix. Make sure the file is an .sdl, .sdls, .graphql, .graphqls, .gql, or .gqls File.") }
            .forEach { typeRegistry.merge(schemaParser.parse(it)) }

        val schema = SchemaGenerator().makeExecutableSchema(typeRegistry, mockRuntimeWiring)
        val graphql = GraphQL.newGraphQL(schema).build()
        val executionResult = graphql.execute(IntrospectionQuery.getIntrospectionQueryString())

        val jsonElement = executionResult.toSpecification().toJson()

        var jsonString = jsonElement.toString()

        jsonString = if (jsonString.startsWith("/uFEFF")) jsonString.substring(1) else jsonString

        println("Full: $jsonString\n")

        return try {
            val schemaResult = SuccessfulIntrospectionSchemaResponse.fromJsonString(jsonString).data.introspectionSchema

            println("SchemaResult = $schemaResult")

            val schemaResultString = schemaResult.toJsonString()

            println("\nSchemaResultString\n = $schemaResultString")

            IntrospectionSchema.fromJsonString(schemaResultString)

            println("After re-parse")

            schemaResult
        } catch (e: Exception) {
            throw e
            //throw InvalidGraphQLSchemaFormat(
            //   message = "Encountered exception while parsing the IntrospectionSchemaData class.",
            //    exception = e
            //)
        }
    }
}
