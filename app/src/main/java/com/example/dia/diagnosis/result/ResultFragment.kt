package com.example.dia.diagnosis.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dia.diagnosis.result.ResultFragmentArgs
import com.example.dia.R
import kotlinx.android.synthetic.main.fragment_result.*
import kotlinx.android.synthetic.main.fragment_result.view.*

class ResultFragment : Fragment() {
    private val resultArgs by navArgs<ResultFragmentArgs>()
    private val array:Array<String> = arrayOf(
            "검사 결과 현재 갱년기 증상이 없는 상태입니다.",
            "검사 결과 현재 경미한 갱년기 상태로 식사, 운동 등의 생활습관 관리가 필요합니다.",
            "검사 결과 현재 중증도 갱년기 상태로 진찰을 통한 상담과 치료가 필요합니다.",
            "검사 결과 현재 심한 갱년기 상태로 전문의를 통한 장기적인 치료가 필요합니다.",
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        total_point_text_view.text=resultArgs.totalPoint.toString()
        when(resultArgs.totalPoint){
            in 0..5 -> result_text_view.text=array[0]
            in 5..10 -> result_text_view.text=array[1]
            in 10..15 -> result_text_view.text=array[2]
            in 15..51 -> result_text_view.text=array[3]
        }
    }

}