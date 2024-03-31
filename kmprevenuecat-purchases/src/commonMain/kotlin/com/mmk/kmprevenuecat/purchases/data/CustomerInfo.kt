package com.mmk.kmprevenuecat.purchases.data

public data class CustomerInfo(
    val originalAppUserId: String,
    val entitlements: EntitlementInfos,
    val managementURL: String?
) 