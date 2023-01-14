// Code from the Github repo
// https://github.com/kaakaww/kotlin-extension-functions-for-protos

package extension_function

import com.google.gson.Gson

fun AlertKt.Dsl.url(host: String, path: String): AlertKt.Dsl {
    url = "https://$host:$path"
    return this
}

// useful when the proto definition can't change, define your own custom builder dsl
// function receiver
fun url(block: UrlBuilder.() -> Unit): String = UrlBuilder().apply { block() }.build()

// associated builder class
data class UrlBuilder(var host: String = "", var path: String = "") {
    fun build(): String = "$host:$path"
}

fun Scan.toJson(): String = Gson().toJson(this)

fun fromJson(json: String): Scan = Gson().fromJson(json, Scan::class.java)

fun Scan.alertsToJson(): String = Gson().toJson(AlertsWrapper(alertsList))

data class AlertsWrapper(val alerts: List<Alert>)

fun ScanKt.Dsl.alerts(json: String): ScanKt.Dsl {
    alerts += Gson().fromJson(json, AlertsWrapper::class.java).alerts
    return this
}

