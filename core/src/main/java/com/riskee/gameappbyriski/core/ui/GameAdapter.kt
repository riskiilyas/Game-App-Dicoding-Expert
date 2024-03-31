package com.riskee.gameappbyriski.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.riskee.gameappbyriski.core.R
import com.riskee.gameappbyriski.core.domain.model.Game


class GameAdapter(private val games: List<Game>, private val onItemClicked: (Game) -> Unit) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val thumbnailImageView: ImageView = itemView.findViewById(R.id.thumbnailImageView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val genreTextView: TextView = itemView.findViewById(R.id.genreTextView)
        val platformTextView: TextView = itemView.findViewById(R.id.platformTextView)
        val shortDescriptionTextView: TextView = itemView.findViewById(R.id.shortDescriptionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]

        holder.titleTextView.text = game.title
        holder.genreTextView.text = game.genre
        holder.platformTextView.text = game.platform
        holder.shortDescriptionTextView.text = game.shortDescription

        Glide.with(holder.itemView).load(game.thumbnail).into(holder.thumbnailImageView)

        holder.itemView.setOnClickListener { onItemClicked(game) }
    }

    override fun getItemCount(): Int {
        return games.size
    }
}
