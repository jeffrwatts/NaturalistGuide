package com.jeffrwatts.naturalistguide.di

import android.content.Context
import androidx.room.Room
import com.jeffrwatts.naturalistguide.data.species.SpeciesDao
import com.jeffrwatts.naturalistguide.data.species.NaturalistDatabase
import com.jeffrwatts.naturalistguide.network.SpeciesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
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
    fun provideSpeciesApi(): SpeciesApi {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.inaturalist.org/")  // Base URL for iNaturalist API
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(SpeciesApi::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NaturalistDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NaturalistDatabase::class.java,
            "species_database"
        ).build()
    }

    @Provides
    fun provideSpeciesDao(database: NaturalistDatabase): SpeciesDao {
        return database.speciesDao()
    }
}