package com.example.flo

interface LookView {
    fun onGetSongsLoading()
    fun onGetSongsSuccess(songs: ArrayList<Song>)
    fun onGetSongsFailure(code: Int, message: String)
}