package by.epam.bikesharing.service;

import by.epam.bikesharing.dao.ModelDao;
import by.epam.bikesharing.entity.BikeModel;
import by.epam.bikesharing.validation.MoneyValidation;

import java.math.BigDecimal;

public class BikeModelLogic {

    public boolean addModel(String name, String cost, String image) {

        if (!MoneyValidation.isValid(cost))
            return false;
        BikeModel model = new BikeModel();
        model.setName(name);
        model.setCost(new BigDecimal(cost));
        if (!image.isEmpty()) {
            model.setImageUrl(image);
        }
        ModelDao dao = new ModelDao();
        dao.create(model);
        dao.closeConnection();
        return true;
    }

    public boolean editModelCost(Long id, String cost) {
        if (!MoneyValidation.isValid(cost))
            return false;
        BikeModel model = new BikeModel();
        model.setId(id);
        model.setCost(new BigDecimal(cost));
        ModelDao dao = new ModelDao();
        dao.update(model);
        dao.closeConnection();
        return true;
    }
}