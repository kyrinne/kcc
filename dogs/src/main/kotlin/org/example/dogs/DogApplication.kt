package org.example.dogs

import retrofit2.awaitResponse
import javax.swing.JFrame

suspend fun loadDogImage(window: JFrame) {
    val infoRequest = dogService.getPictures(1)
    val infoResponse = infoRequest.awaitResponse()
    val imageUrl = infoResponse.body()!!.message.first() // TODO: handle number
    val imageRequest = dogService.downloadImage(imageUrl)
    val imageResponse = imageRequest.awaitResponse()
    window.displayImage(imageResponse.body()!!.bytes())
}
