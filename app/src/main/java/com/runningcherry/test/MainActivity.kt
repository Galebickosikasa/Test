package com.runningcherry.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.runningcherry.test.retrofit.GetAllSeries
import com.runningcherry.test.retrofit.RetrofitClientInstance
import com.runningcherry.test.retrofit.SeriesItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var toolbar : Toolbar
    private var adapter = MyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSeries()
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        recyclerView = findViewById (R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        val itemDecoration = DividerItemDecoration (this, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(getDrawable(R.drawable.divider)!!)
        recyclerView.addItemDecoration(itemDecoration)
        getSeries()
    }

    fun getSeries () {
         val service = RetrofitClientInstance.retrofitInstance?.create(GetAllSeries::class.java)
         val call = service?.getAllSeries()
         call?.enqueue(object : Callback<List<SeriesItem>> {
             override fun onResponse(call: Call<List<SeriesItem>>, response: Response<List<SeriesItem>>) {
                 val list = response.body()
                 runOnUiThread {
                     adapter.setList(list!!)
                 }
             }

             override fun onFailure(call: Call<List<SeriesItem>>, t: Throwable) {
                 Log.e ("kek", "UR MOM'S GAY + ${t.message}")
             }
         })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add) {
//            adapter.addItem()
        } else {
            Log.e ("kek", "gay")
        }
        return super.onOptionsItemSelected(item)
    }
}

