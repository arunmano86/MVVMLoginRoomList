package com.coding.notesapplication.ui.login

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.coding.notesapplication.R
import com.coding.notesapplication.models.Login
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginActivity: AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: com.coding.notesapplication.databinding.ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.let {
            it.loginViewModel = viewModel
            it.lifecycleOwner = this
        }

        viewModel.getUser().observe(this, object: Observer<Login> {
            override fun onChanged(login: Login) {
                binding.loginUserNameTextInputLayout.error = null
                binding.loginPasswordTextInputLayout.error = null

                if (TextUtils.isEmpty(login.emailAddress)) {
                    binding.loginUserNameTextInputLayout.error = "Enter an E-Mail Address"
                    binding.loginUserNameTextInputET.requestFocus()
                } else if (!login.isEmailValid()) {
                    binding.loginUserNameTextInputLayout.error = "Enter a Valid E-mail Address"
                    binding.loginUserNameTextInputET.requestFocus()
                } else if (TextUtils.isEmpty(login.password)) {
                    binding.loginPasswordTextInputLayout.error = "Enter a Password"
                    binding.loginPasswordTextInputET.requestFocus()
                } else if (!login.isPasswordLengthGreaterThan5()) {
                    binding.loginPasswordTextInputLayout.error = "Enter a Password length greater than 5 char"
                    binding.loginPasswordTextInputET.requestFocus()
                } else {
                    viewModel.isLogin(login.emailAddress, login.password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ login: Login ->
                            Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_LONG).show()
                        }, { error ->
                            Toast.makeText(baseContext, "Incorrect Username and Password", Toast.LENGTH_LONG).show()
                            binding.loginUserNameTextInputLayout.error = "Incorrect Username and Password"
                        })
                    /*viewModel.loginRepoInstance.isLogin(login.emailAddress, login.password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ login: Login ->
                            Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_LONG).show()
                        }, { error ->
                            Toast.makeText(baseContext, "Incorrect Username and Password", Toast.LENGTH_LONG).show()
                            binding.loginUserNameTextInputLayout.error = "Incorrect Username and Password"
                        })*/
                }
            }
        })
    }

    override fun onStop() {
        binding.lifecycleOwner = null
        super.onStop()
    }
}