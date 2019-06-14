package com.coding.notesapplication.di.component

import com.coding.notesapplication.app.BaseApp
import com.coding.notesapplication.di.module.ApplicationModule
import dagger.Component

/**
 * Created by Arun on 04/06/2019.
 */
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: BaseApp)

}