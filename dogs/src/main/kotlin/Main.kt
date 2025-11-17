import org.example.dogs.createImageViewerWindow
import org.example.dogs.loadDogImage
import javax.swing.JLabel
import javax.swing.SwingUtilities

fun main() {
    SwingUtilities.invokeLater {
        val window = createImageViewerWindow()
        try {
            loadDogImage(window)
        } catch (e: Exception) {
            window.contentPane.removeAll()
            window.add(JLabel("Error: ${e.message}", JLabel.CENTER))
            window.revalidate()
        }
    }
}
