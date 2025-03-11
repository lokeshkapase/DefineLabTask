package com.lokesh.definelabtask

data class ApiResponse(
    val response: Response
)

data class Response(
    val venues: List<Venue>
)

data class Venue(
    val id: String,
    val name: String
)
