@file:Suppress("unused")

package com.chrynan.graphql.introspection.plugin.gradle

import com.chrynan.graphql.introspection.core.IntrospectionSchema
import com.chrynan.graphql.introspection.input.jvm.fromSdlFiles
import com.chrynan.graphql.introspection.input.jvm.isGraphQLFile
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class SchemaSdlToJsonPlugin : Plugin<Project> {

    companion object {

        private const val GROUP_NAME = "graphql"
        private const val EXTENSION_NAME = "graphqlSchemaSdlToJson"
        private const val TASK_NAME = "convertGraphqlSchemaSdlToJson"
        private const val OUTPUT_FILE_NAME = "schema.json"
    }

    override fun apply(project: Project) {
        val extension = project.extensions.create(EXTENSION_NAME, SchemaSdlToJsonExtension::class.java)

        project.tasks.register(TASK_NAME) { task ->
            task.group = GROUP_NAME

            task.doFirst {
                val graphQLDirectoryPath =
                    extension.graphQLDirectory
                        ?: throw InvalidGraphQLInputDirectoryException(extension.graphQLDirectory)
                val schemaDirectoryPath =
                    extension.schemaOutputDirectory
                        ?: throw InvalidSchemaOutputDirectoryException(extension.schemaOutputDirectory)

                val graphQLDirectory = File(graphQLDirectoryPath)
                val schemaDirectory = File(schemaDirectoryPath)

                if (!graphQLDirectory.exists()) graphQLDirectory.mkdirs()
                if (!schemaDirectory.exists()) schemaDirectory.mkdirs()

                if (!graphQLDirectory.isDirectory) throw InvalidGraphQLInputDirectoryException(graphQLDirectoryPath)
                if (!schemaDirectory.isDirectory) throw InvalidSchemaOutputDirectoryException(schemaDirectoryPath)

                val sdlFiles = graphQLDirectory.listFiles()?.toList()?.filter { it.isGraphQLFile } ?: emptyList()

                val schema = IntrospectionSchema.fromSdlFiles(sdlFiles)

                val outputFile = File(graphQLDirectory, OUTPUT_FILE_NAME)

                if (!outputFile.exists()) outputFile.createNewFile()

                outputFile.writeText(schema.toJsonString())
            }
        }
    }
}
