package br.com.cleanarchitecture.starwars.infrastructure.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UseCaseLoggingAspect {
    private final Logger logger = LogManager.getLogger();

    @Before("within(br.com.cleanarchitecture.starwars.core.usecases..*)")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Iniciando a execução do use case {}", joinPoint);
    }

    @AfterReturning("within(br.com.cleanarchitecture.starwars.core.usecases..*)")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("Use case {} executado sem errors", joinPoint);
    }

    @AfterThrowing(pointcut = "within(br.com.cleanarchitecture.starwars.core.usecases..*)", throwing = "ex")
    public void logThrows(JoinPoint joinPoint, Exception ex) {
        logger.warn("Erro executando o use case {}", joinPoint);
        logger.debug(ex);
    }
}
