// ImageUtils.kt
package com.example.nexcontacts.utils

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import androidx.palette.graphics.Palette
import java.io.ByteArrayOutputStream
import java.io.InputStream

object ImageUtils {

    fun createImageUri(context: Context): Uri {
        val resolver = context.contentResolver
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }
        return resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!
    }

    fun compressImage(context: Context, uri: Uri, quality: Int = 70): ByteArray {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream)

        val output = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, output)

        return output.toByteArray()
    }

    fun isSupportedImage(context: Context, uri: Uri): Boolean {
        val type = context.contentResolver.getType(uri)
        return type == "image/jpeg" || type == "image/png"
    }

    // ------------- NEW: DOMINANT COLOR GETTER -------------
    fun getDominantColor(context: Context, uri: Uri): Int {
        val inputStream = context.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream) ?: return 0xFFCCCCCC.toInt()

        val palette = Palette.from(bitmap).generate()
        val dominant = palette.getDominantColor(0xFF888888.toInt())  // fallback gray
        return dominant
    }
}
