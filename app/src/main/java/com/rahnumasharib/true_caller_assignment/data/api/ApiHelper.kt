package com.rahnumasharib.true_caller_assignment.data.api

import javax.inject.Inject

class ApiHelper @Inject constructor(private val apiService: ApiService) {
    suspend fun getUsers() = apiService.getTenthCharacter()
}