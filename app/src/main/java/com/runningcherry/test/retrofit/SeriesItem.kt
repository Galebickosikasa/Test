package com.runningcherry.test.retrofit

import Image
import Rating
import _links

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
                       val _links : _links) {
    override fun toString(): String {
        return "SeriesItem(id=$id, url='$url', name='$name', season=$season, number=$number, type='$type', airdate='$airdate', airtime='$airtime', airstamp='$airstamp', runtime=$runtime, rating=$rating, image=$image, summary='$summary', _links=$_links)"
    }
}