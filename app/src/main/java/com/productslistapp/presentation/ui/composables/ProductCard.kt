package com.productslistapp.presentation.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.productslistapp.R
import com.productslistapp.domain.models.Product

@Composable
fun ProductCard(navController: NavHostController, item: Product) {
    Card(
        modifier = Modifier
            .height(340.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                navController.navigate("ProductInfo/" + item.id)
            },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ){
            Box(modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .height(175.dp)){
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .height(175.dp),
                    model = item.thumbnail,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )

            }
            Text(
                text = item.title.toString(),
                modifier = Modifier
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = item.description.toString(),
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "$${item.price.toString()}",
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 8.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleLarge
            )
            Row(modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp)
            ){
                Icon(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "",
                    tint = Color.Yellow
                )
                Text(
                    text = "${item.rating}",
                    modifier = Modifier,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }

}