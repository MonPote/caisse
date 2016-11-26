package Service;

import dao.ProductDAO;
import model.ProductEntity;

import java.util.List;

/**
 * Created by nea on 30/09/16.
 */
public class ProductService {
    private ProductDAO productDAO;

    public ProductService() {
        productDAO = new ProductDAO();
    }

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<ProductEntity> listProducts() {
        return this.productDAO.listProducts();
    }

    public List<ProductEntity> listProductsByRef(String ref) {
        return this.productDAO.listProductsByRef(ref);
    }

    public List<ProductEntity> listProductsById(int id) {
        return this.productDAO.listProductsById(id);
    }

    public void addProduct(ProductEntity s) throws Exception {
        this.productDAO.addProduct(s);
    }

    public void removeProduct(ProductEntity s) throws Exception {
        this.productDAO.removeProduct(s);
    }
    public void updateProduct(ProductEntity s) throws Exception {
        this.productDAO.updateProduct(s);
    }
}
