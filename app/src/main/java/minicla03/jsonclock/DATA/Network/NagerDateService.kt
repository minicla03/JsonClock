package minicla03.jsonclock.DATA.Network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import minicla03.jsonclock.DATA.Entity.Holiday
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

class NagerDateService {

    private val client = OkHttpClient()

    suspend fun getFestivita(year: Int, countryCode: String = "IT"): List<Holiday> = withContext(
        Dispatchers.IO)
    {
        val url = "https://date.nager.at/api/v3/PublicHolidays/$year/$countryCode"
        val request = Request.Builder().url(url).build()
        Log.d("NagerDateService", "url: $url")
        val response = client.newCall(request).execute()
        if (!response.isSuccessful) return@withContext emptyList()

        val jsonString = response.body?.string() ?: return@withContext emptyList()
        val jsonArray = JSONArray(jsonString)

        val holidays = mutableListOf<Holiday>()

        for (i in 0 until jsonArray.length()) {
            val jsonObj = jsonArray.getJSONObject(i)
            holidays.add(Holiday(jsonObj.getString("date"), jsonObj.getString("localName")))
        }
        holidays
    }
}
