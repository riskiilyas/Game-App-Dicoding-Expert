package com.riskee.gameappbyriski.core.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import coil.load
import com.riskee.gameappbyriski.core.databinding.ItemScreenshotBinding

class ImageSlideAdapter(private val context: Context, private var imageList: List<String>) : PagerAdapter() {
    private lateinit var binding: ItemScreenshotBinding

    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        binding = ItemScreenshotBinding.inflate(LayoutInflater.from(context), container, false)
        imageList[position].let { binding.screenshotImageView.load(it) }
        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}