package com.riskee.gameappbyriski.home

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.riskee.gameappbyriski.R
import com.riskee.gameappbyriski.core.data.Resource
import com.riskee.gameappbyriski.core.ui.GameAdapter
import com.riskee.gameappbyriski.databinding.ActivityMainBinding
import com.riskee.gameappbyriski.detail.DetailActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            viewModel.gamesState.collect {
                when(it) {
                    is Resource.LOADING -> {
                        binding.rvGame.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.SUCCESS -> {
                        binding.rvGame.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        binding.rvGame.adapter = GameAdapter(it.result) { game ->
                            val detailIntent = Intent(this@MainActivity, DetailActivity::class.java)
                            detailIntent.putExtra(DetailActivity.ID_GAME, game.id)
                            startActivity(detailIntent)
                        }
                    }
                    is Resource.ERROR -> {
                        binding.rvGame.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                    }

                    else -> {}
                }
            }
        }

        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.action_add_favorite -> {
                    val uri = Uri.parse("gameappbyriski://favorite")
                    startActivity(Intent(Intent.ACTION_VIEW, uri))
                    true
                }
                else -> {
                    false
                }
            }
        }

        viewModel.getAllGames()
    }
}