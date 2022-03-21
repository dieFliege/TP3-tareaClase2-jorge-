package com.ort.tareaclase2.navegacion.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ort.tareaclase2.R
import com.ort.tareaclase2.navegacion.viewmodels.FragmentBienvenidaViewModel

class FragmentBienvenida : Fragment() {

    companion object {
        fun newInstance() = FragmentBienvenida()
    }

    private lateinit var viewModel: FragmentBienvenidaViewModel
    private lateinit var v : View
    private lateinit var txtUser : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_bienvenida_fragment, container, false)
        txtUser = v.findViewById(R.id.txtUser)
        return v
    }

    override fun onStart() {
        super.onStart()
        var userNameRecibido = FragmentBienvenidaArgs.fromBundle(requireArguments()).valorUserName
        txtUser.text = userNameRecibido
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentBienvenidaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}