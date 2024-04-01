package com.riskee.gameappbyriski.core.ui

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.riskee.gameappbyriski.core.databinding.ItemFavoriteBinding
import com.riskee.gameappbyriski.core.domain.model.Game


class FavoriteAdapter(
    private val games: List<Game>,
    private val onItemClicked: (Game) -> Unit,
    private val onDeleteClicked: (Game) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding =
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]

        holder.binding.apply {
            titleTextView.text = game.title
            genreTextView.text = game.genre
            platformTextView.text = game.platform
            shortDescriptionTextView.text = game.shortDescription

            thumbnailImageView.load(Uri.parse(game.thumbnail))

            root.setOnClickListener { onItemClicked(game) }
            btnDeleteItem.setOnClickListener { onDeleteClicked(game) }
        }
    }

    override fun getItemCount(): Int {
        return games.size
    }

    class GameViewHolder(val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root)
}