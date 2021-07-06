package com.eslammongy.registrationappwithmvvm.ui.home

import androidx.lifecycle.*
import com.eslammongy.registrationappwithmvvm.data.network.Resource
import com.eslammongy.registrationappwithmvvm.data.repository.UserRepository
import com.eslammongy.registrationappwithmvvm.data.responce.LoginResponse
import com.eslammongy.registrationappwithmvvm.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: UserRepository
) : BaseViewModel(repository) {

    private val _user: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val user: LiveData<Resource<LoginResponse>>
        get() = _user

    fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser()
    }

}