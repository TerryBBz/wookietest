package com.ldnr.wookieAirlines.models.enums;

/**
 *
 * @author Terry
 */
public enum MissionStatusEnum {
    IN_PROGRESS("En cours"),
    DONE("Terminée");

    private final String status;

    MissionStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status;
    }
}