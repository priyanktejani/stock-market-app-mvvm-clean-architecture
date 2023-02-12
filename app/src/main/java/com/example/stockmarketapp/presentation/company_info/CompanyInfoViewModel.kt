package com.example.stockmarketapp.presentation.company_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockmarketapp.domain.repository.StockRepository
import com.example.stockmarketapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: StockRepository
): ViewModel() {

    var state by mutableStateOf(CompanyInfoState())

    init {
        viewModelScope.launch {
            val symbol = savedStateHandle.get<String>("symbol") ?: return@launch

            state = state.copy(loading = true)
            val companyInfo = async { repository.getCompanyInfo(symbol) }
            val intradayInfo = async { repository.getIntradayInfo(symbol) }

            when(val companyInfoResult = companyInfo.await()) {
                is Resource.Loading -> Unit
                is Resource.Error -> {
                    companyInfoResult.message?.let { error ->
                        state = state.copy(error = error)
                    }
                }
                is Resource.Success -> {
                    state = state.copy(company = companyInfoResult.data)
                }
            }

            when(val intradayInfoResult = intradayInfo.await()) {
                is Resource.Loading -> Unit
                is Resource.Error -> {
                    intradayInfoResult.message?.let { error ->
                        state = state.copy(error = error)
                    }
                }
                is Resource.Success -> {
                    intradayInfoResult.data?.let { listing ->
                        state = state.copy(stockInfos = listing)
                    }
                }
            }
        }
    }
}