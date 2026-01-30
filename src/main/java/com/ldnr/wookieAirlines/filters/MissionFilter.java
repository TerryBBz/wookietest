package com.ldnr.wookieAirlines.filters;
import com.ldnr.wookieAirlines.models.enums.MissionStatusEnum;

public class MissionFilter implements Filter<MissionStatusEnum> {

    private MissionStatusEnum status;

    @Override
    public MissionStatusEnum getStatus() {
        return status;
    }

    @Override
    public void setStatus(MissionStatusEnum status) {
        this.status = status;
    }
}