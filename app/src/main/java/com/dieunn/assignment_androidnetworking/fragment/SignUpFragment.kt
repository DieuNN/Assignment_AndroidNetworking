package com.dieunn.assignment_androidnetworking.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.dieunn.assignment_androidnetworking.ApiInstance
import com.dieunn.assignment_androidnetworking.R
import com.dieunn.assignment_androidnetworking.databinding.FragmentSignUpBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SignUpFragment : Fragment() {
    private lateinit var binding : FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater)
        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignUp.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                signUp()
            }
        }
    }

    private suspend fun signUp() {
        if (validateForm()) {
            val response = ApiInstance.api.registerUser(
                binding.txtUsername.text.toString(),
                binding.txtRePassword.text.toString()
            )
            Toast.makeText(requireContext(), response, Toast.LENGTH_SHORT).show()

            if (response == "Đăng ký tài khoản thành công") {
                findNavController().navigate(R.id.loginFragment)
            }
        }
    }

    private fun validateForm() : Boolean {
        removeErrorOnTextChange()
        if (binding.txtUsername.text.isNullOrBlank()) {
            binding.inpLayoutUsername.error = "Bạn chưa nhập username"
            return false
        }
        if (binding.txtPassword.text.isNullOrBlank()) {
            binding.inpLayoutPassword.error = "Bạn chưa nhập password"
            return false
        }


        return true
    }

    private fun removeErrorOnTextChange() {
        binding.txtUsername.addTextChangedListener {
            binding.inpLayoutUsername.error = null
        }

        binding.txtPassword.addTextChangedListener {
            binding.inpLayoutPassword.error = null
        }
        binding.txtRePassword.addTextChangedListener {
            binding.inpLayoutRePassword.error = null
        }
    }
}