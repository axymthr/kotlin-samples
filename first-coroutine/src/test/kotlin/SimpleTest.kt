import builders.doWork
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SimpleTest {
    @Test
    fun firstTest() {
//        doWork()
        assertEquals(2, 1 + 1)
    }

    @Test
    fun secondTest() = runBlocking{ 
        doWork()
        assertEquals(2, 1 + 1)
    }
}