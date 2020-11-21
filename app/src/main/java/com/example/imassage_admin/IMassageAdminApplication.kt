package com.example.imassage_admin

import android.app.Application
import com.example.imassage_admin.data.model.AboutUs
import com.example.imassage_admin.data.remote.Service
import com.example.imassage_admin.data.remote.api.ApiInterface
import com.example.imassage_admin.data.repository.DataRepository
import com.example.imassage_admin.ui.fragment.aboutUs.AboutUsViewModelFactory
import com.example.imassage_admin.ui.fragment.mainPage.MainPageViewModelFactory
import com.facebook.stetho.Stetho
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class IMassageAdminApplication(
) : Application() , KodeinAware{
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@IMassageAdminApplication))

        bind<ApiInterface>() with singleton { Service().invoke()}

        // repository
        bind() from singleton { DataRepository(instance()) }
        // fragment
        bind() from provider { MainPageViewModelFactory() }
        bind() from provider { AboutUsViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

    }
}