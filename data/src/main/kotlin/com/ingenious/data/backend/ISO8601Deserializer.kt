package com.ingenious.data.backend

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.lang.reflect.Type
import java.text.ParseException

class ISO8601Deserializer : JsonDeserializer<LocalDateTime> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalDateTime {

        val dateString = json?.asString

        return try {
            Instant.parse(dateString!!).toLocalDateTime(TimeZone.UTC)
        } catch (e: ParseException) {
            throw JsonParseException("Error parsing date: $dateString", e)
        }
    }
}

