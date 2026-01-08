package com.skure.app.di

import com.skure.app.api.ChatGPTApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.dnsoverhttps.DnsOverHttps
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        
        // DNS over HTTPS via Cloudflare to mitigate host resolution failures
        val bootstrapClient = OkHttpClient.Builder().build()
        val doh = DnsOverHttps.Builder().client(bootstrapClient)
            .url("https://cloudflare-dns.com/dns-query".toHttpUrl())
            .build()

        val openRouterHeaders = Interceptor { chain ->
            val req = chain.request().newBuilder()
                // Recommended by OpenRouter for better reliability/metrics
                .header("HTTP-Referer", "https://skure.app")
                .header("X-Title", "Skure")
                .build()
            chain.proceed(req)
        }

        return OkHttpClient.Builder()
            .dns(doh)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(openRouterHeaders)
            .retryOnConnectionFailure(true)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://openrouter.ai/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    
    @Provides
    @Singleton
    fun provideChatGPTApiService(retrofit: Retrofit): ChatGPTApiService {
        return retrofit.create(ChatGPTApiService::class.java)
    }
}






