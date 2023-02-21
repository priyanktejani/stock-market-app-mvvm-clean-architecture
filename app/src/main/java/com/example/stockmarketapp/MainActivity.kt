package com.example.stockmarketapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stockmarketapp.presentation.company_info.CompanyInfoScreen
import com.example.stockmarketapp.presentation.company_listings.CompanyListingScreen
import com.example.stockmarketapp.ui.theme.StockMarketAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockMarketAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CompanyListingScreen.route,

                    ) {
                        composable(
                            route = Screen.CompanyListingScreen.route
                        ) {
                            CompanyListingScreen(navController)
                        }
                        composable(
                            route = Screen.CompanyInfoScreen.route + "/{symbol}"
                        ) {
                            CompanyInfoScreen()
                        }
                    }
                }
            }
        }

    }
}
