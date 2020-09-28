package com.chrynan.graphql.introspection.input.jvm

import java.io.File

val File.isGraphQLFile: Boolean
    get() = isFile && (path.endsWith(".graphql") ||
            path.endsWith(".graphqls") ||
            path.endsWith(".gql") ||
            path.endsWith(".gqls") ||
            path.endsWith(".sdl") ||
            path.endsWith(".sdls"))
