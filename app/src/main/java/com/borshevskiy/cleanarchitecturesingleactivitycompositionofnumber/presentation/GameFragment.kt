package com.borshevskiy.cleanarchitecturesingleactivitycompositionofnumber.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.borshevskiy.cleanarchitecturesingleactivitycompositionofnumber.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<GameFragmentArgs>()

    private val viewModelFactory by lazy {
        GameViewModelFactory(args.level, requireActivity().application)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]
    }

    private val tvOptions by lazy {
        mutableListOf<TextView>().apply {
            with(binding) {
                add(tvOption1)
                add(tvOption2)
                add(tvOption3)
                add(tvOption4)
                add(tvOption5)
                add(tvOption6)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setClickListenersToOptions()
    }

    private fun setClickListenersToOptions() {
        for (tvOption in tvOptions) {
            tvOption.setOnClickListener {
                viewModel.chooseAnswer(tvOption.text.toString().toInt())
            }
        }
    }

    private fun observeViewModel() {
        with(binding) {
            viewModel.formattedTime.observe(viewLifecycleOwner, {
                tvTimer.text = it
            })
            viewModel.question.observe(viewLifecycleOwner, {
                tvSum.text = it.sum.toString()
                tvLeftNumber.text = it.visibleNumber.toString()
                for (i in 0 until tvOptions.size) {
                    tvOptions[i].text = it.options[i].toString()
                }
            })
            viewModel.progressAnswers.observe(viewLifecycleOwner, {
                tvAnswersProgress.text = it
            })
            viewModel.percentOfRightAnswers.observe(viewLifecycleOwner, {
                progressBar.setProgress(it, true)
            })
            viewModel.enoughCountOfRightAnswers.observe(viewLifecycleOwner, {
                val colorResId = getColorByState(it)
                tvAnswersProgress.setTextColor(ContextCompat.getColor(requireContext(), colorResId))
            })
            viewModel.enoughPercentOfRightAnswers.observe(viewLifecycleOwner, {
                val colorResId = getColorByState(it)
                progressBar.progressTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(requireContext(), colorResId))
            })
            viewModel.minPercent.observe(viewLifecycleOwner, {
                progressBar.secondaryProgress = it
            })
            viewModel.gameResult.observe(viewLifecycleOwner, {
                findNavController().navigate(
                    GameFragmentDirections.actionGameFragmentToGameFinishedFragment(
                        it
                    )
                )
            })
        }
    }

    private fun getColorByState(goodState: Boolean): Int {
        val colorResId = if (goodState) {
            android.R.color.holo_green_light
        } else {
            android.R.color.holo_red_light
        }
        return colorResId
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}