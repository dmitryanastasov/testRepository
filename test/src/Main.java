public class Main {
    public static void main(String[] args) {
        String inputFile = "input.txt"; // Имя входного файла
        String outputFile = "output.txt"; // Имя файла для сохранения отсортированных данных
        String separator = ":";  // Разделитель
        int batchSize = 100;  // Размер порции данных для сортировки

        Sorter.sortDictionary(inputFile, outputFile, separator, batchSize);
    }
}