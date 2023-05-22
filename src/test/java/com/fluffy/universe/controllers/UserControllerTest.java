package com.fluffy.universe.controllers;

import com.fluffy.universe.controllers.UserController;
import com.fluffy.universe.models.Role;
import com.fluffy.universe.models.User;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Test
    public void registerRoutesTest() {
        Javalin app = Mockito.mock(Javalin.class);
        UserController usrctr=new UserController(app);
        usrctr.registerRoutes(app);
        Mockito.inOrder(app).verify(app, VerificationModeFactory.calls(12)).get(Mockito.anyString(), Mockito.any(Handler.class), Mockito.any(Role.class));
    }
}