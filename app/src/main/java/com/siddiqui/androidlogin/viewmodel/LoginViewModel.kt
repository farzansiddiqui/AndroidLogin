package com.siddiqui.androidlogin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.siddiqui.androidlogin.model.UserLogin

class LoginViewModel:ViewModel(){
    val loginResultLiveData:MutableLiveData<Boolean> = MutableLiveData()

    fun login(userName:String, userPassword:String){
        val isLoginSuccessful = userName.isNotEmpty() && userPassword.isNotEmpty()
        loginResultLiveData.postValue(isLoginSuccessful)
    }

}