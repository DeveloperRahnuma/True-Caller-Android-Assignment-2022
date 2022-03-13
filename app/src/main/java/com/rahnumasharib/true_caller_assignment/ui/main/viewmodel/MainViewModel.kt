package com.rahnumasharib.true_caller_assignment.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.rahnumasharib.true_caller_assignment.data.repository.MainRepository
import com.rahnumasharib.true_caller_assignment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

//------------------------------------------------------------------------------------
// View Model For Main Activity
//------------------------------------------------------------------------------------

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    // this will call the repository for get character of 10th postion
    //once data fetch by repository it will pass towards activity
    //and activity can see that data through observer
    fun fetchTenthCharacter() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(data = mainRepository.getTenthCharacter()))
        } catch (exception: Exception) {
            emit(Resource.error(exception.message ?: "Error Occurred!", data = null))
        }
    }


    // this will call the repository for get character of each 10th postion
    //once data fetch by repository it will pass towards activity
    //and activity can see that data through observer
    fun fetchEachTenthCharacter() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(data = mainRepository.getEachTenthCharacter()))
        } catch (exception: Exception) {
            emit(Resource.error(exception.message ?: "Error Occurred!", data = null))
        }
    }


    // this will call the repository for get character of whole content of url
    //once data fetch by repository it will pass towards activity
    //and activity can see that data through observer
    fun wholeWordCounter() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(data = mainRepository.wholeWordCounder()))
        } catch (exception: Exception) {
            emit(Resource.error(exception.message ?: "Error Occurred!", data = null))
        }
    }

}