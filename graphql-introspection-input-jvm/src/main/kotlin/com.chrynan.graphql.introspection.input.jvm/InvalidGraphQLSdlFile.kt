package com.chrynan.graphql.introspection.input.jvm

class InvalidGraphQLSdlFile(message: String) :
    RuntimeException("The provided File is not a valid GraphQL SDL File. $message")
