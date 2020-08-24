package org.helpy.help

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.node.JsonNodeType
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.lang.reflect.Parameter

sealed class Bleh {
    class orange(@JsonProperty("a") val a: String): Bleh()
    class apple(@JsonProperty("b") val b: Int): Bleh()
}
class CustomerDeserializer: StdDeserializer<Bleh>(Bleh::class.java) {
    override fun deserialize(parser: JsonParser, ctx: DeserializationContext): Bleh {
        val mapper = parser.codec as ObjectMapper
        val node: JsonNode = mapper.readTree(parser)
        val msgNode = node.get("a")
        if(msgNode.nodeType == JsonNodeType.STRING) {
            return Bleh.orange(msgNode.textValue())
        } else {
            return Bleh.apple(node.get("b").intValue())
        }
    }
}
class DeserializationTest {
    val objectMapper  = jacksonObjectMapper()
    val module: SimpleModule = SimpleModule().addDeserializer(Bleh::class.java, CustomerDeserializer())

    @Test
    @DisplayName("Jackson Serialisation")
    fun testSerialisation() {
        objectMapper.registerModule(module)
        val json = """{"a":"Myname"}"""
        val bleh: Bleh = objectMapper.readValue(json)
        val returned = when(bleh) {
            is Bleh.orange -> "is an orange"
            is Bleh.apple -> "is an apple"
        }
        assert(returned.equals("is an orange"))
    }
}