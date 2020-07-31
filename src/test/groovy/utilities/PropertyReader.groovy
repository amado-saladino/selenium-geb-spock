package utilities

class PropertyReader {
    private String resourceFile

    PropertyReader(String resourceName)  {
        this.resourceFile = resourceName
    }

    private Properties getAllProperties()  {
        Properties properties = new Properties()

        try {
            File resourceFile = readResourceFile(this.resourceFile)
            properties.load(resourceFile.newDataInputStream())

        } catch (IOException e) {
            e.printStackTrace()
        }
        properties
    }

    String getProperty(String propertyName){
        getAllProperties().getProperty propertyName
    }

    private File readResourceFile(String resource) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader()
        new File(classLoader.getResource(resource).getFile())
    }
}
