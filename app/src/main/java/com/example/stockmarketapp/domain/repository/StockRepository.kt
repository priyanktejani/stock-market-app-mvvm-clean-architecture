package com.example.stockmarketapp.domain.repository

import com.example.stockmarketapp.domain.model.CompanyListing
import com.example.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListings(
        query: String,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<CompanyListing>>>

}