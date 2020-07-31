package utilities

import java.nio.file.Files
import java.nio.file.Paths

class TextFileReader {

    String readFileAsString(String fileName) {
        new String(Files.readAllBytes(Paths.get(fileName)))
    }

    File readResourceFile(String resource) {
        ClassLoader classLoader = ClassLoader.systemClassLoader
        new File(classLoader.getResource(resource).file)
    }

    String readResourceFileAsString(String resource) {
        File file = readResourceFile(resource)
        new String(Files.readAllBytes(file.toPath()))
    }
}
