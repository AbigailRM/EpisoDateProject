package com.moviledev.tvseries.models

class ResponsePagination (var page: Int, var total : Int, var pages: Int) {
    val totalPages: Int = pages/20
}