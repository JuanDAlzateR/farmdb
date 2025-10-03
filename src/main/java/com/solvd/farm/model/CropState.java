package com.solvd.farm.model;

public enum CropState {
    READY_TO_PLANT, PLANTED, GROWING, READY_TO_HARVEST, HARVESTED;

    public CropState next() {
        CropState[] states = values();
        int nextOrdinal = (this.ordinal() + 1) % states.length;
        return states[nextOrdinal];
    }

}
