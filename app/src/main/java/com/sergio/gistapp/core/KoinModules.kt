package com.sergio.gistapp.core

import com.google.gson.GsonBuilder
import com.sergio.gistapp.gist.list.GistListViewModel
import com.sergio.gistapp.gist.repository.GistRepository
import com.sergio.gistapp.gist.service.DynamicFileDto
import com.sergio.gistapp.gist.service.GistApiService
import com.sergio.gistapp.gist.util.DynamicDtoDeserializer
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://api.github.com/"

val appModule = module {
    viewModel {
        GistListViewModel(get())
    }

    single {
        Retrofit.Builder().baseUrl(BASE_URL).client(OkHttpClient())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().registerTypeAdapter(
                        DynamicFileDto::class.java,
                        DynamicDtoDeserializer()
                    )
                        .create()
                )
            ).build()
    }

    factory { provideGistApi(get()) }

    factory { GistRepository(get()) }


}

fun provideGistApi(retrofit: Retrofit): GistApiService = retrofit.create(GistApiService::class.java)


