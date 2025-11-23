package com.example.nexcontacts.ui.contact_profile.components.topbar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.nexcontacts.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteModalSheet(
    userName: String = "",
    onDeleteClicked: () -> Unit,
    onCancelClicked: () -> Unit,
    onDismissRequest: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Delete Contact",
                style = AppTheme.typography.titleXLarge,
                color = AppTheme.colors.textSecondary
            )

            Spacer(Modifier.height(12.dp))

            Text(
                "Are you sure you want to delete this contact?",
                style = AppTheme.typography.labelMedSmall,
                color = AppTheme.colors.textSecondary
            )

            Spacer(Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                // NO (Outlined)
                OutlinedButton(
                    onClick = onCancelClicked,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(50),
                    border = BorderStroke(1.dp, AppTheme.colors.textFifth)
                ) {
                    Text("No",
                        style = AppTheme.typography.bodySmall,
                        color = AppTheme.colors.textFifth
                    )
                }

                // YES (Filled)
                Button(
                    onClick = onDeleteClicked,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppTheme.colors.textFifth
                    ),
                    shape = RoundedCornerShape(50)   // very rounded
                ) {
                    Text("Yes",
                        style = AppTheme.typography.bodySmall,
                        color = Color.White
                        )
                }
            }


            Spacer(Modifier.height(16.dp))
        }
    }
}
