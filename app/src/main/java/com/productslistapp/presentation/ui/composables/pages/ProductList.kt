package com.productslistapp.presentation.ui.composables.pages

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.productslistapp.presentation.MainViewModel
import com.example.productslistapp.R
import com.productslistapp.presentation.ui.composables.ProductCard
import com.productslistapp.presentation.ui.composables.SearchBar

@Composable
fun ProductList(navController: NavHostController, vm: MainViewModel){

    val loadingState = vm.isLoading.observeAsState()
    val productsList = vm.productsList.observeAsState()
    val categoriesList = vm.categoriesList.observeAsState()
    val total = vm.total.observeAsState()

    val expanded = remember {
        mutableStateOf(false)
    }

    if (loadingState.value == true) {
        Box(modifier = Modifier
            .fillMaxSize()
        ){
            CircularProgressIndicator(
                modifier = Modifier
                    .width(60.dp)
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    } else{
        Box(modifier = Modifier.fillMaxSize()){
            Column {
                SearchBar(mainViewModel = vm)
                Box(modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Row(modifier = Modifier.clickable {
                            expanded.value = !expanded.value
                        })
                        {
                            Icon(painter = painterResource(id = R.drawable.sort), contentDescription = "")
                            Text(
                                text = "Сортировать",
                                modifier = Modifier,
                                textAlign = TextAlign.Start,
                                fontSize = 14.sp
                            )
                        }
                        Row{
                            Text(
                                text = total.value.toString() + " Результатов",
                                modifier = Modifier,
                                textAlign = TextAlign.Start,
                                fontSize = 14.sp
                            )
                        }
                    }
                    DropdownMenu(
                        modifier = Modifier.height(250.dp).align(Alignment.CenterEnd),
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "по умолчанию",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            },
                            onClick = {
                                vm.getAllProducts()
                                expanded.value = false
                            }
                        )
                        categoriesList.value?.forEach {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = it,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                },
                                onClick = {
                                    vm.getProductsByCategory(it)
                                    expanded.value = false
                                }
                            )
                        }
                    }

                }

                if (productsList.value?.isEmpty() == true || productsList.value == null){
                    Text(
                        text = "Ничего не найдено",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                } else{
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        items(productsList.value!!) { item ->
                            ProductCard(navController = navController, item = item)
                        }
                    }
                }
            }

            if (productsList.value?.size != null){
                TextButton(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(horizontal = 32.dp, vertical = 32.dp),
                    onClick = { vm.previousPage() },
                    enabled = vm.requestParams.skip != 0,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(id = R.drawable.arrow_left),
                        tint = Color.Black,
                        contentDescription = ""
                    )
                }
                TextButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(horizontal = 32.dp, vertical = 32.dp),
                    onClick = { vm.nextPage()},
                    enabled = vm.requestParams.skip+20 < total.value!!,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(id = R.drawable.arrow_right),
                        tint = Color.Black,
                        contentDescription = ""
                    )
                }
            }

        }
    }


}