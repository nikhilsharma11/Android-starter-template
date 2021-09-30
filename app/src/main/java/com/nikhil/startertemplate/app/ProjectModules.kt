package com.nikhil.startertemplate.app

import androidx.room.Database
import androidx.room.Room
import com.nikhil.startertemplate.BuildConfig
import com.nikhil.startertemplate.activity.SplashViewModel
import com.nikhil.startertemplate.common.ConnectivityLiveData
import com.nikhil.startertemplate.common.database.MyDatabase
import com.nikhil.startertemplate.common.models.ProjectErrorJsonAdapter
import com.nikhil.startertemplate.data.DataRepositoryContract
import com.nikhil.startertemplate.data.ProjectDataRepository
import com.nikhil.startertemplate.data.api.ApiContract
import com.nikhil.startertemplate.data.api.ApiService
import com.nikhil.startertemplate.data.api.ProjectAPI
import com.nikhil.startertemplate.data.idlingresource.ProjectIdlingResource
import com.nikhil.startertemplate.data.idlingresource.SimpleProjectIdlingResource
import com.nikhil.startertemplate.data.preferences.PreferencesContract
import com.nikhil.startertemplate.data.preferences.ProjectPreferences
import com.nikhil.startertemplate.screens.host.HostViewModel
import com.nikhil.startertemplate.screens.landing.LandingViewModel
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val allModules
    get() = listOf(
        appModule,
        dataModule,
        hostModule,
        mainModule,
        landingModule
    )

val appModule = module {

    single { ConnectivityLiveData(androidContext()) }
}

val dataModule = module {

    single<ProjectIdlingResource> { SimpleProjectIdlingResource() }

    single<Converter.Factory> { MoshiConverterFactory.create() }

    single<Interceptor> { ChuckInterceptor(androidContext()) }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get())
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .addConverterFactory(get())
            .client(get<OkHttpClient>())
            .baseUrl(BuildConfig.API_URL)
            .build()
    }

    single {
        Moshi.Builder().build()
    }

    single {
        ProjectErrorJsonAdapter(get())
    }

    single<ApiContract> {
        val retrofitApiService = get<Retrofit>().create(ApiService::class.java)
        ProjectAPI(retrofitApiService)
    }

    single {
        Room.databaseBuilder(androidApplication(), MyDatabase::class.java, "myDatabase")
            .build()
    }
    single { get<MyDatabase>().projectDao() }

    single<PreferencesContract> { ProjectPreferences(androidContext()) }

    single<DataRepositoryContract> { ProjectDataRepository(get(), get()) }
}

val hostModule = module {
    viewModel {
        HostViewModel(get())
    }
}

val landingModule = module {
    viewModel {
        LandingViewModel(
            get(), get()
        )
    }
}

val mainModule = module {
    viewModel {
        SplashViewModel()
    }
}