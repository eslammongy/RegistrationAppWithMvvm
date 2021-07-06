package com.eslammongy.registrationappwithmvvm.ui.auth

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.eslammongy.registrationappwithmvvm.R
import com.eslammongy.registrationappwithmvvm.databinding.FragmentLoginBinding
import com.eslammongy.registrationappwithmvvm.data.network.Resource
import com.eslammongy.registrationappwithmvvm.ui.enable
import com.eslammongy.registrationappwithmvvm.ui.handleApiError
import com.eslammongy.registrationappwithmvvm.ui.home.HomeActivity
import com.eslammongy.registrationappwithmvvm.ui.startNewActivity
import com.eslammongy.registrationappwithmvvm.ui.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment: Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        binding.loginProgressBar.visible(false)
        binding.loginProgressBar.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.loginProgressBar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAccessTokens(
                            it.value.userModel.access_token!!,
                            it.value.userModel.refresh_token!!
                        )
                        requireActivity().startNewActivity(HomeActivity::class.java)
                    }
                }
                is Resource.Failure -> handleApiError(it) { login() }

            }
        })

        binding.inputPassword.addTextChangedListener{
            val email = binding.inputEmail.text.toString().trim()
            binding.btnUserLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.btnUserLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val email = binding.inputEmail.text.toString().trim()
        val password = binding.inputPassword.text.toString().trim()
        viewModel.login(email, password)
    }
}