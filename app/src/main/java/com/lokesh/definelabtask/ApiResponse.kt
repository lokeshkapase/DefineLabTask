package com.lokesh.definelabtask

data class ApiResponse(
    val response: VenueResponse
)

data class VenueResponse(
    val venues: List<Venue>
)

data class Venue(
    val id: String,
    val name: String,
    val location: Location,
    val categories: List<Category>?
)

data class Location(
    val address: String?,
    val lat: Double,
    val lng: Double,
    val city: String?,
    val state: String?,
    val country: String?
)

data class Category(
    val id: String,
    val name: String,
    val icon: Icon
)

data class Icon(
    val prefix: String,
    val suffix: String
)


