package com.example.tmdb_api.data.mappers

import com.example.tmdb_api.domain.models.ResponseLocalUI
import com.example.tmdb_api.domain.models.ResponseRemoteUI
import javax.inject.Inject

class ResponseRemoteUIToLocalUIMapper
    @Inject
    constructor() {
        fun mapShowList(responseRemote: List<ResponseRemoteUI>): List<ResponseLocalUI> =
            responseRemote.map {
                ResponseLocalUI(
                    it.id,
                    it.title,
                    it.imageSet,
                )
            }

        fun mapShow(responseRemote: ResponseRemoteUI): ResponseLocalUI =
            ResponseLocalUI(
                responseRemote.id,
                responseRemote.title,
                responseRemote.imageSet,
            )
    }
