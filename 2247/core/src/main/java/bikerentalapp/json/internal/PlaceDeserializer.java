package bikerentalapp.json.internal;

import bikerentalapp.core.Bike;
import bikerentalapp.core.Place;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlaceDeserializer extends JsonDeserializer<Place> {

    private BikeDeserializer bikeDeserializer = new BikeDeserializer();

    @Override
    public Place deserialize(JsonParser parser,
            DeserializationContext context) throws IOException, JacksonException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return this.deserialize((JsonNode) treeNode);
    }

    public Place deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            JsonNode nameNode = objectNode.get("name");
            JsonNode maximumNumberOfBikesNode = objectNode.get("maximumNumberOfBikes");
            List<Bike> bikes = new ArrayList<>();
            JsonNode bikesNode = objectNode.get("bikes");
            if (bikesNode instanceof ArrayNode) {
                for (JsonNode bikeNode : ((ArrayNode) bikesNode)) {
                    Bike bike = bikeDeserializer.deserialize(bikeNode);
                    if (bike != null) {
                        bikes.add(bike);
                    }
                }
            }
            if (nameNode instanceof TextNode && maximumNumberOfBikesNode instanceof TextNode) {
                return new Place(((TextNode) nameNode)
                        .asText(), ((TextNode) maximumNumberOfBikesNode).asInt(), bikes);
            }
        }
        return null;
    }

}
