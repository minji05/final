package com.example.dia.diagnosis.diagnosis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dia.diagnosis.diagnosis.DiagnosisAdapter
import com.example.dia.DiagnosisModel
import com.example.dia.R
import kotlinx.android.synthetic.main.fragment_diagnosis.*

class DiagnosisFragment : Fragment() {
    private val list = ArrayList<DiagnosisModel>()
    private val adapter: DiagnosisAdapter by lazy{
        DiagnosisAdapter(list, this@DiagnosisFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setDiagnosisPaper(list)
        return inflater.inflate(R.layout.fragment_diagnosis, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter
    }

    private fun setDiagnosisPaper(list: ArrayList<DiagnosisModel>){
        //1~11
        list.add(DiagnosisModel("홍조, 얼굴 화끈거림(1)"))
        list.add(DiagnosisModel("홍조, 얼굴 화끈거림(2)"))
        list.add(DiagnosisModel("홍조, 얼굴 화끈거림(3)"))
        list.add(DiagnosisModel("홍조, 얼굴 화끈거림(4)"))
        list.add(DiagnosisModel("발한(1)"))
        list.add(DiagnosisModel("발한(2)"))
        list.add(DiagnosisModel("불면증(1)"))
        list.add(DiagnosisModel("불면증(2)"))
        list.add(DiagnosisModel("신경질(1)"))
        list.add(DiagnosisModel("신경질(2)"))
        list.add(DiagnosisModel("우울증"))
        list.add(DiagnosisModel("어지럼증"))
        list.add(DiagnosisModel("피로감"))
        list.add(DiagnosisModel("관절통, 근육통"))
        list.add(DiagnosisModel("두통"))
        list.add(DiagnosisModel("가슴 두근거림"))
        list.add(DiagnosisModel("질건조, 분비물 감소"))


    }
}