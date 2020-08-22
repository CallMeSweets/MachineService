package com.configuration.machine.app.actuator.contributors;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ProjectInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
            builder.withDetail("Creator:", "Sebastian Grzelak")
                    .withDetail("Contact:", "grz3lak1997@gmail.com")
                    .withDetail("Date:", new Date());

    }
}
