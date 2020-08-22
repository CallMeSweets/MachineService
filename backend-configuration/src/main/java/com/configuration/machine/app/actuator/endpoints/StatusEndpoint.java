package com.configuration.machine.app.actuator.endpoints;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "status")
public class StatusEndpoint {

    private String appStatus = "NOT_READY";

    @ReadOperation
    public String getApplicationStatus(){
        return  appStatus;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void setAppStatus(){
        appStatus = "READY";
    }


}
