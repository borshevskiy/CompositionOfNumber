package com.borshevskiy.cleanarchitecturesingleactivitycompositionofnumber.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.borshevskiy.cleanarchitecturesingleactivitycompositionofnumber.databinding.FragmentChooseLevelBinding
import com.borshevskiy.cleanarchitecturesingleactivitycompositionofnumber.domain.entity.Level

class ChooseLevelFragment : Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLevelTest.setOnClickListener {
            findNavController().navigate(ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameFragment(Level.TEST))
        }
        binding.buttonLevelEasy.setOnClickListener {
            findNavController().navigate(ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameFragment(Level.EASY))
        }
        binding.buttonLevelNormal.setOnClickListener {
            findNavController().navigate(ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameFragment(Level.NORMAL))
        }
        binding.buttonLevelHard.setOnClickListener {
            findNavController().navigate(ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameFragment(Level.HARD))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}