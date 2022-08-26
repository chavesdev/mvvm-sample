package com.chavesdev.mvvmsamplecode.movies.core.presentation.components

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import org.koin.androidx.compose.get
import java.lang.Exception

@Composable
fun MovieImage(
    url: String,
    contentDescription: String,
    modifier: Modifier,
    picasso: Picasso = get()
) {

    val bitmapState = remember { mutableStateOf<Bitmap?>(null) }

    picasso.load(url).into(object: Target {
        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            bitmapState.value = bitmap
        }

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            Log.e("MovieImage", e?.message ?: "Something wrong on image")
        }

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

        }
    })

    bitmapState.value?.let {
        Image(
            bitmap = it.asImageBitmap(),
            contentDescription = contentDescription,
            modifier = modifier
        )
    }
}