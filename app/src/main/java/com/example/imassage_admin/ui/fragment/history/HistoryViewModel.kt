package com.example.imassage_admin.ui.fragment.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.imassage_admin.data.dataSource.ItemDataSourceFactory
import com.example.imassage_admin.data.dataSource.order.OrderDataSource
import com.example.imassage_admin.data.dataSource.order.OrderDataSourceFactory
import com.example.imassage_admin.data.model.Massage
import com.example.imassage_admin.data.model.Order
import com.example.imassage_admin.data.remote.model.NetworkState
import com.example.imassage_admin.data.repository.DataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

class HistoryViewModel(
        private val dataRepository: DataRepository
) : ViewModel() {

    // DATA
    protected val ioScope = CoroutineScope(Dispatchers.IO)

    private val itemDataSource = OrderDataSourceFactory<Order>(repository = dataRepository , query = "", scope = ioScope)

    // OBSERVABLES
    val users = LivePagedListBuilder(itemDataSource ,pagedListConfig()).build()
    val networkState : LiveData<NetworkState>? =
            Transformations.switchMap(itemDataSource.source) { it.getNetworkState() }


    fun fetchQuery(query:String){
        if(itemDataSource.getQuery() == query) return
        itemDataSource.updateQuery(query = query)
    }
    fun getQuery() = itemDataSource.getQuery()
    // refresh all list after an issue
    fun refreshAllList() = itemDataSource.getSource()?.refresh()

    // UTILS
    private fun pagedListConfig() =
            PagedList.Config.Builder()
                    .setInitialLoadSizeHint(5)
                    .setEnablePlaceholders(false)
                    .setPageSize(5)
                    .build()

    override fun onCleared() {
        super.onCleared()
        ioScope.coroutineContext.cancel()
    }

    suspend fun users(page: Int?) =
            dataRepository.users(page)

}