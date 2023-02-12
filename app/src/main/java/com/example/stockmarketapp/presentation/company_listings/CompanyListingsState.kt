package com.example.stockmarketapp.presentation.company_listings

import com.example.stockmarketapp.domain.model.CompanyListing

data class CompanyListingsState(
    val companies: List<CompanyListing> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null,
    val refreshing: Boolean = false,
    val searchQuery: String = ""
)
