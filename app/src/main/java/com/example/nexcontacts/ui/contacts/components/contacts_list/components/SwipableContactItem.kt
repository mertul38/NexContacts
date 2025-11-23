package com.example.nexcontacts.ui.contacts.components.contacts_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.material.swipeable
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.SwipeableState
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import com.example.nexcontacts.R


import com.example.nexcontacts.domain.model.User
import com.example.nexcontacts.ui.theme.AppTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableContactItem(
    user: User,
    onEdit: (User) -> Unit,
    onDelete: (User) -> Unit,
    onClick: (User) -> Unit
) {
    val swipeWidth = 112.dp
    val swipePx = with(LocalDensity.current) { swipeWidth.toPx() }

    val anchors = mapOf(
        0f to 0,
        -swipePx to 1
    )

    val swipeState = rememberSwipeableState(initialValue = 0)

    Box(
        Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .swipeable(
                state = swipeState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
    ) {

        Row(
            Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Box(
                modifier = Modifier
                    .width(56.dp)
                    .fillMaxHeight()
                    .background(AppTheme.colors.edit)
                    .clickable { onEdit(user) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.edit),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            Box(
                modifier = Modifier
                    .width(56.dp)
                    .fillMaxHeight()
                    .background(Color.Red)
                    .clickable { onDelete(user) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Box(
            Modifier
                .offset { IntOffset(swipeState.offset.value.toInt(), 0) }
                .fillMaxWidth()
                .background(Color.White)
                .clickable { onClick(user) }
        ) {
            ContactItem(user = user, onClick = onClick)
        }
    }
}
