package Service;

import dao.LocalizationDAO;
import model.LocalizationEntity;

import java.util.List;

/**
 * Created by nea on 30/09/16.
 */
public class LocalizationService {
    private LocalizationDAO localizationDAO;

    public LocalizationService() {
        localizationDAO = new LocalizationDAO();
    }

    public LocalizationService(LocalizationDAO localizationDAO) {
        this.localizationDAO = localizationDAO;
    }

    public List<LocalizationEntity> listLocalizations() {
        return this.localizationDAO.listLocalizations();
    }

    public List<LocalizationEntity> listLocalizationsByRef(String name) {
        return this.localizationDAO.listLocalizationsByName(name);
    }

    public List<LocalizationEntity> listLocalizationsById(int id) {
        return this.localizationDAO.listLocalizationsById(id);
    }

    public void addLocalization(LocalizationEntity s) throws Exception {
        this.localizationDAO.addLocalization(s);
    }

    public void removeLocalization(LocalizationEntity s) throws Exception {
        this.localizationDAO.removeLocalization(s);
    }
    public void updateLocalization(LocalizationEntity s) throws Exception {
        this.localizationDAO.updateLocalization(s);
    }
}
