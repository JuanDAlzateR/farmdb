package com.solvd.farm.dao.interfaces;

import com.solvd.farm.model.Countable;

import java.util.ArrayList;

public interface ICountableDAO {
    void save(Countable countable);

    Countable getCountableById(int id);

    ArrayList<Countable> countableList();

    void update(int id, String name, float quantity, int farmId);
}
