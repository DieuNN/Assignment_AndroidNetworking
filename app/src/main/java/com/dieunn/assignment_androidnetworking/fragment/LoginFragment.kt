package com.dieunn.assignment_androidnetworking.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.dieunn.assignment_androidnetworking.ApiInstance
import com.dieunn.assignment_androidnetworking.R
import com.dieunn.assignment_androidnetworking.UserInfoInstance
import com.dieunn.assignment_androidnetworking.databinding.FragmentLoginBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater)
        navController = findNavController()
        return binding.root


    }


    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnToSignUpFragment.setOnClickListener {
            navController.navigate(R.id.signUpFragment)
        }


        binding.btnLogin.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                login()
            }

        }
    }

    private suspend fun login() {
        if (validateForm()) {
            val numberOfRecord = ApiInstance.api.getNumberOfRecord(
                binding.txtUsername.text.toString(),
                binding.txtPassword.text.toString()
            )
            if (numberOfRecord == "\"1\"") {
                Toast.makeText(requireContext(), "Đăng nhập thành công!", Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.homeFragment)
                UserInfoInstance.username = binding.txtUsername.text.toString()
                return
            }

            Toast.makeText(requireContext(), "Tên tài khoản hoặc mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show()
            return
        }
    
    }

    private fun validateForm(): Boolean {
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
    }


}