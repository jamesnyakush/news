package com.jamesnyakush.news.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.jamesnyakush.news.R
import com.jamesnyakush.news.data.model.Article
import kotlinx.coroutines.Dispatchers


@Composable
fun NewsDetailScreen(
    article: Article
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {

        val context = LocalContext.current
        val placeholder = R.drawable.placeholder
        val imageUrl = article.urlToImage

        //
        // Build an ImageRequest with Coil
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
            modifier = Modifier
                .padding(vertical = 18.dp, horizontal = 0.dp)
                .height(200.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.FillWidth,
        )

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                8.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                //Title
                Text(
                    text = "Title",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 18.sp,
                    color = Color.Black,
                )


                Text(
                    text = article.title!!,
                    modifier = Modifier.padding(8.dp),
                    color = Color.DarkGray,
                    fontSize = 16.sp
                )


                // Description
                Text(
                    text = "Description",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 18.sp,
                    color = Color.Black,
                    style = TextStyle.Default
                )

                Text(
                    text = article.description!!,
                    modifier = Modifier.padding(8.dp),
                    color = Color.DarkGray,
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )

                // Source
                Text(
                    text = "Source",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 18.sp,
                    color = Color.Black,
                    style = TextStyle.Default
                )

                article.author?.let {
                    Text(
                        text = it,
                        modifier = Modifier.padding(8.dp),
                        color = Color.DarkGray,
                        textAlign = TextAlign.Start,
                        fontSize = 16.sp
                    )
                }
            }
        }


    }
}

@Preview
@Composable
fun NewsDetailScreenPreview() {
    //NewsDetailScreen()
}