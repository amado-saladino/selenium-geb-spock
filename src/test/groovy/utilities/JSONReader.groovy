package utilities

import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.JsonSyntaxException
import org.json.JSONArray
import org.json.JSONObject

import java.lang.reflect.Type
import java.nio.file.Files
import java.nio.file.Paths

class JSONReader {
    Gson gson
    TextFileReader textFileReader

    public JSONReader(){
        gson = new Gson()
        textFileReader = new TextFileReader()
    }

    public String readFileAsString(String fileName)throws Exception
    {
        new String(Files.readAllBytes(Paths.get(fileName)))
    }

    public JSONArray getJsonArrayFromString(String jsonContent,String key) {
        new JSONObject(jsonContent).getJSONArray(key)
    }

    public Object[] getJsonArrayFromFile(String fileName,String key) {
        String contents=""
        Object[] options
        try {
            contents = textFileReader.readResourceFileAsString(fileName)
        } catch (Exception e) {
            e.printStackTrace()
        }
        JSONArray jsonArray = getJsonArrayFromString(contents,key)
        options = new Object[jsonArray.length()]

        for (int option =0; option < options.length; option++){
            options[option] = jsonArray.get(option)
        }
        options
    }
}
