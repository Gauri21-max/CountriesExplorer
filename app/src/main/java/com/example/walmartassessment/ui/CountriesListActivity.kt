package com.example.walmartassessment.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.walmartassessment.R
import com.example.walmartassessment.databinding.ActivityCountriesBinding
import com.example.walmartassessment.ui.fragments.CountriesListFragment

/**
 * This Activity hosts the CountriesListFragment.
 * It is responsible for managing the lifecycle of the fragment
 * and handling fragment transactions when the activity is created.
 */
class CountriesListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountriesBinding

    private val countriesListFragment: Fragment by lazy {
        supportFragmentManager.findFragmentByTag(LIST_FRAG_TAG) as? CountriesListFragment
            ?: CountriesListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content, countriesListFragment, LIST_FRAG_TAG)
                .commit()
        }
    }

    companion object {
        private const val LIST_FRAG_TAG = "CountriesListFragment"
    }
}
