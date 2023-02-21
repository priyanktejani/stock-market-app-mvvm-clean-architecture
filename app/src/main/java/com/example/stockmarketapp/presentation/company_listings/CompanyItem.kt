package com.example.stockmarketapp.presentation.company_listings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CardDefaults.shape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockmarketapp.domain.model.CompanyListing

@Composable
fun CompanyItem(
    company: CompanyListing,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.padding(start = 16.dp, bottom = 8.dp, end = 16.dp)
            .clip(shape)
            .background(
                MaterialTheme.colorScheme.surfaceVariant
            ),
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = company.name,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = company.exchange,
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "(${company.symbol})",
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}