package com.robin729.todonotes.onboarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.robin729.todonotes.R
import kotlinx.android.synthetic.main.fragment_on_boarding_one.*

/**
 * A simple [Fragment] subclass.
 */
class OnBoardingOneFragment : Fragment() {

    lateinit var onNextClick: OnNextClick

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onNextClick = context as OnNextClick

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_on_boarding_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        next1Txt.setOnClickListener {
            onNextClick.onClick()
        }
    }

    interface OnNextClick {
        fun onClick()
    }

}
