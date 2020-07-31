package unittests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.PropertyReader;

public class PropertyReaderTest {

    PropertyReader propertyReader;

    @BeforeClass
    public void setUp() {
        propertyReader = new PropertyReader("env.properties");
    }

    @Test(priority = 1)
    void testIsNotNull() {
        Assert.assertNotNull(propertyReader);
    }

    @Test(priority = 2)
    public void testGetProperty() {
        Assert.assertEquals(propertyReader.getProperty("productName")
                ,"Apple MacBook Pro 13-inch");
    }
}