package com.waytojava.retrofitwithkotlin

import com.google.gson.annotations.SerializedName

/**
 * Created by Manish Kumar on 11/21/2018
 */

/*wrap them in Model object,
as that allows me to preserved
the class easily from proguard obfuscate*/

object Model {
    data class Result(
        @SerializedName("query")
        val query: Query
    )

    data class Query(
        @SerializedName("searchinfo")
        val searchInfo: SearchInfo
    )

    data class SearchInfo(
        @SerializedName("totalhits")
        val totalhits: Int
    )
}