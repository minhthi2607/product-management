package service;

import exception.DuplicateProductIdException;
import exception.InvalidPriceException;
import exception.ProductNotFoundException;
import model.Product;
import utils.FileUtils;
import utils.ValidateUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private List<Product> products;

    public ProductService() {
        products = FileUtils.readFromFile();
        if (products == null) {
            products = new ArrayList<>();
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) throws DuplicateProductIdException, InvalidPriceException {

        for (Product p : products) {
            if (p.getId() == product.getId()) {
                throw new DuplicateProductIdException("ID sản phẩm đã tồn tại!");
            }
        }

        if (product.getPrice() <= 0) {
            throw new InvalidPriceException("Giá sản phẩm phải > 0");
        }

        if (!ValidateUtils.isValidName(product.getName())) {
            throw new InvalidPriceException("Tên sản phẩm không hợp lệ!");
        }


        products.add(product);
        FileUtils.writeToFile(products);
    }

    public List<Product> searchByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(p);
            }
        }
        return result;
    }

    public void sortByPriceAsc() {
        products.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
    }

    public void sortByPriceDesc() {
        products.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
    }

    public void updateProduct(int id, Product newProduct) throws ProductNotFoundException, InvalidPriceException {
        Product product = findById(id);
        if (newProduct.getPrice() <= 0) {
            throw new InvalidPriceException("Giá sản phẩm phải > 0");
        }

        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        product.setQuantity(newProduct.getQuantity());
        product.setCategory(newProduct.getCategory());

        FileUtils.writeToFile(products);

    }

    private Product findById(int id) throws ProductNotFoundException {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        throw new ProductNotFoundException("Không tìm thấy sản phẩm với ID: " + id);

    }

    public void deleteProduct(int id) throws ProductNotFoundException {
        Product product = findById(id);
        products.remove(product);
        FileUtils.writeToFile(products);
    }


}
