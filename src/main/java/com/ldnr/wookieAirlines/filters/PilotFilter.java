package com.ldnr.wookieAirlines.filters;
import com.ldnr.wookieAirlines.models.enums.PilotStatusEnum;

public class PilotFilter implements Filter<PilotStatusEnum> {

    private PilotStatusEnum status;
    private String search;

    @Override
    public PilotStatusEnum getStatus() {
        return status;
    }

    @Override
    public void setStatus(PilotStatusEnum status) {
        this.status = status;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}