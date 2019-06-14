package com.coding.notesapplication.di.component


import com.coding.notesapplication.di.module.FragmentModule
import dagger.Component

/**
 * Created by Arun on 04/06/2019.
 */
@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    //fun inject(aboutFragment: AboutFragment)

    //fun inject(listFragment: ListFragment)

}