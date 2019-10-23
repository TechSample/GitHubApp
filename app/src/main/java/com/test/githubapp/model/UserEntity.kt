package com.test.githubapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class UserEntity(

    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id : Int ?= null,
    @SerializedName("login")
    @Expose
    var login : String ?= null,
    @SerializedName("type")
    @Expose
    var type : String ?= null,
    @SerializedName("avatar_url")
    @Expose
    var avatar_url : String?= null,
    @SerializedName("node_id")
    @Expose
    var nodeId : String ?= null,

    @SerializedName("gravatar_id")
    @Expose
    var gravatar_id : String ?= null,
    @SerializedName("url")
    @Expose
    var url : String ?= null,
    @SerializedName("html_url")
    @Expose
    var htmlUrl : String ?= null,
    @SerializedName("followers_url")
    @Expose
    var followersUrl : String ?= null,
    @SerializedName("following_url")
    @Expose
    var followingUrl : String ?= null,
    @SerializedName("gists_url")
    @Expose
    var gistsUrl : String ?= null,
    @SerializedName("starred_url")
    @Expose
    var starredUrl : String ?= null,
    @SerializedName("subscriptions_url")
    @Expose
    var subscriptionsUrl : String ?= null,
    @SerializedName("organizations_url")
    @Expose
    var orgUrl : String ?= null,
    @SerializedName("repos_url")
    @Expose
    var reposUrl : String ?= null,
    @SerializedName("events_url")
    @Expose
    var eventsUrl : String ?= null,
    @SerializedName("received_events_url")
    @Expose
    var receivedEventUrl : String ?= null,

    @SerializedName("site_admin")
    @Expose
    var siteAdmin : String

)