package ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import components.centeredMessage
import ui.screen.moviesListScreen
import webclient.MovieWebClient
import webclient.Status

@Composable
@Preview
fun app(content: @Composable () -> Unit) {

    MaterialTheme(colors = darkColors(primary = Color(121212 ))) {
        Surface {
            Box(modifier = Modifier.fillMaxSize()){
                content()
                }
            }
        }
    }

fun main() = application {
    val client = MovieWebClient()
    val loadingMovieStatus = client
        .findTop250Movies()
        .collectAsState(Status.Loading)
        .value
    val icon = painterResource("icon.png")
    Window(
        onCloseRequest = ::exitApplication,
        title = "IMDB",
        icon = icon
        ) {
        app{
            when(loadingMovieStatus){
                is Status.Success -> {
                    val movies = loadingMovieStatus.data
                    if(movies.isNotEmpty()){
                        moviesListScreen(movies)
                    }else{
                        centeredMessage("No movies")
                    }
                }
                is Status.Error -> {
                    val error = loadingMovieStatus.exception
                    var showSnackBar by remember {
                        mutableStateOf(true)
                    }
                    if (showSnackBar) {
                        Snackbar(
                            modifier = Modifier
                                .padding(8.dp),
                            action = {
                                Button(onClick = {
                                    showSnackBar = false
                                }) {
                                    Text("Close")
                                }
                            },
                        ) {
                            Text("Failed finding movies")
                            error.printStackTrace()
                        }
                    }
                }
                Status.Loading -> {
                    centeredMessage("Loading movies...")
                }
            }
        }
    }
}