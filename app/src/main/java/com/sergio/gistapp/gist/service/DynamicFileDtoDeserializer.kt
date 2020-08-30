package com.sergio.gistapp.gist.service

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class DynamicFileDtoDeserializer : JsonDeserializer<DynamicFileDto> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): DynamicFileDto {
        var list = mutableListOf<FileDto>()

        json?.let {
            for (item in it.asJsonObject.entrySet()) {

                list.add(
                    FileDto(
                        item.value.asJsonObject["filename"].asString,
                        item.value.asJsonObject["type"].asString
                    )
                )
            }
        }

        return DynamicFileDto(list)
    }
}