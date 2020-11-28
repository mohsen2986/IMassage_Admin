package com.example.imassage_admin

import android.app.Application
import com.example.imassage_admin.data.remote.*
import com.example.imassage_admin.data.remote.api.ApiInterface
import com.example.imassage_admin.data.remote.api.AuthApiInterface
import com.example.imassage_admin.data.repository.DataRepository
import com.example.imassage_admin.data.repository.TokenRepository
import com.example.imassage_admin.data.repository.UserInformationRepository
import com.example.imassage_admin.ui.fragment.aboutUs.AboutUsViewModelFactory
import com.example.imassage_admin.ui.fragment.config.ConfigTimes.ConfigTimesViewModelFactory
import com.example.imassage_admin.ui.fragment.config.ConfigViewModelFactory
import com.example.imassage_admin.ui.fragment.history.HistoryViewModelFactory
import com.example.imassage_admin.ui.fragment.login.LoginViewModelFactory
import com.example.imassage_admin.ui.fragment.mainPage.MainPageViewModelFactory
import com.example.imassage_admin.ui.fragment.massage.MassageViewModelFactory
import com.example.imassage_admin.ui.fragment.massage.addMassage.AddMassageViewModelFactory
import com.example.imassage_admin.ui.fragment.offer.OfferViewModelFactory
import com.example.imassage_admin.ui.fragment.offer.addOffer.AddOfferViewModelFactory
import com.example.imassage_admin.ui.fragment.packages.PackageViewModelFactory
import com.example.imassage_admin.ui.fragment.packages.addPakcage.AddPackageViewModelFactory
import com.example.imassage_admin.ui.fragment.phoneVerification.PhoneVerificationViewModelFactory
import com.example.imassage_admin.ui.fragment.question.QuestionViewModelFactory
import com.example.imassage_admin.ui.fragment.question.addQuestion.AddQuestionViewModelFactory
import com.example.imassage_admin.ui.fragment.registerForm.RegisterFormViewModelFactory
import com.example.imassage_admin.ui.fragment.showAnswers.ShowAnswersViewModelFactory
import com.example.imassage_admin.ui.fragment.slider.SliderViewModelFactory
import com.example.imassage_admin.ui.fragment.slider.addSlider.AddSliderViewModelFactory
import com.example.imassage_admin.ui.fragment.users.UsersViewModelFactory
import com.facebook.stetho.Stetho
import com.imassage.data.database.sharedPreferences.Preferences
import com.imassage.ui.fragment.signUp.SignUpViewModelFactory
import com.example.imassage_admin.ui.fragment.splashScreen.SplashScreenViewModelFactory
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

class IMassageAdminApplication(
) : Application() , KodeinAware{
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@IMassageAdminApplication))

        bind() from singleton { Preferences(applicationContext) }

        // network
        bind<OkHttpClient>() with singleton { Client().invoke()}
        bind<Retrofit>() with singleton { RetrofitBuilder(instance()).invoke() }
        bind<ApiInterface>() with singleton { CreateApiInterface(instance()).invoke() }
        // auth client
        bind() from singleton { TokenInterceptor(instance() , instance() , instance()) }
        bind() from singleton { TokenAuthenticator(instance() , instance()) }
        bind<AuthApiInterface>() with singleton { CreateAuthApiInterface(instance() , instance()).invoke() }

        // repository
        bind() from singleton { UserInformationRepository(instance() , instance()) }
        bind() from singleton { TokenRepository(instance()) }
        bind() from singleton { DataRepository(instance()) }

        // fragment
        bind() from provider { SplashScreenViewModelFactory(instance() , instance()) }
        bind() from provider { SignUpViewModelFactory() }
        bind() from provider { RegisterFormViewModelFactory(instance()) }
        bind() from provider { LoginViewModelFactory(instance()) }
        bind() from provider { PhoneVerificationViewModelFactory(instance()) }

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
        bind() from provider { AddQuestionViewModelFactory(instance())}
        bind() from provider { ShowAnswersViewModelFactory(instance())}
        bind() from provider { OfferViewModelFactory(instance())}
        bind() from provider { AddOfferViewModelFactory(instance())}
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

    }
}