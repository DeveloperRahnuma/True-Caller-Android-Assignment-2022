package com.rahnumasharib.true_caller_assignment.data.repository

import com.rahnumasharib.true_caller_assignment.data.api.ApiService
import javax.inject.Inject

//--------------------------------------------------------------------------------------
// This class in act like repository here which help view model to get data from weburl
// view model call any of its function to get the data and
// repository call api to fetch those data from server
//--------------------------------------------------------------------------------------

class MainRepository @Inject constructor(private val apiService: ApiService) {

    // function getTenthCharacter() is used to find out the 10th character
    // and give result back to view model
    suspend fun getTenthCharacter(): String {
        return apiService.getTenthCharacter()
    }

    // function getEachTenthCharacter() is used to find out the each 10th character
    // means find out the character on 10, 20, 30, 40 ...... number
    // and give result back to view model
    suspend fun getEachTenthCharacter(): String {
        return apiService.getEachTenthCharacter()
    }

    // function wholeWordCounder() is used to get whole content from url
    // and give result back to view model
    suspend fun wholeWordCounder(): String {
        return apiService.wholeWordCounter()
    }

}