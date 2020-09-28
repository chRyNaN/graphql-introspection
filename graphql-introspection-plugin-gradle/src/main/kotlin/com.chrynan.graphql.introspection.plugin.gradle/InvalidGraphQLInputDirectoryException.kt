package com.chrynan.graphql.introspection.plugin.gradle

class InvalidGraphQLInputDirectoryException(directory: String? = null) :
    RuntimeException("Invalid GraphQL Input Directory. directory = $directory")
