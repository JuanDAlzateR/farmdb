package com.solvd.farm.service.interfaces;

import com.solvd.farm.model.Purchasable;

public interface IPurchasableService {
    void save(Purchasable purchasable);

    Purchasable getPurchasableById(int id);

    void update(Purchasable purchasable);
}
