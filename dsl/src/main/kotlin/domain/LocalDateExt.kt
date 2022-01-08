package domain

import java.time.LocalDate
import java.time.Month

val begin = LocalDate.of(2020, 2, 15)
val end = LocalDate.of(2020, 9, 30)

val May = Month.MAY
val June = Month.JUNE

operator fun ClosedRange<LocalDate>.contains(month: Month) =
    month.value >= start.month.value && month.value <= endInclusive.month.value

fun print(result: Any) = println(result)