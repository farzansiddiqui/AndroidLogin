package com.siddiqui.androidlogin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.siddiqui.androidlogin.model.UserLogin

class LoginViewModel:ViewModel(){
    val loginResultLiveData:MutableLiveData<Boolean> = MutableLiveData()

    fun login(userLogin: UserLogin){
        val isLoginSuccessful = userLogin.userName.isNotEmpty() && userLogin.userPassword.isNotEmpty()
        loginResultLiveData.postValue(isLoginSuccessful)
    }
    fun isError(userLogin: UserLogin): Boolean {
        return userLogin.userName.isEmpty() && userLogin.userPassword.isEmpty()
    }

}