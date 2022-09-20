package bikeRentalApp.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import bikeRentalApp.core.Bike;
import bikeRentalApp.core.Place;
import bikeRentalApp.core.User;


@SuppressWarnings("serial")
public class BikeRentalModule extends SimpleModule {
    
    private static final String NAME = "BikeRentalModule";
  
  public BikeRentalModule() {

    super(NAME, Version.unknownVersion());
    addSerializer(User.class, new UserSerializer());
    //addDeserializer(User.class, new UserDerializer());
    
    addSerializer(Bike.class, new BikeSerializer());
    addDeserializer(Bike.class, new BikeDeserializer());

    addSerializer(Place.class, new PlaceSerializer());
    addDeserializer(Place.class, new PlaceDeserializer());
    
  }

  public static void main(String[] args) {
    User user = new User("name", "pass12");
    Bike bike = new Bike("BIKEIDN1", "Landeveissykkel", "Blå");
    Place place = new Place("Munkholmen", 10);

    user.setBike(bike);
    System.out.println(user);

    Bike bike2 = new Bike("BIKEIDN2", "Tandemsykkel", "Rød");
    Bike bike3 = new Bike("BIKEIDN3", "Fatbike", "Rosa");

    place.addBike(bike2);
    place.addBike(bike3);

    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new BikeRentalModule());

    try {
        System.out.println("JSON for user: ");
        System.out.println(mapper.writeValueAsString(user));
        System.out.println("JSON for place: ");
        System.out.println(mapper.writeValueAsString(place));
    } catch (Exception e) {
        System.err.println("Error");
        e.printStackTrace();
    }
}

}
