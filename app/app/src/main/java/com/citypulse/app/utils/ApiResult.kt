package com.citypulse.app.utils

import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import java.io.IOException

// classe qui représente les 3 états possibles d'un appel réseau : succès, erreur ou chargement
sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val exception: ApiException) : ApiResult<Nothing>()
    data object Loading : ApiResult<Nothing>()

    val isSuccess: Boolean get() = this is Success
    val isError: Boolean get() = this is Error
    val isLoading: Boolean get() = this is Loading

    // retourne la donnée si c'est un succès, sinon null
    fun getOrNull(): T? = if (this is Success) data else null
}

// on distingue le type d'erreur pour pouvoir afficher le bon message à l'utilisateur
sealed class ApiException(message: String, cause: Throwable? = null) : Exception(message, cause) {
    class NetworkException(cause: Throwable) : ApiException("Pas de connexion réseau.", cause)
    class HttpException(val code: Int, message: String) : ApiException("Erreur $code : $message")
    class ParseException(cause: Throwable) : ApiException("Erreur de lecture JSON.", cause)
    class UnknownException(cause: Throwable) : ApiException("Erreur inattendue.", cause)
}

// fonction utilitaire pour éviter les try/catch partout dans le code
// on l'appelle autour de chaque appel API et elle gère les erreurs automatiquement
suspend fun <T> safeApiCall(block: suspend () -> T): ApiResult<T> {
    return try {
        ApiResult.Success(block())
    } catch (e: IOException) {
        // pas de connexion internet
        ApiResult.Error(ApiException.NetworkException(e))
    } catch (e: HttpException) {
        // le serveur a répondu avec une erreur (ex: 404, 500)
        ApiResult.Error(ApiException.HttpException(e.code(), e.message()))
    } catch (e: JsonSyntaxException) {
        // la réponse JSON n'a pas pu être parsée
        ApiResult.Error(ApiException.ParseException(e))
    } catch (e: Exception) {
        ApiResult.Error(ApiException.UnknownException(e))
    }
}
