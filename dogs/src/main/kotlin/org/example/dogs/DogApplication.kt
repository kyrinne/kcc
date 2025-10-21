package org.example.dogs

import javax.swing.JFrame

fun loadDogImage(window: JFrame) {
    val infoRequest = dogService.getPictures() // TODO: add param here!
    val infoResponse = infoRequest.execute()
    val imageUrl = infoResponse.body()!!.message.first()
    val imageRequest = dogService.downloadImage(imageUrl)
    val imageResponse = imageRequest.execute()

    window.displayImage(imageResponse.body()!!.bytes())
}