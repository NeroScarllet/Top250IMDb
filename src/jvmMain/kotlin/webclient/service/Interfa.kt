package webclient.service

import retrofit2.http.GET
import webclient.IMDB_API_KEY
import webclient.model.Top250Data

interface MovieService {
    @GET(IMDB_API_KEY)
    suspend fun findTop250Movies(): Top250Data
}