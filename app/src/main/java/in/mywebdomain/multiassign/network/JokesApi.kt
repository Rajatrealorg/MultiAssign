package `in`.mywebdomain.multiassign.network

import `in`.mywebdomain.multiassign.model.JCategoryAuto
import `in`.mywebdomain.multiassign.model.JokesAuto
import `in`.mywebdomain.multiassign.utils.CATEGORIES_API
import `in`.mywebdomain.multiassign.utils.JOKES_API

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface JokesApi {
    @GET(JOKES_API)
    fun getJokes(@Query("limitTo") catcat : String): Observable<JokesAuto>

    @GET(CATEGORIES_API)
    fun getCategories() : Observable<JCategoryAuto>
}