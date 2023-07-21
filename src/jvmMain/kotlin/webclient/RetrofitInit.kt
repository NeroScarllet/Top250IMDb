package webclient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import webclient.service.MovieService

class RetrofitInit {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/alura-cursos/mocks-imdb/main/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()


    val movieService: MovieService
        get() = retrofit.create(MovieService::class.java)
}

