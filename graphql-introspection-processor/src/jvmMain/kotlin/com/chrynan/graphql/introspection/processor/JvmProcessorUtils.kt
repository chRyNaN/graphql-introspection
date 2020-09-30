package com.chrynan.graphql.introspection.processor

import com.chrynan.graphql.introspection.core.IntrospectionSchema
import com.chrynan.graphql.introspection.input.jvm.fromJsonFile
import com.chrynan.graphql.introspection.input.jvm.fromSdlFiles
import java.io.File

/**
 * Processes the [IntrospectionSchema] retrieved from the provided GraphQL SDL [files].
 */
fun <R> IntrospectionSchemaProcessor<R>.processSdlFiles(files: List<File>): R {
    val schema = IntrospectionSchema.fromSdlFiles(files)

    return process(schema)
}

/**
 * Processes the [IntrospectionSchema] retrieved from the provided GraphQL Schema JSON [file].
 */
fun <R> IntrospectionSchemaProcessor<R>.processJsonFile(file: File): R {
    val schema = IntrospectionSchema.fromJsonFile(file)

    return process(schema)
}
