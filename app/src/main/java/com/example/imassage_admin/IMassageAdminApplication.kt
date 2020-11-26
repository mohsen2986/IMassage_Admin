package com.example.imassage_admin

import android.app.Application
import com.example.imassage_admin.data.model.AboutUs
import com.example.imassage_admin.data.remote.Service
import com.example.imassage_admin.data.remote.api.ApiInterface
import com.example.imassage_admin.data.repository.DataRepository
import com.example.imassage_admin.ui.fragment.aboutUs.AboutUsViewModelFactory
import com.example.imassage_admin.ui.fragment.config.ConfigTimes.ConfigTimesViewModelFactory
import com.example.imassage_admin.ui.fragment.config.ConfigViewModelFactory
import com.example.imassage_admin.ui.fragment.history.HistoryViewModelFactory
import com.example.imassage_admin.ui.fragment.mainPage.MainPageViewModelFactory
import com.example.imassage_admin.ui.fragment.massage.MassageViewModelFactory
import com.example.imassage_admin.ui.fragment.massage.addMassage.AddMassageViewModelFactory
import com.example.imassage_admin.ui.fragment.packages.PackageViewModelFactory
import com.example.imassage_admin.ui.fragment.packages.addPakcage.AddPackageViewModelFactory
import com.example.imassage_admin.ui.fragment.question.QuestionViewModelFactory
import com.example.imassage_admin.ui.fragment.slider.SliderViewModelFactory
import com.example.imassage_admin.ui.fragment.slider.addSlider.AddSliderFragment
import com.example.imassage_admin.ui.fragment.slider.addSlider.AddSliderViewModelFactory
import com.example.imassage_admin.ui.fragment.users.UsersViewModelFactory
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
        bind() from provider { SliderViewModelFactory(instance())}
        bind() from provider { MassageViewModelFactory(instance())}
        bind() from provider { PackageViewModelFactory(instance())}
        bind() from provider { QuestionViewModelFactory(instance()) }
        bind() from provider { UsersViewModelFactory(instance()) }
        bind() from provider { AddSliderViewModelFactory(instance()) }
        bind() from provider { AddPackageViewModelFactory(instance()) }
        bind() from provider { AddMassageViewModelFactory(instance()) }
        bind() from provider { ConfigViewModelFactory(instance()) }
        bind() from provider { ConfigTimesViewModelFactory(instance()) }
        bind() from provider { HistoryViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

    }
}