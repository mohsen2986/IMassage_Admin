package com.example.imassage_admin.data.dataSource.order

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.imassage_admin.data.remote.model.NetworkState
import com.example.imassage_admin.data.repository.DataRepository
import com.example.imassage_admin.ui.utils.StaticVariables
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.*

class OrderDataSource<T>(
    private val repository: DataRepository ,
    private val query:String ,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, T>() {
    // FOR DATA---
    private val supervisorJob = SupervisorJob()
    private val networkState = MutableLiveData<NetworkState>()
    private var retryQuery: (() -> Any)? = null // Keep reference of the last query (to be able to retry it if necessary)
    private var pages :Int ?= null

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, T>
    ) {
        retryQuery = { loadInitial(params, callback) }
        loadInitial {
            callback.onResult(it , null , if(pages == 1) null else 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        val page = params.key
        retryQuery = {loadAfter(params , callback)}
        executeQuery(page){
            callback.onResult( it , if((page+1) == pages ) null else page+1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {}
    // UTILS--
    private fun executeQuery(page:Int , callBack:(List<T>) -> Unit ){
        networkState.postValue(NetworkState.RUNNING)
        scope.launch (getJobErrorHandler() + supervisorJob){
            delay(200)
            val request = if(query == StaticVariables.HISTORY) repository.orders(page , null)
                        else if(query == StaticVariables.RESERVE_TIME)repository.reservedOrders(page)
                        else repository.orders(page , query)

            retryQuery = null
            networkState.postValue(NetworkState.SUCCESS)
            when(request){
                is NetworkResponse.Success ->{
                    callBack((request.body.data) as List<T>)
                    }
                else -> Log.e("Log__" , "issue")
                }
            }
        }

    private fun loadInitial(callBack:(List<T>) -> Unit ){
        scope.launch (getJobErrorHandler() + supervisorJob){
            val callback_ =  if(query == StaticVariables.HISTORY) repository.orders(0 , null)
                        else if(query == StaticVariables.RESERVE_TIME)repository.reservedOrders(0)
                        else repository.orders(0 , query)

            when(callback_){
                is NetworkResponse.Success -> pages = callback_.body.metadata.pagination.totalPages
            }
//            pages = repository.users(query).
            executeQuery(0) {
                callBack(it)
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        Log.e(OrderDataSource::class.java.simpleName, "An error happened: $e")
        networkState.postValue(NetworkState.FAILED)
    }

    override fun invalidate() {
        super.invalidate()
        supervisorJob.cancelChildren()   // Cancel possible running job to only keep last result searched by user
    }

    // PUBLIC API ---

    fun getNetworkState(): LiveData<NetworkState> =
        networkState

    fun refresh() =
        this.invalidate()


}