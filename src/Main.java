import model.Product;
import service.ProductService;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ProductService productService = new ProductService();

    public static void main(String[] args) {

        while (true) {
            showMenu();
            System.out.print("Chọn chức năng : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    showProducts();
                    break;
                case 5:
                    searchByName();
                    break;
                case 6:
                    sortByPriceAsc();
                    break;
                case 7:
                    sortByPriceDesc();
                    break;
                case 0:
                    System.exit(0);
                    scanner.close();
                default:
                    System.out.println("Lựa chọn của bạn không hợp lệ!");
            }


        }
    }

    private static void showMenu() {
        System.out.println("\n====== QUẢN LÝ SẢN PHẨM ======");
        System.out.println("1. Thêm sản phẩm");
        System.out.println("2. Sửa sản phẩm");
        System.out.println("3. Xoá sản phẩm");
        System.out.println("4. Hiển thị danh sách");
        System.out.println("5. Tìm kiếm theo tên");
        System.out.println("6. Sắp xếp theo giá tăng dần");
        System.out.println("7. Sắp xếp theo giá giảm dần");
        System.out.println("0. Thoát");
    }

    private static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số!");
            }
        }
    }

    private static double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }

    private static void addProduct() {
        System.out.println("== Thêm sản phẩm ==");
        int id = readInt("ID : ");
        System.out.print("Tên : ");
        String name = scanner.nextLine();
        double price = readDouble("Giá : ");
        int quantity = readInt("Số lượng : ");
        System.out.print("Loại : ");
        String category = scanner.nextLine();
        Product product = new Product(id, name, price, quantity, category);

        try {
            productService.addProduct(product);
            System.out.println("Thêm sản phẩm thành công!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void updateProduct() {
        System.out.println("=== Sửa sản phẩm ===");
        int id = readInt("Nhập ID cần sửa : ");
        System.out.print("Tên mới :");
        String name = scanner.nextLine();
        double price = readDouble("Giá mới :");
        int quantity = readInt("Số lượng mới: ");
        System.out.print("Loại mới : ");
        String category = scanner.nextLine();

        Product product = new Product(id, name, price, quantity, category);

        try {
            productService.updateProduct(id, product);
            System.out.println("Cập nhật sản phẩm thành công!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void deleteProduct() {
        System.out.println("=== Xóa sản phẩm ===");
        int id = readInt("Nhập ID cần xóa : ");

        try {
            productService.deleteProduct(id);
            System.out.println(" Xóa sản phẩm thành công! ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    private static void showProducts() {
        List<Product> products = productService.getProducts();
        if (products.isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }
        System.out.println("=== Danh sách sản phẩm ===");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void searchByName() {
        System.out.print("Nhập tên cần tìm : ");
        String name = scanner.nextLine();
        List<Product> result = productService.searchByName(name);
        if (result.isEmpty()) {
            System.out.print("Không tìm thấy sản phẩm");
            return;
        }
        result.forEach(System.out::println);
    }

    private static void sortByPriceAsc() {
        productService.sortByPriceAsc();
        showProducts();
        System.out.println("Đã sắp xếp theo giá tăng dần!");
    }

    private static void sortByPriceDesc() {
        productService.sortByPriceDesc();
        showProducts();
        System.out.println("Đã sắp xếp theo giá giảm dần!");
    }

}