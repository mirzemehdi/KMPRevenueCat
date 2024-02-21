package com.mmk.kmprevenuecat.purchases.data

public data class EntitlementInfo(
    val identifier: String,
    val isActive: Boolean,
    val willRenew: Boolean,
    val latestPurchaseDate: Long,
    val originalPurchaseDate: Long,
    val expirationDate: Long?,
    val productIdentifier: String,
    val productPlanIdentifier: String?,
    val isSandbox: Boolean,
    val unsubscribeDetectedAt: Long?,
    val billingIssueDetectedAt: Long?,
)