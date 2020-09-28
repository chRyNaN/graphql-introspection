package com.chrynan.graphql.introspection.input.jvm

class InvalidGraphQLSchemaFormat(
    message: String? = null,
    exception: Exception? = null
) : RuntimeException("Invalid GraphQL Introspection Schema format. $message\n\n $exception")
