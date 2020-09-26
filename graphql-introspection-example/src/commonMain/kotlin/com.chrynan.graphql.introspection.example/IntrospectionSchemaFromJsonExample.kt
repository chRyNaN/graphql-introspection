package com.chrynan.graphql.introspection.example

import com.chrynan.graphql.introspection.core.IntrospectionSchema
import com.chrynan.graphql.introspection.core.Type

fun main() {
    val schema = IntrospectionSchema.fromJsonString(SCHEMA_JSON_STRING)

    schema.types.forEach {
        it.kind
        it.name
        it.description

        when (it) {
            is Type.Scalar -> {
            }
            is Type.Enum -> {
            }
            is Type.InputObject -> {
            }
            is Type.Interface -> {
            }
            is Type.Object -> {
            }
            is Type.Union -> {
            }
        }
    }
}

val SCHEMA_JSON_STRING = """
    {"data":{"__schema":{"types":[{"name":"Ad","interfaces":[{"name":"Node","kind":"INTERFACE"},{"name":"TimeDetail","kind":"INTERFACE"}],"fields":[{"name":"id","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"ID","kind":"SCALAR"}}},{"name":"created","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"DateTime","kind":"SCALAR"}}},{"name":"lastUpdated","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"DateTime","kind":"SCALAR"}}},{"name":"posted","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"DateTime","kind":"SCALAR"}}},{"name":"title","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"String","kind":"SCALAR"}}},{"name":"description","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"String","kind":"SCALAR"}}},{"name":"companyName","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"String","kind":"SCALAR"}}},{"name":"companyWebsiteUri","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"String","kind":"SCALAR"}}},{"name":"contentUri","args":[],"isDeprecated":false,"type":{"name":"UriString","kind":"SCALAR"}},{"name":"linkUri","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"UriString","kind":"SCALAR"}}}],"kind":"OBJECT"},{"name":"AdFeedItem","interfaces":[],"fields":[{"name":"advertisement","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Ad","kind":"OBJECT"}}}],"kind":"OBJECT"},{"name":"Boolean","description":"Built-in Boolean","kind":"SCALAR"},{"name":"Connection","interfaces":[],"possibleTypes":[{"name":"MemeReactionConnection","kind":"OBJECT"}],"fields":[{"name":"totalCount","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Long","kind":"SCALAR"}}},{"name":"pageInfo","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"PageInfo","kind":"OBJECT"}}},{"name":"edges","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"kind":"LIST","ofType":{"kind":"NON_NULL","ofType":{"name":"Edge","kind":"INTERFACE"}}}}},{"name":"nodes","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"kind":"LIST","ofType":{"kind":"NON_NULL","ofType":{"name":"Node","kind":"INTERFACE"}}}}}],"kind":"INTERFACE"},{"name":"Cursor","kind":"SCALAR"},{"name":"DateTime","kind":"SCALAR"},{"name":"Duration","interfaces":[],"fields":[{"name":"totalMilliseconds","args":[],"isDeprecated":false,"type":{"name":"Long","kind":"SCALAR"}}],"kind":"OBJECT"},{"name":"Edge","interfaces":[],"possibleTypes":[{"name":"MemeReactionEdge","kind":"OBJECT"}],"fields":[{"name":"cursor","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Cursor","kind":"SCALAR"}}},{"name":"node","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Node","kind":"INTERFACE"}}}],"kind":"INTERFACE"},{"name":"FeedItem","possibleTypes":[{"name":"MemeFeedItem","kind":"OBJECT"},{"name":"ReMemeFeedItem","kind":"OBJECT"},{"name":"AdFeedItem","kind":"OBJECT"}],"kind":"UNION"},{"name":"FeedItemConnection","interfaces":[],"fields":[{"name":"pageInfo","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"PageInfo","kind":"OBJECT"}}},{"name":"totalCount","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Long","kind":"SCALAR"}}},{"name":"edges","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"kind":"LIST","ofType":{"kind":"NON_NULL","ofType":{"name":"FeedItemEdge","kind":"OBJECT"}}}}},{"name":"nodes","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"kind":"LIST","ofType":{"kind":"NON_NULL","ofType":{"name":"FeedItem","kind":"UNION"}}}}}],"kind":"OBJECT"},{"name":"FeedItemEdge","interfaces":[],"fields":[{"name":"node","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"FeedItem","kind":"UNION"}}},{"name":"cursor","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Cursor","kind":"SCALAR"}}}],"kind":"OBJECT"},{"name":"ID","description":"Built-in ID","kind":"SCALAR"},{"name":"Int","description":"Built-in Int","kind":"SCALAR"},{"name":"Long","kind":"SCALAR"},{"name":"MarkdownString","kind":"SCALAR"},{"name":"Meme","interfaces":[{"name":"Node","kind":"INTERFACE"},{"name":"TimeDetail","kind":"INTERFACE"}],"fields":[{"name":"id","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"ID","kind":"SCALAR"}}},{"name":"created","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"DateTime","kind":"SCALAR"}}},{"name":"lastUpdated","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"DateTime","kind":"SCALAR"}}},{"name":"posted","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"DateTime","kind":"SCALAR"}}},{"name":"title","args":[],"isDeprecated":false,"type":{"name":"String","kind":"SCALAR"}},{"name":"description","args":[],"isDeprecated":false,"type":{"name":"String","kind":"SCALAR"}},{"name":"type","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"MemeType","kind":"ENUM"}}},{"name":"category","args":[],"isDeprecated":false,"type":{"name":"String","kind":"SCALAR"}},{"name":"tags","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"kind":"LIST","ofType":{"kind":"NON_NULL","ofType":{"name":"String","kind":"SCALAR"}}}}},{"name":"contentUri","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"UriString","kind":"SCALAR"}}},{"name":"shareUri","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"UriString","kind":"SCALAR"}}},{"name":"isPromoted","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Boolean","kind":"SCALAR"}}},{"name":"isFlaggedByViewer","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Boolean","kind":"SCALAR"}}},{"name":"reactions","args":[{"name":"take","type":{"kind":"NON_NULL","ofType":{"name":"Int","kind":"SCALAR"}}},{"name":"after","type":{"name":"Cursor","kind":"SCALAR"}}],"isDeprecated":false,"type":{"name":"MemeReactionConnection","kind":"OBJECT"}}],"kind":"OBJECT"},{"name":"MemeFeedItem","interfaces":[],"fields":[{"name":"meme","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Meme","kind":"OBJECT"}}},{"name":"user","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"User","kind":"OBJECT"}}}],"kind":"OBJECT"},{"name":"MemeReaction","interfaces":[{"name":"Node","kind":"INTERFACE"}],"fields":[{"name":"id","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"ID","kind":"SCALAR"}}},{"name":"unicode","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"String","kind":"SCALAR"}}},{"name":"count","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Long","kind":"SCALAR"}}},{"name":"isSelected","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Boolean","kind":"SCALAR"}}}],"kind":"OBJECT"},{"name":"MemeReactionConnection","interfaces":[{"name":"Connection","kind":"INTERFACE"}],"fields":[{"name":"pageInfo","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"PageInfo","kind":"OBJECT"}}},{"name":"totalCount","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Long","kind":"SCALAR"}}},{"name":"edges","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"kind":"LIST","ofType":{"kind":"NON_NULL","ofType":{"name":"MemeReactionEdge","kind":"OBJECT"}}}}},{"name":"nodes","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"kind":"LIST","ofType":{"kind":"NON_NULL","ofType":{"name":"MemeReaction","kind":"OBJECT"}}}}}],"kind":"OBJECT"},{"name":"MemeReactionEdge","interfaces":[{"name":"Edge","kind":"INTERFACE"}],"fields":[{"name":"node","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"MemeReaction","kind":"OBJECT"}}},{"name":"cursor","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Cursor","kind":"SCALAR"}}}],"kind":"OBJECT"},{"name":"MemeSearchResultItem","interfaces":[],"fields":[{"name":"meme","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Meme","kind":"OBJECT"}}},{"name":"user","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"User","kind":"OBJECT"}}}],"kind":"OBJECT"},{"name":"MemeType","kind":"ENUM","enumValues":[{"name":"IMAGE","isDeprecated":false},{"name":"VIDEO","isDeprecated":false},{"name":"ANIMATED_GIF","isDeprecated":false}]},{"name":"Node","interfaces":[],"possibleTypes":[{"name":"Ad","kind":"OBJECT"},{"name":"Meme","kind":"OBJECT"},{"name":"MemeReaction","kind":"OBJECT"},{"name":"User","kind":"OBJECT"},{"name":"Viewer","kind":"OBJECT"}],"fields":[{"name":"id","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"ID","kind":"SCALAR"}}}],"kind":"INTERFACE"},{"name":"PageInfo","interfaces":[],"fields":[{"name":"startCursor","args":[],"isDeprecated":false,"type":{"name":"Cursor","kind":"SCALAR"}},{"name":"endCursor","args":[],"isDeprecated":false,"type":{"name":"Cursor","kind":"SCALAR"}},{"name":"hasNextPage","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Boolean","kind":"SCALAR"}}},{"name":"hasPreviousPage","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Boolean","kind":"SCALAR"}}}],"kind":"OBJECT"},{"name":"Query","interfaces":[],"fields":[{"name":"apiVersion","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"String","kind":"SCALAR"}}},{"name":"viewer","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Viewer","kind":"OBJECT"}}},{"name":"search","args":[{"name":"query","type":{"kind":"NON_NULL","ofType":{"name":"String","kind":"SCALAR"}}},{"name":"take","type":{"kind":"NON_NULL","ofType":{"name":"Int","kind":"SCALAR"}}},{"name":"after","type":{"name":"Cursor","kind":"SCALAR"}}],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"SearchResultItemConnection","kind":"OBJECT"}}},{"name":"feed","args":[{"name":"take","type":{"kind":"NON_NULL","ofType":{"name":"Int","kind":"SCALAR"}}},{"name":"after","type":{"name":"Cursor","kind":"SCALAR"}}],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"FeedItemConnection","kind":"OBJECT"}}}],"kind":"OBJECT"},{"name":"ReMemeFeedItem","interfaces":[],"fields":[{"name":"meme","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Meme","kind":"OBJECT"}}},{"name":"postedBy","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"User","kind":"OBJECT"}}},{"name":"repostedBy","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"User","kind":"OBJECT"}}}],"kind":"OBJECT"},{"name":"ReMemeSearchResultItem","interfaces":[],"fields":[{"name":"meme","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Meme","kind":"OBJECT"}}},{"name":"postedBy","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"User","kind":"OBJECT"}}},{"name":"repostedBy","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"User","kind":"OBJECT"}}}],"kind":"OBJECT"},{"name":"SearchResultItem","possibleTypes":[{"name":"MemeSearchResultItem","kind":"OBJECT"},{"name":"ReMemeSearchResultItem","kind":"OBJECT"},{"name":"UserSearchResultItem","kind":"OBJECT"}],"kind":"UNION"},{"name":"SearchResultItemConnection","interfaces":[],"fields":[{"name":"pageInfo","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"PageInfo","kind":"OBJECT"}}},{"name":"totalCount","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Long","kind":"SCALAR"}}},{"name":"edges","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"kind":"LIST","ofType":{"kind":"NON_NULL","ofType":{"name":"SearchResultItemEdge","kind":"OBJECT"}}}}},{"name":"nodes","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"kind":"LIST","ofType":{"kind":"NON_NULL","ofType":{"name":"SearchResultItem","kind":"UNION"}}}}}],"kind":"OBJECT"},{"name":"SearchResultItemEdge","interfaces":[],"fields":[{"name":"node","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"SearchResultItem","kind":"UNION"}}},{"name":"cursor","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Cursor","kind":"SCALAR"}}}],"kind":"OBJECT"},{"name":"String","description":"Built-in String","kind":"SCALAR"},{"name":"TimeDetail","interfaces":[],"possibleTypes":[{"name":"Ad","kind":"OBJECT"},{"name":"Meme","kind":"OBJECT"},{"name":"User","kind":"OBJECT"},{"name":"Viewer","kind":"OBJECT"}],"fields":[{"name":"created","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"DateTime","kind":"SCALAR"}}},{"name":"lastUpdated","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"DateTime","kind":"SCALAR"}}}],"kind":"INTERFACE"},{"name":"UriString","kind":"SCALAR"},{"name":"User","interfaces":[{"name":"Node","kind":"INTERFACE"},{"name":"TimeDetail","kind":"INTERFACE"},{"name":"UserDetails","kind":"INTERFACE"}],"fields":[{"name":"id","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"ID","kind":"SCALAR"}}},{"name":"created","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"DateTime","kind":"SCALAR"}}},{"name":"lastUpdated","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"DateTime","kind":"SCALAR"}}},{"name":"name","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"String","kind":"SCALAR"}}},{"name":"images","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"UserImages","kind":"OBJECT"}}},{"name":"isOrganization","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Boolean","kind":"SCALAR"}}},{"name":"memes","args":[{"name":"take","type":{"kind":"NON_NULL","ofType":{"name":"Int","kind":"SCALAR"}}},{"name":"after","type":{"name":"Cursor","kind":"SCALAR"}}],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"FeedItemConnection","kind":"OBJECT"}}}],"kind":"OBJECT"},{"name":"UserDetails","interfaces":[{"name":"Node","kind":"INTERFACE"},{"name":"TimeDetail","kind":"INTERFACE"}],"possibleTypes":[{"name":"User","kind":"OBJECT"},{"name":"Viewer","kind":"OBJECT"}],"fields":[{"name":"id","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"ID","kind":"SCALAR"}}},{"name":"created","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"DateTime","kind":"SCALAR"}}},{"name":"lastUpdated","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"DateTime","kind":"SCALAR"}}},{"name":"name","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"String","kind":"SCALAR"}}},{"name":"images","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"UserImages","kind":"OBJECT"}}}],"kind":"INTERFACE"},{"name":"UserImages","interfaces":[],"fields":[{"name":"thumbnail","args":[],"isDeprecated":false,"type":{"name":"UriString","kind":"SCALAR"}},{"name":"banner","args":[],"isDeprecated":false,"type":{"name":"UriString","kind":"SCALAR"}}],"kind":"OBJECT"},{"name":"UserSearchResultItem","interfaces":[],"fields":[{"name":"user","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"User","kind":"OBJECT"}}}],"kind":"OBJECT"},{"name":"Viewer","interfaces":[{"name":"Node","kind":"INTERFACE"},{"name":"TimeDetail","kind":"INTERFACE"},{"name":"UserDetails","kind":"INTERFACE"}],"fields":[{"name":"id","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"ID","kind":"SCALAR"}}},{"name":"created","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"DateTime","kind":"SCALAR"}}},{"name":"lastUpdated","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"DateTime","kind":"SCALAR"}}},{"name":"isAuthenticated","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Boolean","kind":"SCALAR"}}},{"name":"name","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"String","kind":"SCALAR"}}},{"name":"images","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"UserImages","kind":"OBJECT"}}},{"name":"username","args":[],"isDeprecated":false,"type":{"name":"String","kind":"SCALAR"}},{"name":"email","args":[],"isDeprecated":false,"type":{"name":"String","kind":"SCALAR"}},{"name":"phoneNumber","args":[],"isDeprecated":false,"type":{"name":"String","kind":"SCALAR"}},{"name":"memes","args":[{"name":"take","type":{"kind":"NON_NULL","ofType":{"name":"Int","kind":"SCALAR"}}},{"name":"after","type":{"name":"Cursor","kind":"SCALAR"}}],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"FeedItemConnection","kind":"OBJECT"}}},{"name":"reactions","args":[{"name":"take","type":{"kind":"NON_NULL","ofType":{"name":"Int","kind":"SCALAR"}}},{"name":"after","type":{"name":"Cursor","kind":"SCALAR"}}],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"FeedItemConnection","kind":"OBJECT"}}}],"kind":"OBJECT"},{"name":"__Directive","interfaces":[],"fields":[{"name":"name","args":[],"isDeprecated":false,"type":{"name":"String","kind":"SCALAR"}},{"name":"description","args":[],"isDeprecated":false,"type":{"name":"String","kind":"SCALAR"}},{"name":"locations","args":[],"isDeprecated":false,"type":{"kind":"LIST","ofType":{"kind":"NON_NULL","ofType":{"name":"__DirectiveLocation","kind":"ENUM"}}}},{"name":"args","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"kind":"LIST","ofType":{"kind":"NON_NULL","ofType":{"name":"__InputValue","kind":"OBJECT"}}}}},{"name":"onOperation","args":[],"deprecationReason":"Use `locations`.","isDeprecated":true,"type":{"name":"Boolean","kind":"SCALAR"}},{"name":"onFragment","args":[],"deprecationReason":"Use `locations`.","isDeprecated":true,"type":{"name":"Boolean","kind":"SCALAR"}},{"name":"onField","args":[],"deprecationReason":"Use `locations`.","isDeprecated":true,"type":{"name":"Boolean","kind":"SCALAR"}}],"kind":"OBJECT"},{"name":"__DirectiveLocation","description":"An enum describing valid locations where a directive can be placed","kind":"ENUM","enumValues":[{"name":"QUERY","description":"Indicates the directive is valid on queries.","isDeprecated":false},{"name":"MUTATION","description":"Indicates the directive is valid on mutations.","isDeprecated":false},{"name":"FIELD","description":"Indicates the directive is valid on fields.","isDeprecated":false},{"name":"FRAGMENT_DEFINITION","description":"Indicates the directive is valid on fragment definitions.","isDeprecated":false},{"name":"FRAGMENT_SPREAD","description":"Indicates the directive is valid on fragment spreads.","isDeprecated":false},{"name":"INLINE_FRAGMENT","description":"Indicates the directive is valid on inline fragments.","isDeprecated":false},{"name":"VARIABLE_DEFINITION","description":"Indicates the directive is valid on variable definitions.","isDeprecated":false},{"name":"SCHEMA","description":"Indicates the directive is valid on a schema SDL definition.","isDeprecated":false},{"name":"SCALAR","description":"Indicates the directive is valid on a scalar SDL definition.","isDeprecated":false},{"name":"OBJECT","description":"Indicates the directive is valid on an object SDL definition.","isDeprecated":false},{"name":"FIELD_DEFINITION","description":"Indicates the directive is valid on a field SDL definition.","isDeprecated":false},{"name":"ARGUMENT_DEFINITION","description":"Indicates the directive is valid on a field argument SDL definition.","isDeprecated":false},{"name":"INTERFACE","description":"Indicates the directive is valid on an interface SDL definition.","isDeprecated":false},{"name":"UNION","description":"Indicates the directive is valid on an union SDL definition.","isDeprecated":false},{"name":"ENUM","description":"Indicates the directive is valid on an enum SDL definition.","isDeprecated":false},{"name":"ENUM_VALUE","description":"Indicates the directive is valid on an enum value SDL definition.","isDeprecated":false},{"name":"INPUT_OBJECT","description":"Indicates the directive is valid on an input object SDL definition.","isDeprecated":false},{"name":"INPUT_FIELD_DEFINITION","description":"Indicates the directive is valid on an input object field SDL definition.","isDeprecated":false}]},{"name":"__EnumValue","interfaces":[],"fields":[{"name":"name","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"String","kind":"SCALAR"}}},{"name":"description","args":[],"isDeprecated":false,"type":{"name":"String","kind":"SCALAR"}},{"name":"isDeprecated","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Boolean","kind":"SCALAR"}}},{"name":"deprecationReason","args":[],"isDeprecated":false,"type":{"name":"String","kind":"SCALAR"}}],"kind":"OBJECT"},{"name":"__Field","interfaces":[],"fields":[{"name":"name","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"String","kind":"SCALAR"}}},{"name":"description","args":[],"isDeprecated":false,"type":{"name":"String","kind":"SCALAR"}},{"name":"args","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"kind":"LIST","ofType":{"kind":"NON_NULL","ofType":{"name":"__InputValue","kind":"OBJECT"}}}}},{"name":"type","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"__Type","kind":"OBJECT"}}},{"name":"isDeprecated","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"Boolean","kind":"SCALAR"}}},{"name":"deprecationReason","args":[],"isDeprecated":false,"type":{"name":"String","kind":"SCALAR"}}],"kind":"OBJECT"},{"name":"__InputValue","interfaces":[],"fields":[{"name":"name","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"String","kind":"SCALAR"}}},{"name":"description","args":[],"isDeprecated":false,"type":{"name":"String","kind":"SCALAR"}},{"name":"type","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"__Type","kind":"OBJECT"}}},{"name":"defaultValue","args":[],"isDeprecated":false,"type":{"name":"String","kind":"SCALAR"}}],"kind":"OBJECT"},{"name":"__Schema","description":"A GraphQL Introspection defines the capabilities of a GraphQL server. It exposes all available types and directives on the server, the entry points for query, mutation, and subscription operations.","interfaces":[],"fields":[{"name":"description","args":[],"isDeprecated":false,"type":{"name":"String","kind":"SCALAR"}},{"name":"types","args":[],"description":"A list of all types supported by this server.","isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"kind":"LIST","ofType":{"kind":"NON_NULL","ofType":{"name":"__Type","kind":"OBJECT"}}}}},{"name":"queryType","args":[],"description":"The type that query operations will be rooted at.","isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"__Type","kind":"OBJECT"}}},{"name":"mutationType","args":[],"description":"If this server supports mutation, the type that mutation operations will be rooted at.","isDeprecated":false,"type":{"name":"__Type","kind":"OBJECT"}},{"name":"directives","args":[],"description":"'A list of all directives supported by this server.","isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"kind":"LIST","ofType":{"kind":"NON_NULL","ofType":{"name":"__Directive","kind":"OBJECT"}}}}},{"name":"subscriptionType","args":[],"description":"'If this server support subscription, the type that subscription operations will be rooted at.","isDeprecated":false,"type":{"name":"__Type","kind":"OBJECT"}}],"kind":"OBJECT"},{"name":"__Type","interfaces":[],"fields":[{"name":"kind","args":[],"isDeprecated":false,"type":{"kind":"NON_NULL","ofType":{"name":"__TypeKind","kind":"ENUM"}}},{"name":"name","args":[],"isDeprecated":false,"type":{"name":"String","kind":"SCALAR"}},{"name":"description","args":[],"isDeprecated":false,"type":{"name":"String","kind":"SCALAR"}},{"name":"fields","args":[{"name":"includeDeprecated","type":{"name":"Boolean","kind":"SCALAR"},"defaultValue":"false"}],"isDeprecated":false,"type":{"kind":"LIST","ofType":{"kind":"NON_NULL","ofType":{"name":"__Field","kind":"OBJECT"}}}},{"name":"interfaces","args":[],"isDeprecated":false,"type":{"kind":"LIST","ofType":{"kind":"NON_NULL","ofType":{"name":"__Type","kind":"OBJECT"}}}},{"name":"possibleTypes","args":[],"isDeprecated":false,"type":{"kind":"LIST","ofType":{"kind":"NON_NULL","ofType":{"name":"__Type","kind":"OBJECT"}}}},{"name":"enumValues","args":[{"name":"includeDeprecated","type":{"name":"Boolean","kind":"SCALAR"},"defaultValue":"false"}],"isDeprecated":false,"type":{"kind":"LIST","ofType":{"kind":"NON_NULL","ofType":{"name":"__EnumValue","kind":"OBJECT"}}}},{"name":"inputFields","args":[],"isDeprecated":false,"type":{"kind":"LIST","ofType":{"kind":"NON_NULL","ofType":{"name":"__InputValue","kind":"OBJECT"}}}},{"name":"ofType","args":[],"isDeprecated":false,"type":{"name":"__Type","kind":"OBJECT"}},{"name":"specifiedByUrl","args":[],"isDeprecated":false,"type":{"name":"String","kind":"SCALAR"}}],"kind":"OBJECT"},{"name":"__TypeKind","description":"An enum describing what kind of type a given __Type is","kind":"ENUM","enumValues":[{"name":"SCALAR","description":"Indicates this type is a scalar. 'specifiedByUrl' is a valid field","isDeprecated":false},{"name":"OBJECT","description":"Indicates this type is an object. `fields` and `interfaces` are valid fields.","isDeprecated":false},{"name":"INTERFACE","description":"Indicates this type is an interface. `fields` and `possibleTypes` are valid fields.","isDeprecated":false},{"name":"UNION","description":"Indicates this type is a union. `possibleTypes` is a valid field.","isDeprecated":false},{"name":"ENUM","description":"Indicates this type is an enum. `enumValues` is a valid field.","isDeprecated":false},{"name":"INPUT_OBJECT","description":"Indicates this type is an input object. `inputFields` is a valid field.","isDeprecated":false},{"name":"LIST","description":"Indicates this type is a list. `ofType` is a valid field.","isDeprecated":false},{"name":"NON_NULL","description":"Indicates this type is a non-null. `ofType` is a valid field.","isDeprecated":false}]}],"directives":[{"name":"include","args":[{"name":"if","type":{"kind":"NON_NULL","ofType":{"name":"Boolean","kind":"SCALAR"}},"description":"Included when true."}],"description":"Directs the executor to include this field or fragment only when the `if` argument is true","locations":["FIELD","FRAGMENT_SPREAD","INLINE_FRAGMENT"]},{"name":"skip","args":[{"name":"if","type":{"kind":"NON_NULL","ofType":{"name":"Boolean","kind":"SCALAR"}},"description":"Skipped when true."}],"description":"Directs the executor to skip this field or fragment when the `if`'argument is true.","locations":["FIELD","FRAGMENT_SPREAD","INLINE_FRAGMENT"]},{"name":"deprecated","args":[{"name":"reason","description":"The reason for the deprecation","type":{"name":"String","kind":"SCALAR"},"defaultValue":"\"No longer supported\""}],"description":"Marks the field or enum value as deprecated","locations":["FIELD_DEFINITION","ENUM_VALUE"]},{"name":"specifiedBy","args":[{"name":"url","type":{"kind":"NON_NULL","ofType":{"name":"String","kind":"SCALAR"}},"description":"The URL that specifies the behaviour of this scalar."}],"description":"Exposes a URL that specifies the behaviour of this scalar.","locations":["SCALAR"]}],"queryType":{"name":"Query"}}}}
""".trimIndent()
