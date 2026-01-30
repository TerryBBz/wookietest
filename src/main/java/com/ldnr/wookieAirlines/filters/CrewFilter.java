package com.ldnr.wookieAirlines.filters;
import com.ldnr.wookieAirlines.models.enums.CrewStatusEnum;

public class CrewFilter implements Filter<CrewStatusEnum> {

    private CrewStatusEnum status;
    private String search;

    @Override
    public CrewStatusEnum getStatus() {
        return status;
    }

    @Override
    public void setStatus(CrewStatusEnum status) {
        this.status = status;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}