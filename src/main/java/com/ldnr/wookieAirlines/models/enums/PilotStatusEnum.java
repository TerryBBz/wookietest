package com.ldnr.wookieAirlines.models.enums;

/**
 *
 * @author Terry
 */
public enum PilotStatusEnum {
    AVAILABLE("Disponible"),
    WOUNDED("Blessé"),
    WOUNDED_BUT_AVAILABLE("Blessé mais disponible"),
    DEAD("Mort"),
    ASSIGNED("Assigné en mission"),
    IN_MISSION("Mission en cours");

    private final String status;

    PilotStatusEnum(String status) {
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