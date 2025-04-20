package com.example.walmartassessment.ui.fragments

import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartassessment.R
import com.example.walmartassessment.controller.CountriesListAdapter
import com.example.walmartassessment.controller.CountriesViewModel
import com.example.walmartassessment.datasource.net.Country
import com.example.walmartassessment.utils.NetworkUtils
import kotlinx.coroutines.launch

/**
 * Fragment that displays a list of countries, handling loading, error, and success UI states.
 */
class CountriesListFragment : CountriesFragment<Country>() {

    override val adapter by lazy { CountriesListAdapter() }

    override val layoutManager: RecyclerView.LayoutManager
        get() = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

    override fun onStart() {
        super.onStart()
        setupRetryButton()
        observeUiState()
    }

    private fun setupRetryButton() {
        binding.retryButton.setOnClickListener {
            if (NetworkUtils.isNetworkAvailable(requireContext())) {
                viewModel.fetchCountries()
            } else {
                showToast(R.string.no_network_error)
            }
        }
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is CountriesViewModel.UiState.Success -> handleSuccess(state)
                    is CountriesViewModel.UiState.Error -> handleError(state)
                    CountriesViewModel.UiState.Loading -> Unit // Loading handled by base class
                }
            }
        }
    }



    private fun handleSuccess(state: CountriesViewModel.UiState.Success) {
        uiLoaded()
        adapter.submitList(state.countries)
    }

    private fun handleError(state: CountriesViewModel.UiState.Error) {
        if (!NetworkUtils.isNetworkAvailable(requireContext())) {
            showRetryButton()
            showToast(R.string.no_network_error)
        } else {
            showToast(state.message)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showToast(resId: Int) {
        Toast.makeText(requireContext(), getString(resId), Toast.LENGTH_SHORT).show()
    }
}
