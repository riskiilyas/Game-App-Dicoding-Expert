package com.riskee.gameappbyriski.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.riskee.gameappbyriski.core.R
import com.riskee.gameappbyriski.core.domain.model.Screenshot

class ScreenshotAdapter(private val screenshots: List<Screenshot>) : RecyclerView.Adapter<ScreenshotAdapter.ScreenshotViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenshotViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_screenshot, parent, false)
        return ScreenshotViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScreenshotViewHolder, position: Int) {
        val screenshot = screenshots[position]
        Glide.with(holder.itemView.context)
            .load(screenshot.image)
            .into(holder.screenshotImageView)
    }

    override fun getItemCount() = screenshots.size

    class ScreenshotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val screenshotImageView: ImageView = itemView.findViewById(R.id.screenshotImageView)
    }
}