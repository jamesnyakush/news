package com.jamesnyakush.news.ui.component

import androidx.compose.foundation.Image
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
fun NewsCard() {

    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
    ) {

        //Image
        AsyncImage(
            model = "https://static.dw.com/image/62282035_6.jpg",
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
            text = "WolframAlpha.Wolfram has formulas to use for artificial neural networks",
            modifier = Modifier.padding(8.dp),
            color = Color.Black,
            fontSize = 18.sp
        )

        // Description
        Text(
            text = "Dot-product matrix formulas for ANN's signal aggregator functions Dot-product matrix formulas for ANN's signal aggregator functions Dot-product matrix formulas for ANN's signal aggregator functions",
            modifier = Modifier.padding(8.dp),
            color = Color.DarkGray,
            textAlign = TextAlign.Start,
            fontSize = 16.sp
        )

        //Published At
        Text(
            text = "2024-02-20T19:29:22Z",
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
    NewsCard()
}