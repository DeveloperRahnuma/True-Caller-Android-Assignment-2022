package com.rahnumasharib.true_caller_assignment.data.api

import retrofit2.http.GET

//--------------------------------------------------------------------------------------
// This Interface for call server with back end url and return result
// Back to colling function
//--------------------------------------------------------------------------------------

interface ApiService{
    //Use for get 10th character on recived string from below web url
    //result will contain string which contain HTML and JavaScript Tag
    @GET("2018/01/22/life-as-an-android-engineer/")
    suspend fun getTenthCharacter(): String

    //Use for get each 10th character ( 10, 20,30,40 ..... ) on recived string from below web url
    //result will contain string which contain HTML and JavaScript Tag
    @GET("2018/01/22/life-as-an-android-engineer/")
    suspend fun getEachTenthCharacter(): String

    //use, to get whole contain with html and java script on recived string from below web url
    //result will contain string which contain HTML and JavaScript Tag
    @GET("2018/01/22/life-as-an-android-engineer/")
    suspend fun wholeWordCounter(): String
}