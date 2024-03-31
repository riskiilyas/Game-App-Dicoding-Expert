package com.riskee.gameappbyriski.detail_favorite

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.riskee.gameappbyriski.core.data.Resource
import com.riskee.gameappbyriski.core.ui.ScreenshotAdapter
import com.riskee.gameappbyriski.databinding.ActivityDetailFavoriteBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFavoriteActivity : AppCompatActivity() {
    private val viewModel: DetailFavoriteViewModel by viewModel()
    private lateinit var binding: ActivityDetailFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val id = intent.getIntExtra(ID_GAME, 0)

        lifecycleScope.launch {
            viewModel.detailGameFlow.collect {
                when(it) {
                    is Resource.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.playButton.visibility = View.GONE
                    }

                    is Resource.SUCCESS -> {
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
                        Glide.with(this@DetailFavoriteActivity).load(it.result.thumbnail).into(binding.thumbnail)
                        binding.title.text = it.result.title

                        binding.playButton.setOnClickListener {v ->
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.result.gameUrl))
                            startActivity(intent)
                        }

                    }

                    is Resource.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        binding.playButton.visibility = View.GONE
                    }

                    else -> {}
                }
            }
        }

        viewModel.getDetailFavoriteGame(id)
    }

    companion object {
        val ID_GAME: String = "ID"
    }
}
