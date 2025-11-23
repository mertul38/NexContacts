package com.example.nexcontacts.ui.common_components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.nexcontacts.ui.theme.AppTheme

@Composable
fun CommonInputInfoSection(
    firstName: String,
    lastName: String,
    phone: String,
    editMode: Boolean,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        CommonTextField(
            value = firstName,
            enabled = editMode,
            placeholder = "First Name",
            onValueChange = onFirstNameChange
        )

        Spacer(modifier = Modifier.height(12.dp))

        CommonTextField(
            value = lastName,
            enabled = editMode,
            placeholder = "Last Name",
            onValueChange = onLastNameChange
        )

        Spacer(modifier = Modifier.height(12.dp))

        CommonTextField(
            value = phone,
            enabled = editMode,
            placeholder = "+123456789",
            onValueChange = onPhoneChange
        )
    }
}

@Composable
private fun CommonTextField(
    value: String,
    enabled: Boolean,
    placeholder: String,
    onValueChange: (String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }

    val borderColor = if (isFocused) Color(0xFF202020) else Color(0xFFE7E7E7)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .background(AppTheme.colors.background, RoundedCornerShape(12.dp))
            .padding(horizontal = 0.dp)
    ) {
        TextField(
            value = value,
            enabled = enabled,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    placeholder,
                    color = AppTheme.colors.textTertiary,
                    style = AppTheme.typography.labelMedium
                )
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxSize()
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,

                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,

                cursorColor = AppTheme.colors.primary
            ),
            textStyle = AppTheme.typography.labelMedium.copy(
                color = AppTheme.colors.textPrimary
            )
        )
    }
}
