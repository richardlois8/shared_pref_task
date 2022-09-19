package com.rich.sharedpreftask

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.rich.sharedpreftask.databinding.FragmentLoginBinding
import com.rich.sharedpreftask.databinding.FragmentRegisterBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var sharedPrefRegis: SharedPreferences
    private lateinit var sharedPrefLogin: SharedPreferences
    private lateinit var editorLogin: SharedPreferences.Editor
    private val sharedNameRegis = "dataUserRegister"
    private val sharedNameLogin = "dataUserLogin"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPrefRegis = requireActivity().getSharedPreferences(sharedNameRegis, 0)
        sharedPrefLogin = requireActivity().getSharedPreferences(sharedNameLogin, 0)
        editorLogin = sharedPrefLogin.edit()

        binding.btnLogin.setOnClickListener {
            checkLogin()
        }

        binding.tvRegister.setOnClickListener {
            gotoRegister()
        }
    }

    fun checkLogin(){
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        val usernameRegis = sharedPrefRegis.getString("username", "")
        val passwordRegis = sharedPrefRegis.getString("password", "")
        if(usernameRegis == "" && passwordRegis == ""){
            Toast.makeText(requireContext(), "Please register first", Toast.LENGTH_SHORT).show()
        }else if (username == usernameRegis && password == passwordRegis) {
            editorLogin.putString("username", username)
            editorLogin.putString("password", password)
            editorLogin.apply()
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }else{
            Toast.makeText(requireContext(), "Username or Password incorrect", Toast.LENGTH_SHORT).show()
        }
    }

    fun gotoRegister(){
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }
}