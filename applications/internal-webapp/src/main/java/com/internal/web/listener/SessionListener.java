package com.internal.web.listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Date;

@WebListener
public class SessionListener implements HttpSessionListener {

//    @Autowired
//    private UserSessionRepository userSessionRepository;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // Se ejecuta cuando se inicia la sesión
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        UserSession userSession = new UserSession();
//        userSession.setUsername(username);
//        userSession.setStartTime(new Date());
//        userSessionRepository.save(userSession);
        System.out.println("se ha creado una session leyendo info de la base de datos "+username);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // Se ejecuta cuando la sesión termina
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        UserSession userSession = userSessionRepository.findByUsername(username);
//        if (userSession != null) {
//            userSession.setEndTime(new Date());
//            userSessionRepository.save(userSession);
//        }
        System.out.println("se ha destruido una session guardando en la base de datos" +username);
    }
}