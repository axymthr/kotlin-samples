package tutorial

import kotlin.io.path.Path
import kotlin.io.path.inputStream
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Usage: list_person ADDRESS_BOOK_FILE")
        exitProcess(-1)
    }
    Path(args.single()).inputStream().use {
        print(AddressBook.newBuilder().mergeFrom(it).build())
    }
}

// Iterates though all people in the AddressBook and prints info about them.
fun print(addressBook: AddressBook) {
    for (person: Person in addressBook.peopleList) {
        println("Person ID: ${person.id}")
        println("  Name: ${person.name}")
//            if (person.hasEmail()) {
        println("  Email address: ${person.email}")
//            }
        for (phoneNumber in person.phonesList) {
            val modifier = when (phoneNumber.type) {
                Person.PhoneType.MOBILE -> "Mobile"
                Person.PhoneType.HOME -> "Home"
                Person.PhoneType.WORK -> "Work"
                else -> "Unknown"
            }
            println("  $modifier phone #: ${phoneNumber.number}")
        }
    }
}
