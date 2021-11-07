package com.runningcherry.test

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.method.MovementMethod
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout
import com.runningcherry.test.retrofit.SeriesItem
import com.squareup.picasso.Picasso

class EpisodeActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var season : TextView
    private lateinit var episode : TextView
    private lateinit var description : TextView
    private lateinit var image : ImageView
    private lateinit var link : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode)
        val item = intent.getSerializableExtra("SeriesItem") as SeriesItem?
        Log.e ("kek", "episode = $item")
        season = findViewById(R.id.season)
        episode = findViewById(R.id.episode)
        description = findViewById(R.id.description)
        image = findViewById(R.id.episodeImage)
        link = findViewById(R.id.link)

        Picasso.with(this).load(item?.image?.original).into(image)
        toolbar = findViewById (R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = item?.name

        season.text = "${item?.season} season"
        episode.text = "${item?.number} episode"
        description.text = item?.getSummary()
        link.setOnClickListener {
            val uri = Uri.parse(item?.url)
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }
}