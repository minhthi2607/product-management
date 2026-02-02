package utils;

import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    private static final String FILE_PATH = "products.dat";
    private static final String FILE_CSV = "products.csv";

    public static void writeToFile(List<Product> products) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {

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

    public static void writeToCSV(List<Product> products) {
        if (products == null || products.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống, không có gì để xuất!");
            return;
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_CSV))) {
            writer.println("ID,Name,Price,Quantity");

            for (Product p : products) {
                writer.printf(
                        "%d,%s,%.2f,%d%n",
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        p.getQuantity()
                );
            }

            System.out.println("Xuất file CSV thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file CSV");
        }
    }
}
