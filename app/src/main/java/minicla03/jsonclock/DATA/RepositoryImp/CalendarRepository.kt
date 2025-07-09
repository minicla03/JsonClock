package minicla03.jsonclock.DATA.RepositoryImp

import android.util.Log
import minicla03.jsonclock.DATA.Entity.Holiday
import minicla03.jsonclock.DATA.Network.NagerDateService
import minicla03.jsonclock.DOMAIN.Repository.ICalendarRepository

class CalendarRepository() : ICalendarRepository
{
    private val cache = mutableMapOf<String, List<Holiday>>()
    val service: NagerDateService= NagerDateService()

    override suspend fun getFestivita(data: String, countryCode: String): String? {
        val year = data.substring(0, 4).toIntOrNull() ?: return null
        val cacheKey = "$year-$countryCode"

        val holidays = cache[cacheKey] ?: service.getFestivita(year, countryCode).also {
            cache[cacheKey] = it
        }

        Log.d("CalendarRepository", "Holidays for $countryCode: $holidays")
        return holidays.find { it.date == data }?.localName
    }
}