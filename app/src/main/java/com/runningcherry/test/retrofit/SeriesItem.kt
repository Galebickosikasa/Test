package com.runningcherry.test.retrofit

import Image
import Rating
import _links
import android.util.Log
import java.io.Serializable

data class SeriesItem (val id : Int,
                       val url : String,
                       val name : String,
                       val season : Int,
                       val number : Int,
                       val type : String,
                       val airdate : String,
                       val airtime : String,
                       val airstamp : String,
                       val runtime : Int,
                       val rating : Rating,
                       val image : Image,
                       val summary : String,
                       val _links : _links) : Serializable {

    override fun toString(): String {
        return "SeriesItem(id=$id, url='$url', name='$name', season=$season, number=$number, type='$type', airdate='$airdate', airtime='$airtime', airstamp='$airstamp', runtime=$runtime, rating=$rating, image=$image, summary='$summary', _links=$_links)"
    }

    @JvmName("getSummary1")
    fun getSummary () : String {
        var tmp = summary
        if (tmp[0] == '<') {
            tmp = tmp.drop (3)
            tmp = tmp.dropLast(4)
        }
        return tmp
    }
}