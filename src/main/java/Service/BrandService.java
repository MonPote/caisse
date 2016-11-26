package Service;

import dao.BrandDAO;
import model.BrandEntity;

import java.util.List;

/**
 * Created by nea on 30/09/16.
 */
public class BrandService {
    private BrandDAO brandDAO;

    public BrandService() {
        brandDAO = new BrandDAO();
    }

    public BrandService(BrandDAO brandDAO) {
        this.brandDAO = brandDAO;
    }

    public List<BrandEntity> listBrands() {
        return this.brandDAO.listBrands();
    }

    public List<BrandEntity> listBrandsByName(String name) {
        return this.brandDAO.listBrandsByName(name);
    }

    public List<BrandEntity> listBrandsById(int id) {
        return this.brandDAO.listBrandsById(id);
    }

    public void addBrand(BrandEntity s) throws Exception {
        this.brandDAO.addBrand(s);
    }

    public void removeBrand(BrandEntity s) throws Exception {
        this.brandDAO.removeBrand(s);
    }
    public void updateBrand(BrandEntity s) throws Exception {
        this.brandDAO.updateBrand(s);
    }
}
