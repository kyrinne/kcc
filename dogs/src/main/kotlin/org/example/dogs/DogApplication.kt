package org.example.dogs

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame
import kotlin.time.Duration.Companion.seconds

suspend fun loadDogImage(window: JFrame) {
    val pictureInfo = dogService.getPictures(1)
    val imageUrl = pictureInfo.message.first() // TODO: handle number
    val imageRequest = dogService.downloadImage(imageUrl)
    val imageResponse = imageRequest.awaitResponse()
    window.displayImage(imageResponse.body()!!.bytes())
}

class ImageViewer : WindowAdapter() {
    private val coroutineScope = MainScope()

    override fun windowOpened(e: WindowEvent) {
        val window = e.window as JFrame
        coroutineScope.launch {
            while (true) {
                loadDogImage(window)
                delay(3.seconds)
            }
        }
    }

    override fun windowClosed(e: WindowEvent) {
        coroutineScope.cancel()
    }
}
