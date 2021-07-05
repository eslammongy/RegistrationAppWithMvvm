package com.eslammongy.registrationappwithmvvm.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.eslammongy.registrationappwithmvvm.data.UserPreferences
import com.eslammongy.registrationappwithmvvm.data.network.RemoteDataSource
import com.eslammongy.registrationappwithmvvm.data.repository.BaseRepository

abstract class BaseFragment<viewModel:ViewModel , viewBinding: ViewBinding , baseRepository: BaseRepository>:Fragment(){

    protected  lateinit var binding: viewBinding
    protected lateinit var userPreferences: UserPreferences
    protected lateinit var viewModel:viewModel
    protected val remoteDateSource = RemoteDataSource()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userPreferences = UserPreferences(requireContext())
         binding = getFragmentBinding(inflater , container)
         val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this , factory).get(getViewModel())
        return binding.root
    }

    abstract fun getViewModel(): Class<viewModel>

    abstract  fun getFragmentBinding(inflater: LayoutInflater , container: ViewGroup?):viewBinding

    abstract fun getFragmentRepository(): baseRepository

}