package org.example.dogs

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame

suspend fun loadDogImage(window: JFrame) {
    println ("loading dog image")
    val infoRequest = dogService.getPictures(1)
    val infoResponse = infoRequest.awaitResponse()
    val imageUrl = infoResponse.body()!!.message.first() // TODO: handle number
    val imageRequest = dogService.downloadImage(imageUrl)
    val imageResponse = imageRequest.awaitResponse()
    window.displayImage(imageResponse.body()!!.bytes())
}

class ImageViewer : WindowAdapter() {
    private val coroutineScope = MainScope()

    override fun windowOpened(e: WindowEvent) {
        val window = e.window as JFrame
        coroutineScope.launch {
            loadDogImage(window)
        }
    }
}