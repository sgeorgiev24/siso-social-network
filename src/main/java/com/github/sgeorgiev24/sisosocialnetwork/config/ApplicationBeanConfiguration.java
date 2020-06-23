package com.github.sgeorgiev24.sisosocialnetwork.config;

import com.github.sgeorgiev24.sisosocialnetwork.mapping.MappingsInitializer;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.ModelAndView;

@Configuration
public class ApplicationBeanConfiguration {
  private static ModelMapper modelMapper;

  static {
    modelMapper = new ModelMapper();
    MappingsInitializer.initMappings(modelMapper);
  }

  @Bean
  public ModelMapper modelMapper() {
    return modelMapper;
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
