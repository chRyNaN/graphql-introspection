package com.chrynan.graphql.introspection.input.jvm

import kotlinx.serialization.json.*

internal fun Map<*, *>.toJson(): JsonObject =
    buildJsonObject {
        entries.forEach { (key, value) ->
            if (key != null) {
                when (value) {
                    is Map<*, *> -> putJsonObject(key.toString(), value)
                    is Collection<*> -> putJsonArray(key.toString(), value)
                    is Array<*> -> putJsonArray(key.toString(), value.toList())
                    is JsonElement -> put(key.toString(), value)
                    is Number -> put(key.toString(), JsonPrimitive(value))
                    is Boolean -> put(key.toString(), JsonPrimitive(value))
                    is String -> put(key.toString(), JsonPrimitive(value))
                    null -> put(key.toString(), JsonNull)
                    else -> put(key.toString(), JsonPrimitive(value.toString()))
                }
            }
        }
    }

private fun JsonObjectBuilder.putJsonObject(wrapperKey: String, map: Map<*, *>) {
    putJsonObject(wrapperKey) {
        map.entries.forEach { (key, value) ->
            if (key != null) {
                when (value) {
                    is Map<*, *> -> putJsonObject(key.toString(), value)
                    is Collection<*> -> putJsonArray(key.toString(), value)
                    is Array<*> -> putJsonArray(key.toString(), value.toList())
                    is JsonElement -> put(key.toString(), value)
                    is Number -> put(key.toString(), JsonPrimitive(value))
                    is Boolean -> put(key.toString(), JsonPrimitive(value))
                    is String -> put(key.toString(), JsonPrimitive(value))
                    null -> put(key.toString(), JsonNull)
                    else -> put(key.toString(), JsonPrimitive(value.toString()))
                }
            }
        }
    }
}

private fun JsonArrayBuilder.addJsonObject(map: Map<*, *>) {
    addJsonObject {
        map.entries.forEach { (key, value) ->
            if (key != null) {
                when (value) {
                    is Map<*, *> -> putJsonObject(key.toString(), value)
                    is Collection<*> -> putJsonArray(key.toString(), value)
                    is Array<*> -> putJsonArray(key.toString(), value.toList())
                    is JsonElement -> put(key.toString(), value)
                    is Number -> put(key.toString(), JsonPrimitive(value))
                    is Boolean -> put(key.toString(), JsonPrimitive(value))
                    is String -> put(key.toString(), JsonPrimitive(value))
                    null -> put(key.toString(), JsonNull)
                    else -> put(key.toString(), JsonPrimitive(value.toString()))
                }
            }
        }
    }
}

private fun JsonObjectBuilder.putJsonArray(key: String, items: Collection<*>) {
    putJsonArray(key) {
        items.forEach {
            when (it) {
                is Map<*, *> -> addJsonObject(it)
                is Collection<*> -> addJsonArray(it)
                is Array<*> -> addJsonArray(it.toList())
                is JsonElement -> add(it)
                is Number -> add(JsonPrimitive(it))
                is Boolean -> add(JsonPrimitive(it))
                is String -> add(JsonPrimitive(it))
                null -> add(JsonNull)
                else -> add(JsonPrimitive(it.toString()))
            }
        }
    }
}

private fun JsonArrayBuilder.addJsonArray(items: Collection<*>) {
    addJsonArray {
        items.forEach {
            when (it) {
                is Map<*, *> -> addJsonObject(it)
                is Collection<*> -> addJsonArray(it)
                is Array<*> -> addJsonArray(it.toList())
                is JsonElement -> add(it)
                is Number -> add(JsonPrimitive(it))
                is Boolean -> add(JsonPrimitive(it))
                is String -> add(JsonPrimitive(it))
                null -> add(JsonNull)
                else -> add(JsonPrimitive(it.toString()))
            }
        }
    }
}
