package minicla03.jsonclock.DOMAIN.UseCase

interface ICountryCodeProvider {
    suspend fun getCountryCode(): String
}

