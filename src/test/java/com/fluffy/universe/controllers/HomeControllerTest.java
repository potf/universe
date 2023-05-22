package com.fluffy.universe.controllers;

import com.fluffy.universe.controllers.HomeController;
import com.fluffy.universe.models.Role;
import com.fluffy.universe.utils.Configuration;
import com.fluffy.universe.utils.ServerData;
import com.fluffy.universe.utils.SessionUtils;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class HomeControllerTest {
    @Test
    public void registerRoutestest() {
        Javalin application=Mockito.mock(Javalin.class);
        HomeController hc=new HomeController(application);
        hc.registerRoutes(application);
        Mockito.inOrder(application).verify(application, VerificationModeFactory.calls(3)).get(Mockito.anyString(), Mockito.any(Handler.class), Mockito.any(Role.class));
    }
}