# 📱 JsonClock Widget for Android

Un elegante widget orologio per Android che mostra tempo, data e festività via JSON. Semplice, personalizzabile e utile.

---

## 🚀 Funzionalità principali

- **Widget Home Screen** in versione orologio analogico o digitale  
- Sincronizzazione dati da **servizio REST** (es. Nager.Date) per festività  
- Configurazioni via JSON: formato ora/data, stile widget, aggiornamento  
- Completamente in **Kotlin + Jetpack**, compatibile con Android 7.0+

---

## 🧱 Architettura & Componenti

- `JsonClockWidget.kt` – widget principale  
- `NagerDateService.kt` – servizio per il fetch JSON  
- `CalendarRepository.kt` – logica per il repository calendari  
- `UseCase` e `Domain` – logica pulita e separazione di responsabilità  
- Layout XML & drawables per temi chiaro/scuro

---

## ⚙️ Installazione e Uso

1. Clona il repository:  
   ```bash
   git clone https://github.com/minicla03/JsonClock.git
2. Importa in Android Studio

3. Costruisci e installa su dispositivo o emulatore

4. Aggiungi il widget: premi a lungo sulla Home → Widget → JsonClock

---

##🛠️ Tecnologie usate
Linguaggio: Kotlin
Architettura: Clean Architecture (Domain / UseCase / Repository)
Networking: Retrofit + Coroutines
Widget: AppWidgetProvider + AlarmManager
Build System: Gradle Kotlin DSL

---

## 🧰 Screenshots

