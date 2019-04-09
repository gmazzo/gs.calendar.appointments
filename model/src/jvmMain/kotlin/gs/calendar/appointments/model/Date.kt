package gs.calendar.appointments.model

import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.Serializer
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

actual typealias Date = java.util.Date

@Serializer(forClass = Date::class)
actual object DateSerializer {

    private val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }

    override fun serialize(encoder: Encoder, obj: Date) =
        encoder.encodeString(format.format(obj))

    override fun deserialize(decoder: Decoder): Date =
        format.parse(decoder.decodeString())

}
