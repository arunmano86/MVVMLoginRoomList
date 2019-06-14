package com.coding.notesapplication.di.module

import android.app.Activity
import dagger.Module
import dagger.Provides

/**
 * Created by Arun on 04/06/2019.
 */
@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    /*@Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }*/

}