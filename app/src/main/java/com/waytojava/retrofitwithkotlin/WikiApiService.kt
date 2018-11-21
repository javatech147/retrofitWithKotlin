package com.waytojava.retrofitwithkotlin

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Manish Kumar on 11/21/2018
 */

interface WikiApiService {
    @GET("api.php")
    fun hitCountCheck(
        @Query("action") action: String,
        @Query("format") format: String,
        @Query("list") list: String,
        @Query("srsearch") searchText: String
    ): Observable<Model.Result>
}