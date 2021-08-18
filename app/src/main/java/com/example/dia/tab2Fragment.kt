package com.example.dia

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import kotlinx.android.synthetic.main.fragment_tab2.*
import android.content.Intent as Intent

/**
 * A simple [Fragment] subclass.
 */
class tab2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_tab2, container, false)

    }

    fun newInstant() : tab2Fragment
    {
        val args = Bundle()
        val frag = tab2Fragment()
        frag.arguments = args
        return frag
    }



    }


