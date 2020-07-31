package unittests;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utilities.JSONReader;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JSONReaderTest {
    JSONReader jsonReader;

    @BeforeAll
    void setup() {
        jsonReader = new JSONReader();
    }

    @Test
    void getJsonArrayFromFileTest() {
        Object[] users = jsonReader.getJsonArrayFromFile("users.json","users");
        Map user = ((JSONObject) users[0]).toMap();
    }
}