package minicla03.jsonclock.DOMAIN.UseCase

import android.util.Log
import minicla03.jsonclock.DOMAIN.Model.DataTimeInfo
import minicla03.jsonclock.DOMAIN.Repository.ICalendarRepository
import java.text.SimpleDateFormat
import java.util.*

class CurrentTimeUseCase(
    private val repository: ICalendarRepository,
    private val countryCodeProvider: ICountryCodeProvider
) : ICurrentTimeUseCase {

    override suspend fun getFestivita(): DataTimeInfo {
        val now = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dateKeyFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val data = dateFormat.format(now.time)
        val ora = SimpleDateFormat("HH:mm", Locale.getDefault()).format(now.time)
        val dateKey = dateKeyFormat.format(now.time)

        val countryCode = Locale.getDefault().country
        val festivita = repository.getFestivita(dateKey, countryCode) ?: "Nessuna"

        return DataTimeInfo(data, ora, festivita)
    }
}
