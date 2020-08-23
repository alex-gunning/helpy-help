package kotlin.org.helpy.help

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.lang.reflect.Parameter

sealed class Bleh {
    data class orange(val a: String): Bleh()
    data class apple(val a: Int): Bleh()
//    companion object {
//        @JsonCreator
//        @JvmStatic
//        private fun creator(name: String): Parameter? {
//            return Parameter::class.sealedSubclasses.firstOrNull {
//                it.simpleName == name
//            }?.objectInstance
//        }
//    }
}
class DeserializationTest {
    val objectMapper  = jacksonObjectMapper()

    @Test
    @DisplayName("Jackson Serialisation")
    fun testSerialisation() {
        val json = """{"a":"Myname"}"""
        val bleh: Bleh = objectMapper.readValue<Bleh>(json)
        val returned = when(bleh) {
            is Bleh.orange -> "is an orange"
            is Bleh.apple -> "is an apple"
        }
        assert(returned.equals("is an orange"))
    }
}