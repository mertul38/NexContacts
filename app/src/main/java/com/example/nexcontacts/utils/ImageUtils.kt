package com.example.nexcontacts.utils

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import androidx.palette.graphics.Palette
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
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
        val inputStream = context.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val output = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, output)
        return output.toByteArray()
    }

    fun isSupportedImage(context: Context, uri: Uri): Boolean {
        val type = context.contentResolver.getType(uri)
        return type == "image/jpeg" || type == "image/png"
    }

    fun getDominantColor(context: Context, uri: Uri): Int {
        return try {
            val inputStream = when (uri.scheme) {
                "content", "file" -> context.contentResolver.openInputStream(uri)
                "http", "https" -> java.net.URL(uri.toString()).openStream()
                else -> null
            }
            val bitmap = inputStream?.use { BitmapFactory.decodeStream(it) } ?: return 0xFFCCCCCC.toInt()
            val palette = Palette.from(bitmap).generate()
            palette.getDominantColor(0xFF888888.toInt())
        } catch (e: Exception) {
            e.printStackTrace()
            0xFFCCCCCC.toInt()
        }
    }


    fun uriToFile(context: Context, uriString: String, userId: String? = null): File? {
        return try {
            val uri = Uri.parse(uriString)
            val inputStream = context.contentResolver.openInputStream(uri) ?: return null
            val targetDir = File(context.filesDir, "profile_photos").apply { mkdirs() }

            val targetFile = if (!userId.isNullOrBlank()) {
                File(targetDir, "$userId.jpg")
            } else {
                File.createTempFile("upload_", ".jpg", targetDir)
            }

            FileOutputStream(targetFile).use { output -> inputStream.copyTo(output) }
            inputStream.close()

            println("[ImageUtils] uriToFile -> ${targetFile.absolutePath}")
            targetFile
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}
