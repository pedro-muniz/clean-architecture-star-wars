package br.com.cleanarchitecture.starwars.infrastructure.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerLoggingAspect {
    private final Logger logger = LogManager.getLogger();

    @Before("within(br.com.cleanarchitecture.starwars.infrastructure.delivery.controllers..*)")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Iniciando a execução da controller {}", joinPoint);
    }

    @AfterReturning("within(br.com.cleanarchitecture.starwars.infrastructure.delivery.controllers..*)")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("Controller {} executada sem errors", joinPoint);
    }
}
