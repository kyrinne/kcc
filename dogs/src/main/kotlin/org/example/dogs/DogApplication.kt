package org.example.dogs

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.swing.JFrame
import javax.swing.SwingUtilities

fun interface ImageViewerCallback<T> : Callback<T> {
    override fun onResponse(call: Call<T>, response: Response<T>)
    override fun onFailure(call: Call<T>, t: Throwable) = t.printStackTrace()
}

fun loadDogImage(window: JFrame) {
    val infoRequest = dogService.getPictures(1)
    infoRequest.enqueue(ImageViewerCallback { _, infoResponse ->
        val imageUrl = infoResponse.body()!!.message.first() // TODO: handle number
        val imageRequest = dogService.downloadImage(imageUrl)
        imageRequest.enqueue(ImageViewerCallback { _, imageResponse ->
            SwingUtilities.invokeLater {
                window.displayImage(imageResponse.body()!!.bytes())
            }
        })
    })
}
