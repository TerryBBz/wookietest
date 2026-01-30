package com.ldnr.wookieAirlines.filters;

import com.ldnr.wookieAirlines.models.enums.AssignedPilotStatusEnum;

public class AssignedPilotFilter implements Filter<AssignedPilotStatusEnum> {

    private AssignedPilotStatusEnum status;
    private String search;

    @Override
    public AssignedPilotStatusEnum getStatus() {
        return status;
    }

    @Override
    public void setStatus(AssignedPilotStatusEnum status) {
        this.status = status;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}