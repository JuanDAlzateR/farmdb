package com.solvd.farm.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

//@Displayable
public class Farm {

    public static final Logger LOGGER = LogManager.getLogger(Farm.class);
    private String farmName;
    private String latitude;
    private String longitude;
//    private LocalDate date;


    public Farm() {
//        this.date = LocalDate.now();
        this.farmName = "New Farm";
        this.latitude = "6.265643";
        this.longitude = "-75.574925";
    }

    public Farm(String farmName) {
        this();
        this.farmName = farmName;

    }

    public Farm(String farmName, String latitude, String longitude) {
        this(farmName);
        this.latitude = latitude;
        this.longitude = longitude;

    }

//    public Farm(LocalDate date) {
//        this();
//        this.date = date;
//    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getFarmName() {
        return farmName;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void displayWeather() {
        Weather.WEATHER.getWeather(latitude, longitude);
    }

//    public WeatherThread weatherTread(int seconds) {
//        WeatherTask weatherTask=new WeatherTask(latitude,longitude,seconds);
//        WeatherThread weatherThread=new WeatherThread(weatherTask);
//        return weatherThread;
//    }

//    public <T extends Countable & IPassTime> void applyPassTime(GenericList<T> genericList, int days) {
//        genericList.getList().stream()
//                .forEach(item -> item.passTime(days));
//    }

//    @Override
//    public void passTime(int days) {
//        this.date = this.date.plusDays(days);
//
//        applyPassTime(crops, days);
//        applyPassTime(products, days);
//        applyPassTime(tools, days);
//
//        animalSet.passTime(days);
//    }
//



}