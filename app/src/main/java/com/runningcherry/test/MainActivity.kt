package com.runningcherry.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    private var adapter = MyAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById (R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        getSeries()
        val itemDecoration = DividerItemDecoration (this, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(getDrawable(R.drawable.divider)!!)
//        recyclerView.addItemDecoration(itemDecoration)
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

    fun onClickItem (item : SeriesItem) {
//        Log.e ("kek", "from activity ${item.toString()}")
        val intent = Intent (this, EpisodeActivity::class.java).apply { putExtra("SeriesItem", item) }
        startActivity(intent)
    }
}

