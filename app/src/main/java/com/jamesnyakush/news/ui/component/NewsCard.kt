package com.jamesnyakush.news.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.jamesnyakush.news.R
import com.jamesnyakush.news.data.model.Article
import com.jamesnyakush.news.getPeriod
import com.jamesnyakush.news.toDateFormat
import kotlinx.coroutines.Dispatchers

@Composable
fun NewsCard(
    article: Article,
) {
    if (article.title != "[Removed]") {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()

        ) {

            HeroImage(article = article)

            Text(
                text = article.title!!,
                modifier = Modifier.padding(8.dp),
                color = Color.Black,
                fontSize = 18.sp
            )

            Text(
                text = getPeriod(article.publishedAt!!.toDateFormat()),
                modifier = Modifier.padding(8.dp),
                color = Color.Gray,
                textAlign = TextAlign.Start,
                fontSize = 12.sp
            )

        }
    }
}

@Composable
fun HeroImage(article: Article) {
    val context = LocalContext.current
    val placeholder = R.drawable.placeholder
    val imageUrl = article.urlToImage


    val listener = object : ImageRequest.Listener {
        override fun onError(request: ImageRequest, result: ErrorResult) {
            super.onError(request, result)
        }

        override fun onSuccess(request: ImageRequest, result: SuccessResult) {
            super.onSuccess(request, result)
        }
    }

    val imageRequest = ImageRequest.Builder(context)
        .data(imageUrl)
        .listener(listener)
        .dispatcher(Dispatchers.IO)
        .memoryCacheKey(imageUrl)
        .diskCacheKey(imageUrl)
        .placeholder(placeholder)
        .error(placeholder)
        .fallback(placeholder)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()

    //Image
    AsyncImage(
        model = imageRequest,
        contentDescription = null,
        placeholder = painterResource(R.drawable.placeholder),
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8)),
        contentScale = ContentScale.FillWidth,

        )
}