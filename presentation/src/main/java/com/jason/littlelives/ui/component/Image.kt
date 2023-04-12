package com.jason.littlelives.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jason.littlelives.R

@Composable
fun ResImage(
    modifier: Modifier = Modifier,
    resIconId: Int,
    colorFilter: ColorFilter? = null
) {
    Image(
        modifier = modifier,
        painter = painterResource(id = resIconId),
        contentDescription = "",
        colorFilter = colorFilter,
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun getErrorHolderBackground(): Int {
    return if (isSystemInDarkTheme()) {
        R.drawable.background_placeholder_dark
    } else {
        R.drawable.background_placeholder_light
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GlideImage(
    modifier: Modifier = Modifier,
    imageUrl: Any?,
    placeHolder: Int? = null,
    errorHolder: Int = getErrorHolderBackground(),
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null
) {
    com.bumptech.glide.integration.compose.GlideImage(
        modifier = modifier,
        model = imageUrl,
        contentScale = contentScale,
        contentDescription = contentDescription,
    ) {
        if (placeHolder != null) {
            it.placeholder(placeHolder)
        }
        it.error(errorHolder)
        it.diskCacheStrategy(DiskCacheStrategy.ALL)
    }
}