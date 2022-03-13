package com.rahnumasharib.true_caller_assignment.di

import com.rahnumasharib.true_caller_assignment.data.api.ApiService
import com.rahnumasharib.true_caller_assignment.data.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

//---------------------------------------------------------------
// Using HILT For Dependency Injection
//----------------------------------------------------------------


@Module
@InstallIn(ActivityRetainedComponent::class)
object DataRepositoryModule {

    //provideDataRepository take ApiService ( interface where we called backend server )
    //and return MainRepository instance
    @Provides
    fun provideDataRepository(apiService: ApiService): MainRepository {
        return MainRepository(apiService)
    }
}