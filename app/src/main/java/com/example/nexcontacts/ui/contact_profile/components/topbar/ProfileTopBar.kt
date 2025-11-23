package com.example.nexcontacts.ui.contacts.contact_profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.nexcontacts.ui.theme.AppTheme
import com.example.nexcontacts.R

@Composable
fun ProfileTopBar(
    editMode: Boolean,
    onCancel: () -> Unit,
    onEditClicked: () -> Unit,
    onDone: () -> Unit,
    onRemoveClicked: () -> Unit
) {

    var menuExpanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (!editMode) {
            // --- VIEW MODE ---
            Spacer(Modifier.weight(1f))


            // 3 DOT MENU

            Box(
                modifier = Modifier.wrapContentSize(Alignment.TopEnd)
            ) {
                Text(
                    "⋮",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable { menuExpanded = true }
                )

                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false },
                    modifier = Modifier
                        .wrapContentWidth()
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                ) {

                    // EDIT ROW
                    DropdownMenuItem(
                        text = {
                            Row(
                                modifier = Modifier
                                    .width(165.dp)
                                    .height(40.dp)
                                    .padding(horizontal = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    "Edit",
                                    color = AppTheme.colors.textSecondary
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.edit),
                                    colorFilter = ColorFilter.tint(AppTheme.colors.textSecondary),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        },
                        onClick = {
                            menuExpanded = false
                            onEditClicked()
                        },
                        modifier = Modifier.background(Color.White)
                    )

                    // Gray divider line
                    Divider(
                        color = Color(0xFFE0E0E0),
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    // DELETE ROW
                    DropdownMenuItem(
                        text = {
                            Row(
                                modifier = Modifier
                                    .width(165.dp)
                                    .height(40.dp)
                                    .padding(horizontal = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    "Delete",
                                    color = AppTheme.colors.delete
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.delete),
                                    colorFilter = ColorFilter.tint(AppTheme.colors.delete),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                )
                            }
                        },
                        onClick = {
                            menuExpanded = false
                            onRemoveClicked()   // Function name stays same
                        },
                        modifier = Modifier.background(Color.White)
                    )
                }

            }


        } else {
            // --- EDIT MODE ---
            Text(
                text = "Cancel",
                color = AppTheme.colors.primary,
                style = AppTheme.typography.bodyMedium,
                modifier = Modifier.clickable { onCancel() }
            )

            Spacer(Modifier.weight(1f))

            Text(
                text = "Edit Contact",
                style = AppTheme.typography.titleXLarge,
                color = AppTheme.colors.textFifth
            )

            Spacer(Modifier.weight(1f))

            Text(
                text = "Done",
                color = AppTheme.colors.primary,
                style = AppTheme.typography.bodyLarge,
                modifier = Modifier.clickable { onDone() }
            )
        }
    }
}
