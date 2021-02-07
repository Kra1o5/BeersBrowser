package com.android.beersbrowser.view

import android.R.attr.data
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.beersbrowser.R
import com.android.beersbrowser.model.*
import com.android.beersbrowser.model.APIClient.Companion.getClient
import com.android.beersbrowser.presenter.BeerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private var progressBar: ProgressBar? = null
    private var recyclerView: RecyclerView? = null
    private var textView: TextView? = null
    private var beersList: ArrayList<BeerInfo> = ArrayList()
    private var beerAdapter: BeerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)
        progressBar = findViewById(R.id.progress_bar)

        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        progressBar!!.isIndeterminate = true
        getBeersInfo()
    }

    private fun getBeersInfo() {
        val service = getClient().create(APIInterface::class.java)
        val call = service.getAllBeers()
        call.enqueue(object : Callback<MutableList<BeerInfo>> {

            override fun onResponse(
                call: Call<MutableList<BeerInfo>>?,
                response: Response<MutableList<BeerInfo>>?
            ) {
                if (response!!.isSuccessful && response.body() != null) {
                    beersList = ArrayList(response.body())
                    beerAdapter = BeerAdapter(beersList)
                    recyclerView!!.adapter = beerAdapter
                    progressBar!!.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<MutableList<BeerInfo>>?, t: Throwable?) {
                textView = findViewById(R.id.check_internet)
                textView!!.visibility = View.VISIBLE
                progressBar!!.visibility = View.GONE
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val menuItem: MenuItem = menu.findItem(R.id.search)
        val searchView = menuItem.actionView
        search(searchView as SearchView)
        return true
    }

    private fun search(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (beerAdapter != null) beerAdapter!!.filter.filter(newText)
                return true
            }
        })
    }
}