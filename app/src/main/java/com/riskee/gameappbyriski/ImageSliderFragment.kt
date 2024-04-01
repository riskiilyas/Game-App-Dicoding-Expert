package com.riskee.gameappbyriski

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.riskee.gameappbyriski.core.domain.model.Screenshot
import com.riskee.gameappbyriski.core.ui.ImageSlideAdapter
import com.riskee.gameappbyriski.databinding.FragmentImageSliderBinding
import me.relex.circleindicator.CircleIndicator

class ImageSliderFragment(private val imagesModel: List<Screenshot>) : Fragment() {

    private var _binding: FragmentImageSliderBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPagerAdapter: ImageSlideAdapter
    private lateinit var indicator: CircleIndicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageSliderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imagesModel.let {
            viewPagerAdapter = ImageSlideAdapter(requireContext(), it.map { ss -> ss.image })
            binding.viewpager.adapter = viewPagerAdapter
            indicator = binding.indicator
            indicator.setViewPager(binding.viewpager)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
