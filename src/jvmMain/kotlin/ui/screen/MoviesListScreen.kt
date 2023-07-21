package ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import extensions.loadImageBitmap
import webclient.model.Movie

@Composable
fun moviesListScreen(movies: List<Movie>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),

        // content padding
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        content = {
            items(movies) { movie ->
                movieItem(movie)
            }
        })
}

@Composable
private fun movieItem(movie: Movie) {
    Column(verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(5.dp).width(200.dp).padding(5.dp)) {
        movie.image.loadImageBitmap()?.let {
            Image(
                alignment = Alignment.Center,
                bitmap = it,
                contentDescription = "Banner ${movie.title}",
                modifier = Modifier.clip(RoundedCornerShape (10.dp)))
        }
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Row {
                Icon(Icons.Sharp.Star, contentDescription = "Star", tint = Color.Yellow, modifier = Modifier.height(17.dp))
                Text("${movie.rating}", fontWeight = FontWeight.Bold, fontSize = 15.sp)
            }
            Text("${movie.year}", fontSize = 15.sp, fontWeight = FontWeight.Medium)
        }
        Text(movie.title, fontSize = 12.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Medium)
    }
}
