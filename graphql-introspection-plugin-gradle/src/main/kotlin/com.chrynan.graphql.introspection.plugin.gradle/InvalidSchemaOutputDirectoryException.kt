package com.chrynan.graphql.introspection.plugin.gradle

class InvalidSchemaOutputDirectoryException(directory: String? = null) :
    RuntimeException("Invalid GraphQL Schema Output Directory. directory = $directory")
