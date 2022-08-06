package com.dieunn.assignment_androidnetworking

import com.dieunn.assignment_androidnetworking.model.ProductsRetrieved
import com.dieunn.assignment_androidnetworking.model.ProductsRetrievedItem
import com.dieunn.assignment_androidnetworking.model.UserCartRetrieved
import retrofit2.Call
import retrofit2.http.*

interface AppAPI {
    @POST("user/new")
    @FormUrlEncoded
    suspend fun registerUser(
        @Field("username")
        username : String,
        @Field("password")
        password : String
    ) : String

    @GET("user/get")
    suspend fun getNumberOfRecord(
        @Query("username")
        username: String,
        @Query("password")
        password: String
    ) : String

    @GET("user/cart/{username}")
    fun getUserCart(
        @Path("username")
        username: String
    ) : Call<UserCartRetrieved>

    @GET("products")
    suspend fun getAllProducts() : ProductsRetrieved

    @GET("products/get/{id}")
    suspend fun getProductById(
        @Path("id", encoded = true) id :String
    ) : ProductsRetrieved

    @FormUrlEncoded
    @POST("user/put")
    suspend fun addToCart(
        @Field("username", encoded = true)
        username: String,
        @Field("productId", encoded = true)
        productId : String,
    )


}