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

        return try {
            SuccessfulIntrospectionSchemaResponse.fromJsonString(jsonElement.toString()).data.introspectionSchema
        } catch (e: Exception) {
            throw InvalidGraphQLSchemaFormat(
                message = "Encountered exception while parsing the IntrospectionSchemaData class.",
                exception = e
            )
        }
    }
}
