package utils;

public class Utils {
    public static <T> void printArray(T[] t) {
        StringBuilder builder = new StringBuilder();
        for (T item : t) {
            builder.append(item);
            builder.append("\n");
        }
        System.out.println(builder.toString());
    }
}
