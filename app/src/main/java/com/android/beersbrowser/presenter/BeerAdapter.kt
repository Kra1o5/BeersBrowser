package com.android.beersbrowser.presenter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.android.beersbrowser.R
import com.android.beersbrowser.model.BeerInfo
import com.android.beersbrowser.view.DetailActivity
import com.android.beersbrowser.view.MainActivity
import java.util.*
import kotlin.collections.ArrayList


class BeerAdapter(beers: ArrayList<BeerInfo>) :
    RecyclerView.Adapter<BeerAdapter.ViewHolder?>(), Filterable {
    private var beers: ArrayList<BeerInfo> = ArrayList()
    private var mArrayList: ArrayList<BeerInfo> = ArrayList()
    private var beerProduct: BeerInfo? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.list_product, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                beers = if (charString.isEmpty()) {
                    mArrayList
                } else {
                    val filteredList: ArrayList<BeerInfo> = ArrayList()
                    for (beersName in mArrayList) {
                        if (beersName.name?.toLowerCase(Locale.getDefault())!!
                                .contains(charString.toLowerCase(Locale.getDefault()))
                        ) {
                            filteredList.add(beersName)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = beers
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                beers = filterResults.values as ArrayList<BeerInfo>
                notifyDataSetChanged()
            }
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.beerName.text = beers[position].name
        viewHolder.cardView.setOnClickListener { view ->
            beerProduct = beers[position]
            val myIntent = Intent(view.context, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("beer", beerProduct)
            myIntent.putExtras(bundle)
            view.context.startActivity(myIntent)
        }
    }

    override fun getItemCount(): Int {
        return beers.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val beerName: TextView = view.findViewById(R.id.title)
        val cardView: CardView = view.findViewById(R.id.card_products_list)
    }

    init {
        this.beers = beers
        mArrayList = beers
    }
}