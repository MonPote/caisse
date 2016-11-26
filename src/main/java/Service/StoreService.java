package Service;

/**
 * Created by nea on 30/09/16.
 */
import dao.StoreDAO;
import model.StoreEntity;

import java.util.List;

public class StoreService {
    private StoreDAO storeDAO;
    
    public StoreService() {
        storeDAO = new StoreDAO();
    }

    public StoreService(StoreDAO storeDAO) {
        this.storeDAO = storeDAO;
    }

    public List<StoreEntity> listStores() {
        return this.storeDAO.listStores();
    }

    public List<StoreEntity> listStoresByName(String name) {
        return this.storeDAO.listStoresByName(name);
    }

    public List<StoreEntity> listStoresById(int id) {
        return this.storeDAO.listStoresById(id);
    }

    public void addStore(StoreEntity s) throws Exception {
        this.storeDAO.addStore(s);
    }

    public void removeStore(StoreEntity s) throws Exception {
        this.storeDAO.removeStore(s);
    }
    public void updateStore(StoreEntity s) throws Exception {
        this.storeDAO.updateStore(s);
    }
}
