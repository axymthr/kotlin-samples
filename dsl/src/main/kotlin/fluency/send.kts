import Send.Message.StatementReady

enum class Message {
    StatementReady,
    LowBalanceAlert;

    infix fun to(number: Int) = println("sending message $this to $number")
}

object send {
    infix fun message(messageId: Message) = messageId
}

send message StatementReady to 12345678


