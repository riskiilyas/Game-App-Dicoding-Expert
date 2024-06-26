package com.riskee.gameappbyriski.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.riskee.gameappbyriski.ImageSliderFragment
import com.riskee.gameappbyriski.R
import com.riskee.gameappbyriski.core.data.Resource
import com.riskee.gameappbyriski.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModels()

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val gameId = intent.getIntExtra(ID_GAME, 0)

        viewModel.detailGameState.observe(this) {
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
                    val fragment = ImageSliderFragment(it.result.screenshots)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, fragment, "image_slider_fragment")
                        .commit()

                    binding.description.text = it.result.description
                    binding.genre.text = it.result.genre
                    binding.platform.text = it.result.platform
                    binding.shortDescription.text = it.result.shortDescription
                    binding.status.text = it.result.status
                    binding.thumbnail.load(Uri.parse(it.result.thumbnail))
                    binding.title.text = it.result.title

                    binding.playButton.setOnClickListener { _ ->
                        val intent =
                            Intent(Intent.ACTION_VIEW, Uri.parse(it.result.gameUrl))
                        startActivity(intent)
                    }

                    binding.fabFav.setOnClickListener { _ ->
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

        viewModel.insertState.observe(this) {
            if (it) {
                binding.fabFav.visibility = View.GONE
            }
        }


        viewModel.getDetailGame(gameId)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val ID_GAME = "ID"
    }
}
