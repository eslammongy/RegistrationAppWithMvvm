package com.eslammongy.registrationappwithmvvm.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslammongy.registrationappwithmvvm.data.network.Resource
import com.eslammongy.registrationappwithmvvm.data.repository.AuthRepository
import com.eslammongy.registrationappwithmvvm.data.responce.LoginResponse
import kotlinx.coroutines.launch

class AuthViewModel (private val repository: AuthRepository):ViewModel(){

    private val _loginResponse:MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse:LiveData<Resource<LoginResponse>>
    get() = _loginResponse
    fun userLogin(email:String , password:String)= viewModelScope.launch {
        _loginResponse.value = repository.login(email , password)
    }
}