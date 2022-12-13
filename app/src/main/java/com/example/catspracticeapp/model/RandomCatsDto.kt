package com.example.catspracticeapp.model

data class RandomCatsDto(
    val _id: String,
    val createdAt: String,
    val `file`: String,
    val mimetype: String,
    val owner: String,
    val size: Int,
    val tags: List<Any>,
    val updatedAt: String,
    val url: String,
    val validated: Boolean
)