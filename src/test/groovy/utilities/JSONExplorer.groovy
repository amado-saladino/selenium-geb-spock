package utilities

import org.json.JSONObject

class JSONExplorer implements Iterable<String[]> {

    String file, key

    JSONExplorer(file, key) {
        this.file = file
        this.key = key
    }

    @Override
    Iterator<Map> iterator() {
        return new JSONIterator<String[]>(file,key)
    }

    class JSONIterator<T> implements Iterator<T> {

        Object[] users
        JSONReader jsonReader
        int index =0

        JSONIterator(file, key) {
            this.file = file
            this.key = key
            jsonReader = new JSONReader()
            users = jsonReader.getJsonArrayFromFile(file,key);
        }

        @Override
        boolean hasNext() {
            return index < users.length
        }

        @Override
        T next() {
            JSONObject user = users[index++]
            return user.toMap().values().toArray() as T
        }
    }

}
