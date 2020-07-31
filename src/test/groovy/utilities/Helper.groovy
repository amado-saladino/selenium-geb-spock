package utilities

class Helper {
    private Random random = new Random()

    def generateKeywordFromText(text) {
        def length = text.length()
        def startIndex, endIndex
        def keyword

        while (1) {
            startIndex = random.nextInt(length -1) + 0
            endIndex = random.nextInt(length -1) + 0

            if (endIndex - startIndex >= 5) {
                keyword = text.substring(startIndex, endIndex)
                break
            }
        }
        keyword
    }
}
