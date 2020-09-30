package com.chrynan.graphql.introspection.processor

/**
 * Retrieves an [IntrospectionSchemaProcessor] that wraps the provided [processors] and invokes all of their
 * [IntrospectionSchemaProcessor.process] functions, accumulating their results, when the returned
 * [IntrospectionSchemaProcessor.process] function is invoked.
 */
@Suppress("FunctionName")
fun <R> SchemaProcessor(processors: List<IntrospectionSchemaProcessor<R>>): IntrospectionSchemaProcessor<Map<String, R>> =
    DelegateIntrospectionSchemaProcessor(processors)

/**
 * Retrieves an [IntrospectionSchemaProcessor] that wraps the provided [processors] and invokes all of their
 * [IntrospectionSchemaProcessor.process] functions, accumulating their results, when the returned
 * [IntrospectionSchemaProcessor.process] function is invoked.
 */
@Suppress("FunctionName")
fun <R> SchemaProcessor(vararg processors: IntrospectionSchemaProcessor<R>): IntrospectionSchemaProcessor<Map<String, R>> =
    DelegateIntrospectionSchemaProcessor(processors.toList())

/**
 * Retrieves an [IntrospectionSchemaProcessor] that wraps the provided [processors] and invokes all of their
 * [IntrospectionSchemaTypeProcessor.process] functions, accumulating their results, when the returned
 * [IntrospectionSchemaProcessor.process] function is invoked.
 */
@Suppress("FunctionName")
fun <R> TypeProcessor(processors: List<IntrospectionSchemaTypeProcessor<R>>): IntrospectionSchemaProcessor<Map<String, List<R>>> =
    DelegateTypeIntrospectionSchemaProcessor(processors)

/**
 * Retrieves an [IntrospectionSchemaProcessor] that wraps the provided [processors] and invokes all of their
 * [IntrospectionSchemaTypeProcessor.process] functions, accumulating their results, when the returned
 * [IntrospectionSchemaProcessor.process] function is invoked.
 */
@Suppress("FunctionName")
fun <R> TypeProcessor(vararg processors: IntrospectionSchemaTypeProcessor<R>): IntrospectionSchemaProcessor<Map<String, List<R>>> =
    DelegateTypeIntrospectionSchemaProcessor(processors.toList())
