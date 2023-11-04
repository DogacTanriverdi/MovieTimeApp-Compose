package com.dogactnrvrdi.movietimecompose.presentation.search

sealed class SearchEvent {
    data class Search(val searchQuery: String) : SearchEvent()
}