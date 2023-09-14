package com.example.taskmanager

import android.R.attr.bitmap
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest


private val Int.CLAMP: Shader.TileMode
    get() {
        TODO("Not yet implemented")
    }

class CircleCropTransformation : BitmapTransformation() {
    var shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

    private fun BitmapShader(
        bitmap: Int,
        clamp: Shader.TileMode,
        clamp1: Shader.TileMode
    ): BitmapShader {
        TODO("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        return circleCrop(pool, toTransform)!!
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update("circle-crop".toByteArray())
    }

    companion object : Transformation<Bitmap> {
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        private fun circleCrop(pool: BitmapPool, source: Bitmap?): Bitmap? {
            if (source == null) return null
            val size = Math.min(source.width, source.height)
            val x = (source.width - size) / 2
            val y = (source.height - size) / 2
            val squared = Bitmap.createBitmap(source, x, y, size, size)
            var result = pool[size, size, Bitmap.Config.ARGB_8888]
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
            }

            // Обрезаем изображение в круглую форму
            val canvas = Canvas(result)
            val paint = Paint()
            paint.shader =
                BitmapShader(squared, BitmapShader.FILTER_MODE_DEFAULT.CLAMP, BitmapShader.FILTER_MODE_DEFAULT.CLAMP)
            paint.isAntiAlias = true
            val r = size / 2f
            canvas.drawCircle(r, r, r, paint)
            return result
        }

        override fun updateDiskCacheKey(messageDigest: MessageDigest) {
            TODO("Not yet implemented")
        }

        override fun transform(
            context: Context,
            resource: Resource<Bitmap>,
            outWidth: Int,
            outHeight: Int
        ): Resource<Bitmap> {
            TODO("Not yet implemented")
        }
    }
}
