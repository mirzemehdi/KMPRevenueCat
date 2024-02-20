package com.mmk.kmprevenuecat.purchases

/**
 * KMPRevenueCatInternalApi Annotation class that limits access for internal usage
 */
@RequiresOptIn(
    message = "This is internal API for KMPRevenueCat. This shouldn't be used outside of KMPRevenueCat API",
    level = RequiresOptIn.Level.ERROR
)
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY
)
public annotation class KMPRevenueCatInternalApi