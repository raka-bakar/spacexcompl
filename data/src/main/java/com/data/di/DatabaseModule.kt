package com.data.di

import android.content.Context
import androidx.room.Room
import com.data.persist.CacheDatabase
import com.data.persist.Converter
import com.data.persist.LaunchDao
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class DatabaseModule {
    /**
     * provides room database instance
     */
    @Singleton
    @Provides
    fun providesRoomDatabase(context: Context, moshi: Moshi): CacheDatabase {
        return Room.databaseBuilder(
            context,
            CacheDatabase::class.java,
            "cache"
        ).addTypeConverter(Converter(moshi))
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideLaunchDao(cacheDatabase: CacheDatabase): LaunchDao {
        return cacheDatabase.feedDao()
    }
}