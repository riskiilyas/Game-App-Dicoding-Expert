package com.riskee.gameappbyriski.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.riskee.gameappbyriski.core.data.Resource
import com.riskee.gameappbyriski.core.ui.ScreenshotAdapter
import com.riskee.gameappbyriski.databinding.ActivityDetailBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private val viewModel: DetailViewModel by viewModel()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val gameId = intent.getIntExtra(ID_GAME, 0)

        lifecycleScope.launch {
            viewModel.detailGameState.collect {
                when (it) {
                    is Resource.LOADING -> {
                        binding.fabFav.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                        binding.playButton.visibility = View.GONE
                    }

                    is Resource.SUCCESS -> {
                        binding.fabFav.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        binding.playButton.visibility = View.VISIBLE
                        val screenshotAdapter = ScreenshotAdapter(it.result.screenshots)
                        binding.screenshotViewPager.adapter = screenshotAdapter
                        TabLayoutMediator(binding.screenshotTabLayout, binding.screenshotViewPager) { tab, position ->
                            tab.text = "Screenshot ${position + 1}"
                        }.attach()

                        binding.description.text = it.result.description
                        binding.genre.text = it.result.genre
                        binding.platform.text = it.result.platform
                        binding.shortDescription.text = it.result.shortDescription
                        binding.status.text = it.result.status
                        Glide.with(this@DetailActivity).load(it.result.thumbnail).into(binding.thumbnail)
                        binding.title.text = it.result.title

                        binding.playButton.setOnClickListener {v ->
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.result.gameUrl))
                            startActivity(intent)
                        }

                        binding.fabFav.setOnClickListener {v->
                            viewModel.addFavoriteGame(it.result)
                        }
                    }

                    is Resource.ERROR -> {
                        binding.fabFav.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        binding.playButton.visibility = View.GONE
                    }

                    else -> {}
                }
            }
        }

        lifecycleScope.launch {
            viewModel.insertState.collect {
                if (it) {
                    binding.fabFav.visibility = View.GONE
                }
            }
        }

        viewModel.getDetailGame(gameId)
    }

    companion object {
        val ID_GAME = "ID"
    }
}
