package com.pankaj.gojek.model

import com.google.gson.annotations.SerializedName

class GithubRepoResponse {

    @SerializedName("forks")
    var forks: String? = null

    @SerializedName("builtBy")
    var developer: List<Developer>? = null

    @SerializedName("author")
    var author: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("avatar")
    var avatar: String? = null

    @SerializedName("stars")
    var stars: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("currentPeriodStars")
    var currentPeriodStars: String? = null
}