package com.chrynan.graphql.introspection.input.jvm

import java.io.File

/**
 * Determines if this is [File] that ends in a supported GraphQL pathname suffix. Supported GraphQL File suffixes are:
 * .graphql, .graphqls, .gql, .gqls, .sdl, .sdls.
 */
val File.isGraphQLFile: Boolean
    get() = isFile && (path.endsWith(".graphql") ||
            path.endsWith(".graphqls") ||
            path.endsWith(".gql") ||
            path.endsWith(".gqls") ||
            path.endsWith(".sdl") ||
            path.endsWith(".sdls"))
