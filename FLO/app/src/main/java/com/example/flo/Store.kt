package com.example.flo

import androidx.room.Entity

@Entity
data class Store(
    var title: String? = "",
    var singer: String? = "",
    var coverImg: Int? = null,
    var songs: ArrayList<Song>? = null
)
