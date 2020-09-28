@file:Suppress("unused")

package com.chrynan.graphql.introspection.input.jvm

import com.chrynan.graphql.introspection.core.IntrospectionSchema
import java.io.File

private val sdlParser = GraphQLSchemaSdlInputParser()
private val jsonParser = GraphQLSchemaJsonInputParser()

/**
 * Converts the provided GraphQL SDL Files into an [IntrospectionSchema] object.
 */
fun IntrospectionSchema.Companion.fromSdlFiles(files: List<File>): IntrospectionSchema = sdlParser(files)

/**
 * Converts the provided GraphQL Schema JSON File into an [IntrospectionSchema] object.
 */
fun IntrospectionSchema.Companion.fromJsonFile(file: File): IntrospectionSchema = jsonParser(file)
