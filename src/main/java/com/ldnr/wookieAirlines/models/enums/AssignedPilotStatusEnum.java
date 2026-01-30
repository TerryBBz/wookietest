package com.ldnr.wookieAirlines.models.enums;

/**
 *
 * @author Terry
 */
public enum AssignedPilotStatusEnum {
    WAITING("En attente"),
    ASSIGNED("En mission");

    private final String status;

    AssignedPilotStatusEnum(String status) {
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