package com.robin729.todonotes.onboarding

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.robin729.todonotes.R
import kotlinx.android.synthetic.main.fragment_on_boarding_one.*
import kotlinx.android.synthetic.main.fragment_on_boarding_two.*

/**
 * A simple [Fragment] subclass.
 */
class OnBoardingTwoFragment : Fragment() {

    lateinit var onOptionClick: OnOptionClick

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onOptionClick = context as OnOptionClick
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_on_boarding_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        done2Txt.setOnClickListener {
            onOptionClick.onNextClick()
        }

        back2Txt.setOnClickListener {
            onOptionClick.onBackClick()
        }
    }

    interface OnOptionClick{
        fun onNextClick()
        fun onBackClick()
    }

}
