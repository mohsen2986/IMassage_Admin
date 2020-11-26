package com.example.imassage_admin.data.dataSource.order

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.imassage_admin.data.repository.DataRepository
import kotlinx.coroutines.CoroutineScope

class OrderDataSourceFactory<T>(
    private val repository: DataRepository ,
    private var query: String ,
    private val scope: CoroutineScope
):DataSource.Factory<Int , T>(){
    val source = MutableLiveData<OrderDataSource<T>>()

    override fun create(): DataSource<Int, T> {
        val source = OrderDataSource<T>(repository , query, scope)
        this.source.postValue(source)
        return source
    }

    // --- PUBLIC API
    fun getSource() = source.value
    fun getQuery() = query

    fun updateQuery(query:String){
        this.query = query
        getSource()?.refresh()
    }

}