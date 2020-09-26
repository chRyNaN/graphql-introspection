@file:Suppress("unused")

package com.chrynan.graphql.introspection.core

object IntrospectionQuery {

    fun getIntrospectionQueryString(nestedLevelCount: Int = 7) =
        """
        |query IntrospectionQuery {
        |    __schema {
        |        queryType { name }
        |        mutationType { name }
        |        subscriptionType { name }
        |        types {
        |            ...FullType
        |        }
        |        directives {
        |            name
        |            description
        |            locations
        |            args {
        |                ...InputValue
        |            }
        |        }
        |    }
        |}
        |
        |fragment FullType on __Type {
        |    kind
        |    name
        |    description
        |    fields(includeDeprecated: true) {
        |        name
        |        description
        |        args {
        |            ...InputValue
        |        }
        |        type {
        |            ...TypeRef
        |        }
        |        isDeprecated
        |        deprecationReason
        |    }
        |    inputFields {
        |        ...InputValue
        |    }
        |    interfaces {
        |        ...TypeRef
        |    }
        |    enumValues(includeDeprecated: true) {
        |        name
        |        description
        |        isDeprecated
        |        deprecationReason
        |    }
        |    possibleTypes {
        |        ...TypeRef
        |    }
        |}
        |
        |fragment InputValue on __InputValue {
        |    name
        |    description
        |    type { ...TypeRef }
        |    defaultValue
        |}
        |
        |fragment TypeRef on __Type {
        |   kind
        |   name
        |   ${getTypeRefFragment(nestedLevelCount = nestedLevelCount)}
        |}
    """.trimMargin()

    private fun getTypeRefFragment(nestedLevelCount: Int): String = buildString {
        for (i in 0 until nestedLevelCount) {
            append(
                """
                    |ofType {
                    |   kind 
                    |   name
                    """.trimMargin()
            )
        }

        append("\n")

        for (i in 0 until nestedLevelCount) {
            append("}\n")
        }
    }
}
