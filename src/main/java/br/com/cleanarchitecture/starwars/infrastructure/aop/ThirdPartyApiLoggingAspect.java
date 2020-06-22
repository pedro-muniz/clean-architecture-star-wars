package br.com.cleanarchitecture.starwars.infrastructure.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ThirdPartyApiLoggingAspect {
    private final Logger logger = LogManager.getLogger();

    @Before("within(br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi..*)")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Iniciando a execução da api de terceiros {}", joinPoint);
    }

    @AfterReturning("within(br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi..*)")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("Api de terceiros {} executado sem errors", joinPoint);
    }

    @AfterThrowing(pointcut = "within(br.com.cleanarchitecture.starwars.infrastructure.thirdpartyapi..*)", throwing = "ex")
    public void logThrows(JoinPoint joinPoint, Exception ex) {
        logger.warn("Erro executando a api de terceiros {}", joinPoint);
        logger.debug(ex);
    }
}
