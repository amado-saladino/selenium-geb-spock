package unittests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utilities.TextFileReader;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TextFileReaderTest {
    TextFileReader textFileReader;

    @BeforeAll
    void setup() {
        textFileReader = new TextFileReader();
    }

    void initClassTest() {
        assertNotNull(textFileReader);
    }

    @Test
    void readResourceFileTest() {
        String contents = textFileReader.readResourceFileAsString("data.csv");
        System.out.println("File contains: \n" + contents);
        assertTrue(contents.contains("Ahmed Saladino,Amado@mail.com"));
    }

    @Test
    void readFileAsStringTest() {
        String contents = textFileReader.readFileAsString("src/test/resources/data.csv");
        assertTrue(contents.contains("Ahmed Saladino,Amado@mail.com"));
    }
}