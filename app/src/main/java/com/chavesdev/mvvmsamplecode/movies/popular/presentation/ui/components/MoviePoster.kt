package com.chavesdev.mvvmsamplecode.movies.popular.presentation.ui.components

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import com.chavesdev.mvvmsamplecode.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

@Composable
fun MoviePoster(posterPath: String, contentDescription: String, modifier: Modifier) {

    val url = (LocalContext.current).getString(R.string.base_poster_img_url) + posterPath

    val bitmapState = remember { mutableStateOf<Bitmap?>(null) }

    Picasso.get().load(url).into(object: Target {
        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            bitmapState.value = bitmap
        }

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
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