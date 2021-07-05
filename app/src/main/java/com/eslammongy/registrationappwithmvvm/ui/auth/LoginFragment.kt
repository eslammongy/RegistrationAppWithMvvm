package com.eslammongy.registrationappwithmvvm.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.eslammongy.registrationappwithmvvm.databinding.FragmentLoginBinding
import com.eslammongy.registrationappwithmvvm.data.network.AuthApi
import com.eslammongy.registrationappwithmvvm.data.network.Resource
import com.eslammongy.registrationappwithmvvm.data.repository.AuthRepository
import com.eslammongy.registrationappwithmvvm.ui.base.BaseFragment
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<AuthViewModel , FragmentLoginBinding , AuthRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loginResponse.observe(viewLifecycleOwner , {
            when(it){
                is Resource.Success -> {
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                    lifecycleScope.launch {
                        userPreferences.saveAuthToken(it.value.userModel.access_token!!)
                    }
                }
                is Resource.Failure ->{
                    Toast.makeText(requireContext(), "Login Failure ...", Toast.LENGTH_SHORT).show()
                }
            }
        })
        binding.btnUserLogin.setOnClickListener {
            val email = binding.inputEmail.text.toString().trim()
            val password = binding.inputPassword.text.toString().trim()
            Toast.makeText(requireContext(), "$email ... $password", Toast.LENGTH_SHORT).show()
            viewModel.userLogin(email , password)
        }
    }
    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater , container , false)

    override fun getFragmentRepository() = AuthRepository(remoteDateSource.buildAPi(AuthApi::class.java))


}