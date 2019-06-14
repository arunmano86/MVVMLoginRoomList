package com.coding.notesapplication.ui.login

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.coding.notesapplication.models.Login
import com.coding.notesapplication.repo.LoginRepository
import io.reactivex.Single

class LoginViewModel: AndroidViewModel {

    var emailAddressVM : MutableLiveData<String> = MutableLiveData()
    var passwordVM : MutableLiveData<String> = MutableLiveData()
    private lateinit var loginLiveData : MutableLiveData<Login>

    public var loginRepoInstance: LoginRepository

    //var loginRepoInstance: LoginRepository = LoginRepository(getApplication())

    constructor(application: Application) : super(application)

    init {
        loginRepoInstance = LoginRepository(getApplication())

        emailAddressVM.value = "arun@gmail.com"
        passwordVM.value = "123456"
    }

    fun isLogin(emailAddress: String, password: String) : Single<Login> {
        return loginRepoInstance.isLogin( emailAddress, password )
    }

    fun getUser() : MutableLiveData<Login> {
        if (!::loginLiveData.isInitialized) {
            loginLiveData = MutableLiveData()
        }
        return  loginLiveData
    }

    fun onClickLogin(view : View) {
        loginLiveData.value = Login(emailAddressVM.value.orEmpty(), passwordVM.value.orEmpty())
    }
}