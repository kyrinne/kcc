package org.example.dogs

import java.awt.Dimension
import java.awt.event.WindowListener
import javax.swing.*

fun JFrame.displayImage(data: ByteArray) {
    contentPane.removeAll()
    add(JLabel(ImageIcon(data)))
    pack()
}

fun createWindow(title: String, listener: WindowListener) {
    SwingUtilities.invokeLater {
        val window = JFrame(title)
        window.defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        window.size = Dimension(400, 300)
        window.add(JLabel("Loading your image ...", JLabel.CENTER))
        window.addWindowListener(listener)
        window.isVisible = true
    }
}
