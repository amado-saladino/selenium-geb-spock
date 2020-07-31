package unittests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utilities.Faker;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FakerTest {

    Faker faker;
    private Random random;

    @BeforeAll
    void setup() {
        faker = new Faker();
        faker.generateLocalDate();
        random = new Random();
    }

    @Test
    void itShouldInitClass() {
        assertNotNull(faker);
    }

    @Test
    void itShouldGetFullName() {
        assertTrue(faker.getFullName().length() > 0);
    }

    @Test
    void itShouldGenerateRandomNumber() {
        int min = random.nextInt(5) + 1;
        int max = random.nextInt(20) + 10;
        int randomNumber = faker.generateRandomNumberBetween(min, max);
        assertTrue(randomNumber <= max && randomNumber >= min);
    }

    @Test
    void getMonthTest() {
        String month = faker.getMonth();
        int day = Integer.parseInt(faker.getDay());
        assertNotNull(month);
        assertTrue(month.length() > 0);

        if (month.contains("Feb")) {
            assertTrue(day > 0 && day <= 29);
        }
    }

    @Test
    void getDayOfMonthTest() {
        int day = Integer.parseInt(faker.getDayOfMonth());
        assertTrue(day > 0 && day <= 31);
    }
}