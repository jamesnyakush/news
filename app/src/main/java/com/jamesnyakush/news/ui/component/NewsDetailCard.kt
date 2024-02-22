package com.jamesnyakush.news.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@Composable
fun NewsDetailCard() {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        //Image
        AsyncImage(
            model = "https://static.dw.com/image/62282035_6.jpg",
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
                    text = "WolframAlpha.Wolfram has formulas to use for artificial neural networks",
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
                    text = "Dot-product matrix formulas for ANN's signal aggregator functions Dot-product matrix formulas for ANN's signal aggregator functions Dot-product matrix formulas for ANN's signal aggregator functions",
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

                Text(
                    text = "BBC News",
                    modifier = Modifier.padding(8.dp),
                    color = Color.DarkGray,
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp
                )
            }
        }


    }
}


@Preview
@Composable
fun NewsDetailCardPreview() {
    NewsDetailCard()
}