package org.example.dogs

import java.awt.Dimension
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel

fun createImageViewerWindow() =
    JFrame("Image Viewer").apply {
        defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
        size = Dimension(400, 300)
        add(JLabel("Loading your image ...", JLabel.CENTER))
        isVisible = true
    }

fun JFrame.displayImage(data: ByteArray) {
    contentPane.removeAll()
    add(JLabel(ImageIcon(data)))
    pack()
}