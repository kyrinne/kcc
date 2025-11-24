package org.example.dogs

import com.squareup.moshi.JsonClass
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import java.util.concurrent.Executors


interface DogService  {
    @GET("{count}")
    suspend fun getPictures(
        @Path("count") count: Int,
    ): DogPictureInfo

    @GET
    fun downloadImage(@Url url: String): Call<ResponseBody>
}

@JsonClass(generateAdapter = true)
data class DogPictureInfo(
    val message: List<String>, // urls
    val status: String,
)

private val executor = Executors.newCachedThreadPool { task ->
    Thread(task).apply { isDaemon = true }
}

private fun createHttpClient(): OkHttpClient.Builder =
    OkHttpClient.Builder()
        .protocols(listOf(Protocol.HTTP_1_1))
        .dispatcher(Dispatcher(executor))

val dogService = Retrofit.Builder()
    .client(createHttpClient().build())
    .baseUrl("https://dog.ceo/api/breeds/image/random/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()
    .create<DogService>()
