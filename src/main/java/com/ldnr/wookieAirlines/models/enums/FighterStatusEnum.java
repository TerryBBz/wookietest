package com.ldnr.wookieAirlines.models.enums;

/**
 *
 * @author Terry
 */
public enum FighterStatusEnum {
    OPERATIONAL("Operationnel"),
    DESTROYED("Détruit"),
    MAINTENANCE("Maintenance"),
    MAINTENANCE_BUT_OPERATIONAL("En maintenance mais operationnel"),
    ASSIGNED("Assigné en mission"),
    IN_MISSION("En mission");


    private final String status;

    FighterStatusEnum(String status) {
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