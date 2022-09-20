package bikeRentalApp.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import bikeRentalApp.core.User;
import bikeRentalApp.core.Bike;

public class UserSerializer extends JsonSerializer<User>{

    /*
    JSON format: 
        {
            "username": "...",
            "password": "...",
            "bike": "[...]"
        }

     */

    @Override
    public void serialize(User user, JsonGenerator jGen, SerializerProvider serializerProvider) throws IOException {
        jGen.writeStartObject();
        jGen.writeStringField("username", user.getUsername());
        jGen.writeStringField("password", user.getPassword());
        jGen.writeArrayFieldStart("bike");
        jGen.writeObject(user.getBike());
        jGen.writeEndArray();
        jGen.writeEndObject();
    }
    
}