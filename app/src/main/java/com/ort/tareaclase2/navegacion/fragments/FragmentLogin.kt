package com.ort.tareaclase2.navegacion.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ort.tareaclase2.R
import com.ort.tareaclase2.entities.User
import com.ort.tareaclase2.navegacion.viewmodels.FragmentLoginViewModel

class FragmentLogin : Fragment() {

    companion object {
        fun newInstance() = FragmentLogin()
    }

    private lateinit var viewModel: FragmentLoginViewModel
    private lateinit var v : View
    private lateinit var inputEmail : EditText
    private lateinit var inputPass : EditText
    private lateinit var btnLogin : Button
    private lateinit var layout : ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_login_fragment, container, false)
        inputEmail = v.findViewById(R.id.inputEmail)
        inputPass = v.findViewById(R.id.inputPass)
        btnLogin = v.findViewById(R.id.btnLogin)
        layout = v.findViewById(R.id.layoutLogin)
        return v
    }

    override fun onStart() {
        super.onStart()

        var users : MutableList<User> = mutableListOf()
        users.add(User("jorge@ort.com", "123", "Jorge"))
        users.add(User("dan@ort.com", "123", "Dan"))
        users.add(User("tadeo@ort.com", "123", "Tadeo"))
        users.add(User("tom@ort.com", "123", "Tom"))
        users.add(User("jorge1@ort.com", "456", "Jorge1"))
        users.add(User("dan1@ort.com", "456", "Dan1"))
        users.add(User("tadeo1@ort.com", "456", "Tadeo1"))
        users.add(User("tom1@ort.com", "456", "Tom1"))
        users.add(User("admin@ort.com", "admin", "admin"))
        users.add(User("service@ort.com", "service", "servicio"))

        btnLogin.setOnClickListener {
            if(inputEmail.length() > 0 && inputPass.length() > 0) {
                var search = buscarUsuario(users, inputEmail.text.toString(), inputPass.text.toString())
                if(search.name.length > 0){
                    val action = FragmentLoginDirections.actionFragmentLoginToFragmentBienvenida(search.name)
                    v.findNavController().navigate(action)
                } else {
                    Snackbar.make(layout, "Email/Pass incorrectos", Snackbar.LENGTH_SHORT).show()
                }
            } else {
                Snackbar.make(layout, "Email/Pass no pueden estar vacíos", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun buscarUsuario(users: MutableList<User>, userEmail: String, userPass: String) : User {
        var match = User("", "", "");
        var filter = users.filter { u -> u.email == userEmail && u.pass == userPass }
        if(filter.size > 0){
            match = filter[0]
        }
        return match
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentLoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

}