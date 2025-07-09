package minicla03.jsonclock.DOMAIN.UseCase

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.Locale
import kotlin.coroutines.resume

class GPSCountryCodeProvider(private val context: Context) : ICountryCodeProvider {

    override suspend fun getCountryCode(): String = suspendCancellableCoroutine { cont ->
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        try
        {
            val fineLocationGranted = androidx.core.content.ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED

            val coarseLocationGranted = androidx.core.content.ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED

            if (fineLocationGranted || coarseLocationGranted)
            {
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location ->
                        if (location != null)
                        {
                            val geocoder = Geocoder(context, Locale.getDefault())
                            val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                            val countryCode = addresses?.firstOrNull()?.countryCode ?: "IT"
                            cont.resume(countryCode)
                        }
                        else
                        {
                            cont.resume("IT")
                        }
                    }
                    .addOnFailureListener {
                        cont.resume("IT")
                    }
            }
            else
            {
                cont.resume("IT")
            }
        }
        catch (e: Exception)
        {
            cont.resume("IT")
        }
    }
}
