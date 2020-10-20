package com.app.movie.datasource.network

object ApiClient {

   /* val retrofit: Retrofit by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        //val cacheSize = (5 * 1024 * 1024).toLong()
       // val myCache = Cache(context!!.cacheDir, cacheSize)

        val okHttpClient:OkHttpClient = OkHttpClient.Builder()
            //.cache(myCache)
//                .addInterceptor(offlineInterceptor)
//                .addNetworkInterceptor(onlineInterceptor)
            .addInterceptor(interceptor)
//            .addInterceptor { chain ->
//                var request = chain.request()
//                request = if (hasNetwork()!!)
//                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
//                else
//                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
//                chain.proceed(request)
//            }
            .build()


        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            //.addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }*/


}