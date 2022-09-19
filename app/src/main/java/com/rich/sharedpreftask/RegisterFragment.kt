package com.rich.sharedpreftask

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.rich.sharedpreftask.databinding.FragmentRegisterBinding
import com.rich.sharedpreftask.databinding.FragmentSplashScreenBinding

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var sharedPref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val sharedNameRegis = "dataUserRegister"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = requireActivity().getSharedPreferences(sharedNameRegis, MODE_PRIVATE)
        editor = sharedPref.edit()

        binding.btnRegister.setOnClickListener {
            registerUser()
        }
    }

    fun registerUser(){
        val username = binding.etUsername.text.toString()
        val name = binding.etName.text.toString()
        val pass = binding.etPassword.text.toString()
        val passConfirm = binding.etPasswordConfirm.text.toString()

        if(pass.equals(passConfirm)){
            editor.putString("username", username)
            editor.putString("name", name)
            editor.putString("password", pass)
            editor.apply()
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }else{
            Toast.makeText(requireContext(), "Password confirmation does not match", Toast.LENGTH_SHORT).show()
        }
    }
}