package utils;

import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    private static final String FILE_PATH = "products.dat";

    public static void writeToFile(List<Product> products) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream("products.dat"))) {

            oos.writeObject(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<Product> readFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
            return (List<Product>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Lỗi đọc file!");
            return new ArrayList<>();

        }
    }
}
