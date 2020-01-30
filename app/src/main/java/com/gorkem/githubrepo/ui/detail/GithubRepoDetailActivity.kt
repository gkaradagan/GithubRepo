package com.gorkem.githubrepo.ui.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.gorkem.githubrepo.R
import com.gorkem.githubrepo.base.BaseActivity
import com.gorkem.githubrepo.base.whenNonNull
import com.gorkem.githubrepo.data.local.favourite.Favourite
import com.gorkem.githubrepo.data.model.GithubRepoResponse
import com.gorkem.githubrepo.databinding.ActivityGithubRepoDetailBinding
import javax.inject.Inject


class GithubRepoDetailActivity :
    BaseActivity<ActivityGithubRepoDetailBinding, GithubRepoDetailViewModel>(GithubRepoDetailViewModel::class.java) {

    //Handle LandScape/Potrait
    private var saveRepo: GithubRepoResponse? by instanceState()
    private var saveDefaultFavourite: Boolean? by instanceState()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null || saveRepo == null) {
            saveRepo = intent.extras?.getParcelable(REPO)
        }

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        saveRepo.whenNonNull {
            supportActionBar!!.title = saveRepo!!.name
            if (savedInstanceState == null || saveRepo == null) {
                saveDefaultFavourite = saveRepo!!.favourite
            }
            binding.item = saveRepo!!

            binding.ivStar.setOnClickListener {
                if (saveRepo!!.favourite) {
                    viewModel.deleteFavourite(Favourite(saveRepo!!.id))
                } else {
                    viewModel.insertFavourite(Favourite(saveRepo!!.id))
                }
                saveRepo!!.favourite = !(saveRepo!!.favourite)
                binding.item = saveRepo!!
                binding.executePendingBindings()
            }
        }
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

    }

    override fun onBackPressed() {
        if (saveDefaultFavourite != null && saveDefaultFavourite != saveRepo?.favourite) {
            val returnIntent = Intent()
            returnIntent.putExtra(REPO, saveRepo)
            setResult(Activity.RESULT_OK, returnIntent)
        }
        finish()
    }

    override val layoutRes: Int
        get() = R.layout.activity_github_repo_detail

    companion object {
        const val REPO = "REPO"
    }

}