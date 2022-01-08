package fluency

class Account(val accountNumber: Int) {
    companion object {
        infix fun number(accountNumber: Int) = Account(accountNumber)
    }

    infix fun deposit(amount: Int) =
        println("depositing $${amount/100.0} into account $accountNumber")

    infix fun withdraw(amount: Int) =
        println("withdrawing $${amount/100.0} from account $accountNumber")
}

typealias account = Account