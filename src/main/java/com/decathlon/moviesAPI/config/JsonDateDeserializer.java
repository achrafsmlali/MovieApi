package com.decathlon.moviesAPI.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//this deserializer is created to return null if the json string value
//is something rather than a string date in the format "dd MMM yyyy"
public class JsonDateDeserializer extends JsonDeserializer<LocalDate>
{
    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String date = jsonParser.getText();

        try {
            return LocalDate.parse(date, formatter);
        }catch (DateTimeParseException e){
            return null;
        }

    }

}
