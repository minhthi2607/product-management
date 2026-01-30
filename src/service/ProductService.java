package service;

import comparator.PriceAscComparator;
import comparator.PriceDescComparator;
import exception.DuplicateProductIdException;
import exception.InvalidProductException;
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
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product)
            throws DuplicateProductIdException, InvalidProductException {

        validateProduct(product);

        if (findById(product.getId()) != null) {
            throw new DuplicateProductIdException("ID sản phẩm đã tồn tại! ");
        }

        products.add(product);
        FileUtils.writeToFile(products);
    }

    public void updateProduct(int id, Product newProduct)
            throws ProductNotFoundException, InvalidProductException {
        Product oldProduct = findById(id);

        if(oldProduct == null){
            throw new ProductNotFoundException("Không tìm thấy sản phẩm với ID: " + id);
        }

        validateProduct(newProduct);

        oldProduct.setName(newProduct.getName());
        oldProduct.setPrice(newProduct.getPrice());
        oldProduct.setQuantity(newProduct.getQuantity());

        FileUtils.writeToFile(products);

    }

    private static void validateProduct(Product product) throws InvalidProductException {
        if (!ValidateUtils.isValidName(product.getName())) {
            throw new InvalidProductException("Tên sản phẩm không hợp lệ!");
        }

        if (!ValidateUtils.isValidPrice(product.getPrice())) {
            throw new InvalidProductException("Giá sản phẩm phải > 0");
        }

        if (!ValidateUtils.isValidQuantity(product.getQuantity())) {
            throw new InvalidProductException("Số lượng sản phẩm phải > 0");
        }

    }

    public void deleteProduct(int id) throws ProductNotFoundException {
        Product product = findById(id);
        if(product == null){
            throw new ProductNotFoundException("Không tìm thấy sản phẩm với ID: " + id);
        }
        products.remove(product);
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
        products.sort(new PriceAscComparator());
    }

    public void sortByPriceDesc() {
        products.sort(new PriceDescComparator());
    }

    private Product findById(int id)  {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
