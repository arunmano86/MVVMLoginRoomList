package com.coding.notesapplication.app

import android.app.Application
import com.coding.notesapplication.BuildConfig
import com.coding.notesapplication.di.component.ApplicationComponent
import com.coding.notesapplication.di.component.DaggerApplicationComponent
import com.coding.notesapplication.di.module.ApplicationModule

/**
 * Created by Arun on 04/06/2019.
 */
class BaseApp: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()

        if (BuildConfig.DEBUG) {
            // Maybe TimberPlant etc.
        }
    }

    private fun setup() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: BaseApp private set
    }
}