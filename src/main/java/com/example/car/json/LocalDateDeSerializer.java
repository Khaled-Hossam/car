package com.example.car.json;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// import com.example.car.exception.WrongDateException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalDateDeSerializer extends StdDeserializer<LocalDate> {

    public LocalDateDeSerializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {
        String value = p.readValueAs(String.class);
        try {
//    		return new SimpleDateFormat("MM/dd/yyyy").parse(value);
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    		LocalDate localDate = LocalDate.parse(value, formatter);
    		return localDate;
        } catch (DateTimeParseException e) {
//        	throw new Exception
        	// throw new WrongDateException("date format not valid");
            return null;
        }
    }

}