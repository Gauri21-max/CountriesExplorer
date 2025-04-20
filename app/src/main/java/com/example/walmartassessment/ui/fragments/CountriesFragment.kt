package com.example.walmartassessment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartassessment.controller.CountriesListAdapter
import com.example.walmartassessment.controller.CountriesViewModel
import com.example.walmartassessment.databinding.FragmentRecyclerViewBinding
import com.example.walmartassessment.di

/**
 * A generic fragment responsible for displaying a list of countries.
 * This fragment can be extended to scale use cases in the future,
 * for example, by adding pagination or a country detail view.
 *
 * @param T The type of data to be displayed in the list.
 */
abstract class CountriesFragment<T> : Fragment() {

    // Abstract properties that must be implemented by subclasses

    // Adapter for displaying the country data
    protected abstract val adapter: CountriesListAdapter

    // LayoutManager for the RecyclerView
    protected abstract val layoutManager: RecyclerView.LayoutManager

    // Binding for the fragment's layout
    private var _binding: FragmentRecyclerViewBinding? = null
    protected val binding: FragmentRecyclerViewBinding
        get() = _binding
            ?: throw IllegalStateException("Binding accessed before onCreateView or after onDestroyView")

    // ViewModel for managing the countries data
    protected lateinit var viewModel: CountriesViewModel

    /**
     * Inflates the fragment's layout and binds the RecyclerView to the adapter and layout manager.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentRecyclerViewBinding.inflate(inflater, container, false).apply {
        // Setting up the binding
        _binding = this

        // RecyclerView setup with the given layoutManager and adapter
        recyclerView.apply {
            layoutManager = this@CountriesFragment.layoutManager
            adapter = this@CountriesFragment.adapter
            setHasFixedSize(true)
        }
    }.root

    /**
     * Initializes the ViewModel and binds it to the activity's lifecycle.
     * This method is called when the fragment is started.
     */
    @CallSuper
    override fun onStart() {
        super.onStart()

        // Initialize the ViewModel with the factory that provides the repository
        viewModel = ViewModelProvider(
            requireActivity() as AppCompatActivity,
            CountriesViewModel.Factory(requireContext().di().countriesRepo)
        ).get(CountriesViewModel::class.java)
    }

    /**
     * Clears the binding when the fragment's view is destroyed to avoid memory leaks.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * UI method to handle the loaded state.
     * Makes the RecyclerView visible and hides the empty state and retry button.
     */
    protected fun uiLoaded() {
        binding.apply {
            recyclerView.visibility = View.VISIBLE
            emptyState.visibility = View.GONE
            retryButton.visibility = View.GONE
        }
    }

    /**
     * UI method to show an error message by displaying the retry button.
     * Hides the RecyclerView and shows the empty state.
     */
    protected fun showRetryButton() {
        binding.apply {
            emptyState.visibility = View.GONE
            retryButton.visibility = View.VISIBLE
        }
    }
}
