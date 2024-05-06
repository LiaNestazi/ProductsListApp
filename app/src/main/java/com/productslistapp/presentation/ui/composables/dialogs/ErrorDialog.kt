package com.productslistapp.presentation.ui.composables.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorDialog(
    onReloadButtonClick: () -> Unit,
    title: String,
    text: String,
    errorMsg: String
){

    AlertDialog(
        onDismissRequest = { },
        icon = {},
        title = {
                Text(
                    text = title,
                    modifier = Modifier
                        .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium
                )
        },
        text = {
               Column {
                   Text(
                       text = text,
                       modifier = Modifier
                           .padding(horizontal = 8.dp, vertical = 4.dp)
                           .fillMaxWidth(),
                       style = MaterialTheme.typography.bodySmall
                   )
                   Text(
                       text = "Ошибка: $errorMsg",
                       modifier = Modifier
                           .padding(horizontal = 8.dp, vertical = 4.dp)
                           .fillMaxWidth(),
                       style = MaterialTheme.typography.bodySmall
                   )
               }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onReloadButtonClick()
                }
            ) {
                Text("Перезагрузить")
            }

        }
    )
}