package minicla03.jsonclock.DOMAIN.UseCase

class DefaultCountryCodeProvider : ICountryCodeProvider {
    override suspend fun getCountryCode(): String {
        return "IT"
    }
}
