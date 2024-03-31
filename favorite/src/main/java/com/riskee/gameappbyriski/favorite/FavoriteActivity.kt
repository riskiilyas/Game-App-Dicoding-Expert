package com.riskee.gameappbyriski.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.riskee.gameappbyriski.core.data.Resource
import com.riskee.gameappbyriski.core.ui.FavoriteAdapter
import com.riskee.gameappbyriski.databinding.ActivityFavoriteBinding
import com.riskee.gameappbyriski.detail_favorite.DetailFavoriteActivity
import com.riskee.gameappbyriski.di.KoinModuleFavorite
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private val viewModel: FavoriteViewModel by viewModel()

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(KoinModuleFavorite.getInjectionModule())

        lifecycleScope.launch {
            viewModel.favoriteGamesFlow.collect { it ->
                when (it) {
                    is Resource.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is Resource.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rvGame.adapter = FavoriteAdapter(it.result, onDeleteClicked = {
                            viewModel.deleteFavoriteGame(it)
                        }, onItemClicked = {
                            val detailIntent =
                                Intent(this@FavoriteActivity, DetailFavoriteActivity::class.java)
                            detailIntent.putExtra(DetailFavoriteActivity.ID_GAME, it.id)
                            startActivity(detailIntent)
                        })
                    }

                    is Resource.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                    }

                    else -> {}
                }
            }
        }

        viewModel.getAllFavoriteGames()
    }
}
