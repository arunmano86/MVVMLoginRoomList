package com.coding.notesapplication.di.module

import android.app.Application
import com.coding.notesapplication.app.BaseApp
import com.coding.notesapplication.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Arun on 04/06/2019.
 */
@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }
}