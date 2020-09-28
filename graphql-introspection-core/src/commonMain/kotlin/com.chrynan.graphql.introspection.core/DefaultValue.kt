package com.chrynan.graphql.introspection.core

import kotlinx.serialization.json.JsonElement

/**
 * A [DefaultValue] is a typealias to [Any]. It can be Any type in Kotlin but the supported types are:
 * - Boolean
 * - Int
 * - Float
 * - String
 * - Enum
 * - JsonElement
 * - List of one of the above types
 *
 * When attempting to obtain the Serializer for the [DefaultValue], it will check the Kotlin type and try to match a
 * Serializer. If the Kotlin type is not one of the supported types, an exception will be thrown.
 *
 * When attempting to serialize a value into a Kotlin [DefaultValue] type, it will check the corresponding [TypeRef]
 * value for that GraphQL type, to try and resolve the type. It will fall back to a Serializer for a [JsonElement] if
 * another Kotlin type isn't detected that corresponds to it. That means any GraphQL Input Object value will be
 * represented as a [JsonElement].
 */
typealias DefaultValue = Any
