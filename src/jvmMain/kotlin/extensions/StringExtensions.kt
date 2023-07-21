package extensions

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.net.URL
import javax.imageio.ImageIO

fun String.loadImageBitmap(): ImageBitmap? {
    return try {
        val inputStream = BufferedInputStream(
            URL(this).openStream()
        )
        val bufferedImage = ImageIO.read(inputStream)

        val stream = ByteArrayOutputStream()
        ImageIO.write(bufferedImage, "png", stream)
        val byteArray = stream.toByteArray()

        Image.makeFromEncoded(byteArray).toComposeImageBitmap()
    }  catch (e: java.io.FileNotFoundException){
        val errorImage = "https://developers.google.com/static/maps/documentation/maps-static/images/error-image-generic.png"
        errorImage.loadImageBitmap()
    }
}