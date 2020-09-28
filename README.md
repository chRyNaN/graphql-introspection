# graphql-introspection
Kotlin Multi-platform GraphQL introspection models and utilities.

With a GraphQL API, it's common to have a `schema.json` file, which is a JSON representation of a GraphQL Schema, so that Kotlin code can be generated, as the [apollo-android library](https://github.com/apollographql/apollo-android) does.

Sometimes we need to access this `schema.json` file for processing or our own code generators. This library provides a Kotlin type representation of the `schema.json` file which can easily be obtained from the JSON String using the [kotlinx serialization library](https://github.com/Kotlin/kotlinx.serialization).

**Note:** This library requires an explicit dependency on the [kotlinx serialization library](https://github.com/Kotlin/kotlinx.serialization).

### Obtaining an `IntrospectionSchema` object from a JSON String
```kotlin
val schema = IntrospectionSchema.fromJsonString(jsonString)

schema.types.forEach { ... }
```

### Convenient GraphQL Introspection Query
This can be used to query a GraphQL API and obtain the result whose `data` field can be used as an `IntrospectionSchema` object.
```kotlin
val query = IntrospectionQuery.getIntrospectionQueryString()
```

## Building
The library is provided through [Bintray](https://bintray.com/chrynan/chrynan). Checkout the [releases page](https://github.com/chRyNaN/graphql-introspection/releases) to get the latest version.

### Repository
```groovy
repositories {
    maven {
        url = uri("https://dl.bintray.com/chrynan/chrynan")
    }
}
```

### Dependencies
The library uses the new Kotlin 1.4.0 setup, so you can simply specify the common dependency for the `commonMain` source set:
```groovy
commonMain {
    dependencies {
        implementation "com.chrynan.graphql.introspection:graphql-introspection-core:$VERSION"
    }
}
```

If you need dependencies for specific targets, they are listed below:

#### Kotlin Common:
```groovy
implementation "com.chrynan.graphql.introspection:graphql-introspection-core:$VERSION"
```

#### Kotlin JVM:
```groovy
implementation "com.chrynan.graphql.introspection:graphql-introspection-core-jvm:$VERSION"
```

#### Kotlin JS:
```groovy
implementation "com.chrynan.graphql.introspection:graphql-introspection-core-js:$VERSION"
```

## License
```
Copyright 2020 chRyNaN

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
