package com.riskee.gameappbyriski.favorite.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.riskee.gameappbyriski.core.data.Resource
import com.riskee.gameappbyriski.core.ui.FavoriteAdapter
import com.riskee.gameappbyriski.databinding.ActivityFavoriteBinding
import com.riskee.gameappbyriski.di.DaggerModuleFavorite
import com.riskee.gameappbyriski.di.FavoriteModuleInterface
import com.riskee.gameappbyriski.favorite.ViewModelFactory
import com.riskee.gameappbyriski.favorite.detail_favorite.DetailFavoriteActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class FavoriteActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: FavoriteViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerModuleFavorite.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleInterface::class.java
                )
            )
            .build()
            .inject(this)


        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.favoriteGamesFlow.observe(this) { it ->
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

        viewModel.getAllFavoriteGames()
    }
}
