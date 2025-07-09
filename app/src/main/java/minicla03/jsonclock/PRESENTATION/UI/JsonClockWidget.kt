package minicla03.jsonclock.PRESENTATION.UI

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import kotlinx.coroutines.*
import minicla03.jsonclock.DATA.RepositoryImp.CalendarRepository
import minicla03.jsonclock.DOMAIN.UseCase.CurrentTimeUseCase
import minicla03.jsonclock.R
import minicla03.jsonclock.DOMAIN.UseCase.DefaultCountryCodeProvider

class JsonClockWidget : AppWidgetProvider()
{
    class JsonClockWidget : AppWidgetProvider() {

        override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
            for (widgetId in appWidgetIds) {
                CoroutineScope(Dispatchers.IO).launch {
                    val repository = CalendarRepository()
                    val countryCodeProvider = DefaultCountryCodeProvider()
                    val useCase = CurrentTimeUseCase(repository, countryCodeProvider)

                    val info = useCase.getFestivita()

                    val jsonClockString = """
                    {
                      "data": "${info.data}",
                      "ore": "${info.ora}",
                      "festivita": "${info.festivita}"
                    }
                """.trimIndent()

                    val views = RemoteViews(context.packageName, R.layout.widget_json_clock)
                    views.setTextViewText(R.id.json_text, jsonClockString)

                    withContext(Dispatchers.Main) {
                        appWidgetManager.updateAppWidget(widgetId, views)
                    }
                }
            }
        }
    }
}
