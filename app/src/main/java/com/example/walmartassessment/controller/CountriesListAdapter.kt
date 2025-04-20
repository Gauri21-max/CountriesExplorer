package com.example.walmartassessment.controller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartassessment.R
import com.example.walmartassessment.databinding.ListItemCountryBinding
import com.example.walmartassessment.datasource.net.Country

/**
 * Adapter to display a list of countries in a RecyclerView.
 * Utilizes ListAdapter to efficiently handle item updates with DiffUtil.
 */
class CountriesListAdapter : ListAdapter<Country, CountriesListAdapter.CountriesViewHolder>(CountryItemCallback()) {

    /**
     * Called when a new view holder is created to represent a country.
     *
     * @param parent the parent view group where the new view will be added.
     * @param viewType the type of view to be created (unused here).
     * @return a new [CountriesViewHolder] instance with the layout inflated.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        // Inflate the layout for a country item and return the view holder
        val binding = ListItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountriesViewHolder(binding)
    }

    /**
     * Binds data from the given position of the list to the ViewHolder.
     *
     * @param holder the view holder where data is bound.
     * @param position the position in the list of the country to bind.
     */
    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        // Retrieve the country at the given position and bind it to the holder
        val country = getItem(position)
        holder.bind(country)
    }

    /**
     * ViewHolder that binds data for each individual country item.
     *
     * @param binding the binding object to set views and data.
     */
    class CountriesViewHolder(private val binding: ListItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the country data to the view holder's views.
         *
         * @param country the country data to display in the item.
         */
        fun bind(country: Country) {
            // Bind country name, region, code, and capital to respective TextViews
            // TODO: Handle null values that might come as null from the server.
            // For simplicity, and after reviewing the JSON, fields are kept as non-null in the data class.
            with(binding) {
                val name = country.name.ifNullOrBlank("NA")
                val region = country.region.ifNullOrBlank("NA")
                val code = country.code.ifNullOrBlank("NA")
                val capital = country.capital.ifNullOrBlank("NA")

                tvCountryNameRegion.text = root.context.getString(R.string.country_name_region_format, name, region)
                tvCountryCode.text = code
                tvCountryCapital.text = capital
            }
        }

        private fun String?.ifNullOrBlank(default: String): String {
            return if (this.isNullOrBlank()) default else this
        }

    }

    /**
     * DiffUtil callback class to optimize RecyclerView updates.
     * Compares the equality of two [Country] items and their contents.
     */
    class CountryItemCallback : DiffUtil.ItemCallback<Country>() {

        /**
         * Checks if two items represent the same country.
         *
         * @param oldItem the previous country item.
         * @param newItem the new country item to compare.
         * @return true if both items represent the same country (based on code).
         */
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean = oldItem.code == newItem.code

        /**
         * Checks if the contents of two country items are the same.
         *
         * @param oldItem the previous country item.
         * @param newItem the new country item to compare.
         * @return true if both country items are identical.
         */
        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean = oldItem == newItem
    }
}
