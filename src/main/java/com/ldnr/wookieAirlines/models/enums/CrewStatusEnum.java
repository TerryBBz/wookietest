package com.ldnr.wookieAirlines.models.enums;

public enum CrewStatusEnum {
    AVAILABLE("Disponible"),
    ASSIGNED("Assigné en mission"),
    IN_MISSION("En mission"),
    DISSOLVED("Dissous");

    private final String status;

    CrewStatusEnum(String status) {
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