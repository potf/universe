package com.fluffy.universe.controllers;

import com.fluffy.universe.models.Role;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;

public class HomeControllerTest {
    @Test
    public void registerRoutestest() {
        Javalin application=Mockito.mock(Javalin.class);
        HomeController hc=new HomeController(application);
        hc.registerRoutes(application);
        Mockito.inOrder(application).verify(application, VerificationModeFactory.calls(3)).get(Mockito.anyString(), Mockito.any(Handler.class), Mockito.any(Role.class));
    }
}