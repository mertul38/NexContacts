package com.example.nexcontacts.ui.contacts.components.contacts_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.onFocusChanged
import com.example.nexcontacts.R
import com.example.nexcontacts.ui.theme.AppTheme

@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    onFocusChanged: (Boolean) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = onValueChange,

        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(18.dp)
            )
        },


        placeholder = {
            if (value.isEmpty()) {
                Text(
                    text = "Search by name",
                    color = Color.Gray,
                    style = AppTheme.typography.labelMedium
                )
            }
        },

        singleLine = true,

        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .onFocusChanged { state ->
                isFocused = state.isFocused
                onFocusChanged(state.isFocused)
            },

        shape = RoundedCornerShape(12.dp),

        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,

            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,

            cursorColor = Color.Black,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
        ),

        textStyle = AppTheme.typography.labelLarge
    )
}
