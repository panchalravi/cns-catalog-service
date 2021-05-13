package com.example.catalogservice.persistence

import java.time.Year
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class YearAttributeConverter : AttributeConverter<Year, Int> {
    override fun convertToDatabaseColumn(attribute: Year?): Int = attribute?.value!!

    override fun convertToEntityAttribute(dbData: Int?): Year = Year.of(dbData!!)
}