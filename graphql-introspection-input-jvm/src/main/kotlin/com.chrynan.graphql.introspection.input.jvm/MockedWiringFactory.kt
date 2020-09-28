package com.chrynan.graphql.introspection.input.jvm

import graphql.schema.Coercing
import graphql.schema.GraphQLScalarType
import graphql.schema.idl.EchoingWiringFactory
import graphql.schema.idl.ScalarInfo
import graphql.schema.idl.ScalarWiringEnvironment

internal class MockedWiringFactory : EchoingWiringFactory() {

    override fun providesScalar(environment: ScalarWiringEnvironment): Boolean =
        !ScalarInfo.isGraphqlSpecifiedScalar(environment.scalarTypeDefinition.name)

    override fun getScalar(environment: ScalarWiringEnvironment): GraphQLScalarType =
        GraphQLScalarType.newScalar()
            .name(environment.scalarTypeDefinition.name)
            .coercing(object : Coercing<Any, Any> {

                override fun parseValue(input: Any): Any =
                    throw UnsupportedOperationException("parseValue() function is not implemented.")

                override fun parseLiteral(input: Any): Any =
                    throw UnsupportedOperationException("parseLiteral() function is not implemented.")

                override fun serialize(dataFetcherResult: Any): Any =
                    throw UnsupportedOperationException("serialize() function is not implemented.")
            })
            .build()
}
