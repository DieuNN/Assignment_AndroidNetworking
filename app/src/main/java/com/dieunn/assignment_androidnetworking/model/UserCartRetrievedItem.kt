package com.dieunn.assignment_androidnetworking.model

data class UserCartRetrievedItem(
    val __v: Int,
    val _id: String,
    val password: String,
    val products: List<Product>,
    val username: String
)