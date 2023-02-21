package com.example.stockmarketapp.presentation.company_listings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.stockmarketapp.Screen
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@ExperimentalMaterial3Api
@Composable
fun CompanyListingScreen(
    navController: NavController,
    viewModel: CompanyListingsViewModel = hiltViewModel()
) {
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.refreshing,
    )
    val state = viewModel.state
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "fistock",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding).fillMaxSize()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = state.searchQuery,
                        onValueChange = {
                            viewModel.onEvent(
                                CompanyListingsEvent.OnSearchQueryChange(it)
                            )
                        },

                        placeholder = {
                            Text("Search stocks...")
                        },
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp, bottom = 9.dp, end = 16.dp)
                            .fillMaxWidth()
                            .weight(1f),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        )

                    )

                }
                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = {
                        viewModel.onEvent(CompanyListingsEvent.Refresh)
                    }
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(state.companies.size) { i ->
                            val company = state.companies[i]
                            CompanyItem(
                                company = company,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate(
                                            Screen.CompanyInfoScreen.route + "/${company.symbol}"
                                        )
                                    }
                                    .padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    )
}
