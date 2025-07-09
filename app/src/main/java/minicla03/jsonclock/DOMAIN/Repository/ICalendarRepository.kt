package minicla03.jsonclock.DOMAIN.Repository

interface ICalendarRepository
{
    suspend fun getFestivita(data: String, countryCode: String = "IT"): String?
}