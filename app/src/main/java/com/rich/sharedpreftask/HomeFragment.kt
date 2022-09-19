package com.rich.sharedpreftask

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rich.sharedpreftask.databinding.FragmentHomeBinding
import com.rich.sharedpreftask.databinding.FragmentLoginBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
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
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPrefLogin = requireActivity().getSharedPreferences(sharedNameLogin, MODE_PRIVATE)
        sharedPrefRegis = requireActivity().getSharedPreferences(sharedNameRegis, MODE_PRIVATE)
        editorLogin = sharedPrefLogin.edit()

        binding.tvName.text = sharedPrefRegis.getString("name", "")
        binding.btnLogout.setOnClickListener{
            logout()
        }
    }

    fun logout(){
        editorLogin.clear()
        editorLogin.apply()
        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
    }
}