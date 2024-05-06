package com.productslistapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.productslistapp.domain.models.Product
import com.productslistapp.domain.models.RequestParams
import com.productslistapp.domain.models.ResponseResult
import com.productslistapp.domain.usecase.GetAllCategoriesUseCase
import com.productslistapp.domain.usecase.GetProductDetailsUseCase
import com.productslistapp.domain.usecase.GetProductsByCategoryUseCase
import com.productslistapp.domain.usecase.GetProductsUseCase
import com.productslistapp.domain.usecase.SearchProductUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel(
    private val getProductDetailsUseCase : GetProductDetailsUseCase,
    private val getProductsByCategory : GetProductsByCategoryUseCase,
    private val getProductsUseCase : GetProductsUseCase,
    private val searchProductUseCase : SearchProductUseCase,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase
) : ViewModel() {

    private val productsListPrivate = MutableLiveData<List<Product>>()
    val productsList: LiveData<List<Product>> = productsListPrivate

    private val categoriesListPrivate = MutableLiveData<List<String>>()
    val categoriesList: LiveData<List<String>> = categoriesListPrivate

    private val currentProductPrivate = MutableLiveData<Product>()
    val currentProduct: LiveData<Product> = currentProductPrivate

    private val currentCategoryPrivate = MutableLiveData<String>()
    val currentCategory = currentCategoryPrivate

    private var requestParamsPrivate = RequestParams(skip = 0, limit = 20)
    val requestParams = requestParamsPrivate

    private val errorMessagePrivate = MutableLiveData<String>()
    val errorMessagePublic = errorMessagePrivate

    private var isLoadingPrivate = MutableLiveData<Boolean>(true)
    val isLoading = isLoadingPrivate

    private var totalPrivate = MutableLiveData<Int>(0)
    val total = totalPrivate

    init{
        //Получение списка продуктов
        getAllProducts()
        //Получение списка категорий
        CoroutineScope(Dispatchers.IO).launch{
            val result = getCategoriesCall()
            categoriesListPrivate.postValue(result)
        }
        isLoadingPrivate.postValue(false)
    }

    //Переключение страниц
    fun nextPage(){
        requestParams.skip = requestParams.skip+20
        getAllProducts()
    }
    fun previousPage(){
        requestParams.skip = requestParams.skip-20
        getAllProducts()
    }

    //Функциональность для usecase
    fun getAllProducts(){
        CoroutineScope(Dispatchers.IO).launch{
            val result = getProductsCall(requestParams)
            if (result.errorMessage != null){
                errorMessagePrivate.postValue(result.errorMessage!!)
            } else{
                if (result.productsList != null){
                    productsListPrivate.postValue(result.productsList!!)
                } else{
                    errorMessagePrivate.postValue("Can't load data")
                }
                if (result.totalValue != null){
                    totalPrivate.postValue(result.totalValue!!)
                }
            }
        }
    }
    fun getProductDetails(id: Int){
        CoroutineScope(Dispatchers.IO).launch{
            currentProductPrivate.postValue(getProductDetailsCall(id))
        }
    }
    fun searchProduct(searchRequest: String){
        CoroutineScope(Dispatchers.IO).launch{
            val result = searchProductCall(searchRequest)
            if (result.errorMessage != null){
                errorMessagePrivate.postValue(result.errorMessage!!)
            } else{
                if (result.productsList != null){
                    productsListPrivate.postValue(result.productsList!!)
                } else{
                    errorMessagePrivate.postValue("Can't load data")
                }
                if (result.totalValue != null){
                    totalPrivate.postValue(result.totalValue!!)
                }
            }
        }
    }
    fun getProductsByCategory(category: String){
        currentCategoryPrivate.postValue(category)
        CoroutineScope(Dispatchers.IO).launch{
            val result = getProductsByCategoryCall(category)
            if (result.errorMessage != null){
                errorMessagePrivate.postValue(result.errorMessage!!)
            } else{
                if (result.productsList != null){
                    productsListPrivate.postValue(result.productsList!!)
                } else{
                    errorMessagePrivate.postValue("Can't load data")
                }
                if (result.totalValue != null){
                    totalPrivate.postValue(result.totalValue!!)
                }
            }
        }
    }

    //Запросы в сеть
    private suspend fun getProductsCall(requestParams: RequestParams): ResponseResult{
        val job = CoroutineScope(Dispatchers.IO).async {
            getProductsUseCase.execute(requestParams)
        }
        this.requestParamsPrivate = requestParams
        return job.await()
    }
    private suspend fun getProductDetailsCall(id:Int): Product{
        val job = CoroutineScope(Dispatchers.IO).async {
            getProductDetailsUseCase.execute(id)
        }
        return job.await()
    }
    private suspend fun searchProductCall(searchRequest: String): ResponseResult{
        val job = CoroutineScope(Dispatchers.IO).async {
            searchProductUseCase.execute(searchRequest)
        }
        return job.await()
    }
    private suspend fun getCategoriesCall(): List<String>{
        val job = CoroutineScope(Dispatchers.IO).async {
            getAllCategoriesUseCase.execute()
        }
        return job.await()
    }
    private suspend fun getProductsByCategoryCall(category: String): ResponseResult{
        val job = CoroutineScope(Dispatchers.IO).async {
            getProductsByCategory.execute(category)
        }
        return job.await()
    }
}