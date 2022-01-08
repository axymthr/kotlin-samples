package fluency

object fetch {
    infix fun balance(number: Int) = println("Fetch the balance of $number")
}

fetch balance 123456