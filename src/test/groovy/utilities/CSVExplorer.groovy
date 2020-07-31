package utilities

import com.opencsv.CSVReader

class CSVExplorer implements Iterable<String[]> {
    String filename

    CSVExplorer(String filename) {
        this.filename = filename
    }

    @Override
    Iterator<String[]> iterator() {
        new CSVIterator<String[]>(filename)
    }

    class CSVIterator<T> implements Iterator<T> {
        TextFileReader textFileReader
        CSVReader csvReader
        File file

        CSVIterator(String filename) {
            textFileReader = new TextFileReader()
            file = textFileReader.readResourceFile(filename)
            csvReader = new CSVReader(new FileReader(file))
            csvReader.skip(1)
        }

        @Override
        boolean hasNext() {
            csvReader.peek() !=null
        }

        @Override
        T next() {
            csvReader.readNext() as T
        }
    }
}
