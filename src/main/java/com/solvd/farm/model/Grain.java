package com.solvd.farm.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Grain extends Purchasable {

    public static final Logger LOGGER = LogManager.getLogger(Grain.class);

    public Grain(String name, float quantity) {
        super(name, quantity);
    }

    public Grain(String name, float quantity, float price) {
        super(name, quantity, price);

    }

    public Grain() {
        super("New grain", 0);
    }

    @Override
    public String toString() {
        return (this.getQuantity() + " seeds of " + this.getName());
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Grain)) {
            return false;
        } else {
            return (((Grain) object).getName().equals(this.getName()));
        }
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }

    public void sow(int quantity) {
        if (quantity <= this.getQuantity() && quantity >= 0) {
            this.addQuantity(-quantity);
        } else if (quantity >= 0) {
            this.setQuantity(0);
            LOGGER.info("All seeds sown. Quantity of " + this.getName() + " set to 0.");
        } else {
            LOGGER.info("Error, you can't sow a negative amount");
        }
    }

}