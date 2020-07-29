package com.adclear.customervalidator.exception;


/**
 * @author R.Fazaeli
 */
public enum ApplicationMessageTypeEnum {

    ERROR("ERROR"), WARN("WARN"), INFO("INFO");
    private String type;

    ApplicationMessageTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
