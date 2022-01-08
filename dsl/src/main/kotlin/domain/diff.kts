import domain.begin
import domain.end
import java.time.LocalDate
import java.time.Period

operator fun LocalDate.minus(other: LocalDate) {
    Period.between(this, other).apply {
        println("$years years $months months $days days")
    }
}

begin - end