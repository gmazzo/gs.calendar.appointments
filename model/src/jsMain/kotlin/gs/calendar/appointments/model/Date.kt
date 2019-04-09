package gs.calendar.appointments.model

import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.Serializer

actual typealias Date = kotlin.js.Date

@Serializer(forClass = Date::class)
actual object DateSerializer {

    override fun serialize(encoder: Encoder, obj: Date) =
        encoder.encodeString(obj.toISOString())

    override fun deserialize(decoder: Decoder) = Date(decoder.decodeString())

}
