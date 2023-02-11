package com.example.stockmarketapp

sealed class Screen(val route: String) {

    object CompanyListingScreen: Screen("company_listing_screen")
}
