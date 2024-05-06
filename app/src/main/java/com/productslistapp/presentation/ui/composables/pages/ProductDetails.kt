package com.productslistapp.presentation.ui.composables.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.productslistapp.presentation.MainViewModel
import com.example.productslistapp.R
import com.productslistapp.presentation.constants.ErrorConstants
import com.productslistapp.presentation.ui.composables.dialogs.ErrorDialog
import java.util.Currency


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductDetails(navController: NavHostController, mainViewModel: MainViewModel, itemId: Int?){
    if (itemId == null){
        ErrorDialog(
            onReloadButtonClick = {navController.popBackStack()},
            title = ErrorConstants.INTERNAL_ERROR,
            text = ErrorConstants.TRY_AGAIN_HINT,
            errorMsg = ErrorConstants.PAGE_NOT_FOUND_MSG,
        )
    } else{
        mainViewModel.getProductDetails(itemId)
        val prod = mainViewModel.currentProduct.observeAsState()
        if (prod.value?.images?.size != null){
            val pagerState = rememberPagerState{ prod.value?.images?.size!!}
            Box(modifier = Modifier.fillMaxSize()){
                Column(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .heightIn(0.dp, 250.dp)

                    ){
                        when (prod.value?.images){
                            null -> {

                            }
                            else -> {
                                HorizontalPager(
                                    state = pagerState,
                                    key = { prod.value?.images!![it]}
                                ) {index ->
                                    AsyncImage(
                                        modifier = Modifier.fillMaxSize(),
                                        model = prod.value?.images!![index],
                                        contentDescription = "",
                                    )
                                }
                            }
                        }

                        Icon(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(40.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color.Black)
                                .clickable {
                                    navController.popBackStack()
                                }
                                .align(Alignment.TopStart),
                            painter = painterResource(id = R.drawable.arrow_left),
                            tint = Color.White,
                            contentDescription = ""
                        )
                        Box(modifier = Modifier
                            .padding(16.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .align(Alignment.BottomCenter)
                            .background(Color.White.copy(alpha = 0.5F))){
                            Text(
                                text = if (prod.value?.images?.size != null) "${pagerState.currentPage+1}/${prod.value?.images?.size}" else "${pagerState.currentPage+1}/1",
                                modifier = Modifier
                                    .padding(vertical = 4.dp, horizontal = 8.dp),
                                textAlign = TextAlign.Start,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically){
                        Row(horizontalArrangement = Arrangement.Start) {
                            Text(
                                "$${prod.value?.price}",
                                modifier = Modifier,
                                textAlign = TextAlign.Start,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = " (-${prod.value?.discountPercentage}%)",
                                modifier = Modifier,
                                textAlign = TextAlign.Start,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Green
                            )
                        }

                        Row(horizontalArrangement = Arrangement.End) {
                            Icon(
                                painter = painterResource(id = R.drawable.star),
                                contentDescription = "",
                                tint = Color.Yellow
                            )
                            Text(
                                text = " ${prod.value?.rating}",
                                modifier = Modifier,
                                textAlign = TextAlign.Start,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                    }
                    Text(
                        text = "В наличии: ${prod.value?.stock}",
                        modifier = Modifier
                            .padding(start = 8.dp, bottom = 8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Divider(modifier = Modifier
                        .fillMaxWidth()
                        .size(1.dp)
                        .padding(horizontal = 8.dp))
                    Text(
                        "${prod.value?.category}",
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        "${prod.value?.brand} \u00B7 ${prod.value?.title}",
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Divider(modifier = Modifier
                        .fillMaxWidth()
                        .size(1.dp)
                        .padding(horizontal = 8.dp))
                    Text(
                        text = "${prod.value?.description}",
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.bodyMedium
                    )

                }

            }
        }
    }
}