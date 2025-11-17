package org.example.dogs

import javax.swing.JFrame

fun loadDogImage(window: JFrame) {
    val infoRequest = dogService.getPictures(2)
    val infoResponse = infoRequest.execute()
    val imageUrl = infoResponse.body()!!.message.first() // TODO: handle number
    val imageRequest = dogService.downloadImage(imageUrl)
    val imageResponse = imageRequest.execute()

    window.displayImage(imageResponse.body()!!.bytes())
}