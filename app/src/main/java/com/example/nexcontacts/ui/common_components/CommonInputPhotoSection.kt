package com.example.nexcontacts.ui.common_components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.nexcontacts.R
import com.example.nexcontacts.ui.theme.AppTheme
import com.example.nexcontacts.utils.ImageUtils
import kotlin.math.exp


private fun generateHaloGlow(color: Color, steps: Int = 40): List<Color> {
    return List(steps) { i ->
        val t = i.toFloat() / (steps - 1)


        val center = 0.5f
        val sigma = 0.22f

        val gaussian = exp(-((t - center) * (t - center)) / (2f * sigma * sigma))

        val alpha = gaussian * 0.40f

        color.copy(alpha = alpha)
    } + Color.Transparent
}


@Composable
fun CommonInputPhotoSection(
    photoUri: String?,
    editMode: Boolean,
    onPhotoClick: () -> Unit
) {
    val context = LocalContext.current

    var dominantColor by remember { mutableStateOf(Color(0xFFCCCCCC)) }

    LaunchedEffect(photoUri) {
        if (!photoUri.isNullOrBlank()) {
            val uri = Uri.parse(photoUri)
            val colorInt = ImageUtils.getDominantColor(context, uri)
            dominantColor = Color(colorInt)
        }
    }


    val glowBrush = remember(dominantColor) {
        Brush.radialGradient(
            colors = generateHaloGlow(dominantColor),
            radius = 240f
        )
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Box(
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
                .background(glowBrush),
            contentAlignment = Alignment.Center
        ) {


            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (photoUri.isNullOrBlank()) {
                    Image(
                        painter = painterResource(id = R.drawable.empty_avatar),
                        contentDescription = null,
                        modifier = Modifier.size(96.dp)
                    )
                } else {
                    AsyncImage(
                        model = photoUri,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                    )
                }
            }
        }

        if (editMode) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = if (photoUri == null) "Add Photo" else "Change Photo",
                color = AppTheme.colors.primary,
                style = AppTheme.typography.bodyLarge,
                modifier = Modifier.clickable { onPhotoClick() }
            )
        }
    }
}
