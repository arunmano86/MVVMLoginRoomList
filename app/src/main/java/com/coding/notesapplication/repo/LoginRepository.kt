package com.coding.notesapplication.repo

import android.app.Application
import android.util.Log
import com.coding.notesapplication.data.local.dao.LoginDao
import com.coding.notesapplication.data.local.db.AppDatabase
import com.coding.notesapplication.models.Login
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

class LoginRepository {

    public var loginDao: LoginDao

    constructor(application : Application) {
        var appDatabase: AppDatabase = AppDatabase.invoke(application)
        loginDao = appDatabase.loginDao()
    }

    fun isLogin(emailAddress: String, password: String) : Single<Login> {
        return loginDao.isLogin(emailAddress, password)
    }
}