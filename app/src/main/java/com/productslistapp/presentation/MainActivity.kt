package com.productslistapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.productslistapp.domain.models.Product
import com.productslistapp.presentation.constants.ErrorConstants
import com.productslistapp.presentation.managers.ConnectionManager
import com.productslistapp.presentation.ui.composables.dialogs.ErrorDialog
import com.productslistapp.presentation.ui.composables.pages.MainPage
import com.productslistapp.presentation.ui.theme.ProductsListAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val vm by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val connectionManager = ConnectionManager(applicationContext)

        setContent {
            ProductsListAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (connectionManager.checkConnection()){
                        true -> {
                            when (vm.errorMessagePublic.observeAsState().value){
                                null -> {
                                    MainPage(mainViewModel = vm)
                                }
                                else -> {
                                    ErrorDialog(
                                        onReloadButtonClick = {
                                            finish()
                                            startActivity(intent)
                                             },
                                        title = ErrorConstants.INTERNAL_ERROR,
                                        text = ErrorConstants.TRY_AGAIN_HINT,
                                        errorMsg = vm.errorMessagePublic.observeAsState().value !!
                                    )
                                }
                            }
                        }
                        else -> {
                            ErrorDialog(
                                onReloadButtonClick = {
                                    finish()
                                    startActivity(intent)
                                },
                                title = ErrorConstants.INTERNET_ERROR,
                                text = ErrorConstants.CHECK_CONNECTION_HINT,
                                errorMsg = ErrorConstants.INTERNET_ERROR_MSG
                            )
                        }
                    }
                }
            }
        }
    }
}
