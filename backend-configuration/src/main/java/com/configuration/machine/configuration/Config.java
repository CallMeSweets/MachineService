package com.configuration.machine.configuration;

import com.configuration.machine.converters.ConverterDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class Config {

    @Bean
    @Primary
    public ConverterDTO getConvertedDTO(){
        return new ConverterDTO();
    }

}
