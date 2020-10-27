package com.app.movie.utils

object Constants {
    //data base
    const val DATABASE_NAME: String = "blog_db"

    // api
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val LANGUAGE = "en-US"
    const val API_KEY = "0b05f42616ee89883ecf6adb6af55ae0"
    const val NOW_PLAYING = BASE_URL + "movie/now_playing"
    const val VIDEO = BASE_URL + "movie/{movie_id}/videos"

    const val YOUTUBE_WEB_LINK = "http://www.youtube.com/watch?v="
    const val YOUTUBE_APP_LINK = "vnd.youtube:"

}
