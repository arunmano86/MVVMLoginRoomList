package com.coding.notesapplication.di.component

import com.coding.notesapplication.di.module.ActivityModule
import dagger.Component

/**
 * Created by Arun on 04/06/2019.
 */
@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    //fun inject(mainActivity: MainActivity)

}