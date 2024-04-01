package com.riskee.gameappbyriski.favorite.detail_favorite

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
import com.riskee.gameappbyriski.databinding.ActivityDetailFavoriteBinding
import com.riskee.gameappbyriski.di.DaggerModuleFavoriteDetail
import com.riskee.gameappbyriski.di.FavoriteModuleInterface
import com.riskee.gameappbyriski.favorite.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class DetailFavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailFavoriteBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: DetailFavoriteViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerModuleFavoriteDetail.builder()
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
        binding = ActivityDetailFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val id = intent.getIntExtra(ID_GAME, 0)

        viewModel.detailGameFlow.observe(this) {
            when (it) {
                is Resource.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.playButton.visibility = View.GONE
                }

                is Resource.SUCCESS -> {
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

        viewModel.getDetailFavoriteGame(id)
    }

    companion object {
        const val ID_GAME: String = "ID"
    }
}
