package fluency.notfluent

import fluency.notfluent.Send.Message.StatementReady

enum class Message {
    StatementReady,
    LowBalanceAlert
}

object send {
    fun message(messageId: Message, number: Int) =
        println("sending message $messageId to $number")
}

send.message(StatementReady, 12345678)
