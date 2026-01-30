package com.ldnr.wookieAirlines.filters;
import com.ldnr.wookieAirlines.models.enums.FighterStatusEnum;

public class FighterFilter implements Filter<FighterStatusEnum> {

    private FighterStatusEnum status;

    @Override
    public FighterStatusEnum getStatus() {
        return status;
    }

    @Override
    public void setStatus(FighterStatusEnum status) {
        this.status = status;
    }
}