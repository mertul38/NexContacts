package com.example.nexcontacts.ui.contacts.components.contacts_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.nexcontacts.R
import com.example.nexcontacts.ui.theme.AppTheme

@Composable
fun SearchHistorySection(
    history: List<String>,
    onSelect: (String) -> Unit,
    onRemove: (String) -> Unit,
    onClearAll: () -> Unit
) {
    Column(
        Modifier.background(Color.Transparent)
    ) {
        // HEADER
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "SEARCH HISTORY",
                style = AppTheme.typography.labelLarge,
                color = AppTheme.colors.textTertiary
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                "Clear All",
                style = AppTheme.typography.labelMedSmaller,
                color = AppTheme.colors.primary,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable { onClearAll() }
            )
        }

        Spacer(Modifier.height(12.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            // HISTORY LIST
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                history.forEach { item ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.close),
                            contentDescription = null,
                            tint = AppTheme.colors.textTertiary,
                            modifier = Modifier
                                .size(8.dp)
                                .clickable { onRemove(item) }
                        )

                        Spacer(Modifier.width(12.dp))

                        Text(
                            text = item,
                            style = AppTheme.typography.labelMedSmaller,
                            color = AppTheme.colors.textPrimary,
                            modifier = Modifier.clickable { onSelect(item) }
                        )
                    }
                }
            }
        }


    }
}
