package com.example.tmdb_api.data.mappers

import com.example.tmdb_api.domain.models.ResponseLocalUI
import com.example.tmdb_api.domain.models.ResponseRemoteUI
import javax.inject.Inject

class ResponseRemoteUIToLocalUIMapper @Inject constructor() {

    fun mapShowList(responseRemote: List<ResponseRemoteUI>): List<ResponseLocalUI> {
        return responseRemote.map {
            ResponseLocalUI(
                it.id,
                it.title,
            )
        }
    }

    fun mapShow(responseRemote: ResponseRemoteUI): ResponseLocalUI {
        return ResponseLocalUI(
            responseRemote.id,
            responseRemote.title,
        )
    }


}