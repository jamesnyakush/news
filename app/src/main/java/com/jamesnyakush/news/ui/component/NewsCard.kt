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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun NewsCard(
    title: String,
    description: String,
    urlToImage: String?,
    publishedAt: String,
) {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {

        //Image
        AsyncImage(
            model = urlToImage,
            contentDescription = null,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(8)),
            contentScale = ContentScale.FillWidth,

            )

        //Title
        Text(
            text = title,
            modifier = Modifier.padding(8.dp),
            color = Color.Black,
            fontSize = 18.sp
        )

        // Description
        Text(
            text = description,
            modifier = Modifier.padding(8.dp),
            color = Color.DarkGray,
            textAlign = TextAlign.Start,
            fontSize = 16.sp
        )

        //Published At
        Text(
            text = publishedAt,
            modifier = Modifier.padding(8.dp),
            color = Color.Gray,
            textAlign = TextAlign.Start,
            fontSize = 12.sp
        )
    }
}

@Preview
@Composable
fun NewsCardPreview() {
    NewsCard("", "", "", "")
}