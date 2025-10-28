package com.solvd.farm.dao.interfaces;

import com.solvd.farm.model.Countable;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface ICountableDAO {
    void save(Countable countable);

    Countable getCountableById(int id);

    ArrayList<Countable> countableList();

    //the Param anottation it's only used for Mybatis
    void update(@Param("id") int id, @Param("name") String name, @Param("quantity") float quantity, @Param("farmId") int farmId);
}
