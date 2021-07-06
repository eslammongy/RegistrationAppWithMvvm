package com.eslammongy.registrationappwithmvvm.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.eslammongy.registrationappwithmvvm.R
import com.eslammongy.registrationappwithmvvm.data.network.Resource
import com.eslammongy.registrationappwithmvvm.data.responce.UserModel
import com.eslammongy.registrationappwithmvvm.databinding.FragmentHomeBinding
import com.eslammongy.registrationappwithmvvm.ui.handleApiError
import com.eslammongy.registrationappwithmvvm.ui.logout
import com.eslammongy.registrationappwithmvvm.ui.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        binding.loginProgressBar.visible(false)

        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    binding.loginProgressBar.visible(false)
                    updateUI(it.value.userModel)
                }
                is Resource.Loading -> {
                    binding.loginProgressBar.visible(true)
                }
                is Resource.Failure -> {
                    handleApiError(it)
                }
            }
        })

        binding.btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun updateUI(user: UserModel) {
        with(binding) {
            contactInputID.setText(user.id.toString())
            contactInputEmail.setText(user.email)
            contactInputName.setText(user.name)
        }
    }
}