package unittests;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import utilities.Helper;
import utilities.PropertyReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HelperTest {
    Helper helper;
    PropertyReader propertyReader;
    String productName;
    Random random;

    @BeforeAll
    void setup() {
        helper = new Helper();
        propertyReader = new PropertyReader("env.properties");
        productName = propertyReader.getProperty("productName");
        random = new Random();
    }

    @Test
    void testHelperInitiated() {
        assertNotNull(helper);
    }

    @ParameterizedTest
    @MethodSource("randomKeywords")
    void generateKeywordFromText(String keyword) {
        assertTrue(productName.contains(keyword));
    }

    Stream<Arguments> randomKeywords() {
        List<Arguments> args = new ArrayList<>();

        for (int i=0; i<=4;i++)
            args.add(Arguments.of(helper.generateKeywordFromText(productName)));

        return args.stream();
    }

    @ParameterizedTest
    @MethodSource("getDataTable")
    void testStreamArguments(String id, String name, String age) {
        System.out.println("Id: " + id + " Name: " + name + " Age: " + age);
    }

    Stream<Arguments> getDataTable() {
        List<Arguments> args = new ArrayList<>();
        String[][] data = new String[][] {
                {"1", "Ahmed", "35"},
                {"2", "Asmaa", "25"},
                {"3", "Mai", "24"},
                {"4", "Ezzo", "30"}
        };

        for (int i = 0; i < data.length; i++) {
            args.add(Arguments.of(data[i]));
        }
        return args.stream();
    }
}