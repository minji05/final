package com.example.dia

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 */
class tab1Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_tab1, container, false)
    }

    fun newInstant() : tab1Fragment
    {
        val args = Bundle()
        val frag = tab1Fragment()
        frag.arguments = args
        return frag
    }

}