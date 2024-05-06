package com.productslistapp.presentation.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.productslistapp.presentation.MainViewModel
import com.example.productslistapp.R

@Composable
fun SearchBar(mainViewModel: MainViewModel){
    val searchRequest = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        value = searchRequest.value,
        onValueChange = {
            searchRequest.value = it
            if (searchRequest.value != ""){
                mainViewModel.searchProduct(searchRequest.value)
            } else{
                mainViewModel.getAllProducts()
            }
        },
        textStyle = MaterialTheme.typography.bodyMedium,
        placeholder = {
            Row {
                Icon(modifier = Modifier,
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "",
                    tint = Color.Gray
                )
                Text("Введите запрос",
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 4.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            cursorColor = Color.Black,
            disabledTrailingIconColor = Color.Gray
        )
    )

}