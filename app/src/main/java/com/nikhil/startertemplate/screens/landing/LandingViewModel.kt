package com.nikhil.startertemplate.screens.landing

import androidx.appcompat.app.WindowDecorActionBar
import androidx.lifecycle.viewModelScope
import com.nikhil.startertemplate.base.BaseViewModel
import com.nikhil.startertemplate.common.database.MyDatabase
import com.nikhil.startertemplate.common.database.ProjectDAO
import com.nikhil.startertemplate.common.models.ModalData
import com.nikhil.startertemplate.data.DataRepositoryContract
import timber.log.Timber
import java.lang.Exception

class LandingViewModel(
    private val dataRepository: DataRepositoryContract,
    private val localDBRepository: ProjectDAO
) : BaseViewModel<LandingEvent>()
{
    fun fetchAllLocalData(){
        viewModelScope.launchIdling {
            var data = localDBRepository.getAllData()
        }
    }

    fun getSkins(){
        viewModelScope.launchIdling {
            try {
                with (dataRepository) {
                    val token = "ACCESS TOKEN"
                    val skins = getSkins(token)
                }

            } catch (e: Throwable) {
                postApiError(e)
            } finally {
                hideLoader()
            }
        }
    }
}