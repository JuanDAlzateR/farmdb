package com.solvd.farm.dao.interfaces;

import com.solvd.farm.model.Countable;
import com.solvd.farm.model.Purchasable;

import java.util.ArrayList;

public interface IPurchasableDAO {
    void save(Purchasable purchasable);

    Countable getPurchasableById(int id);

    ArrayList<Purchasable> purchasableList();

    void update(Purchasable purchasable);
}
