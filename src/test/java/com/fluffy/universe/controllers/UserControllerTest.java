package com.fluffy.universe.controllers;

import com.fluffy.universe.models.Role;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;

import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Test
    public void registerRoutesTest() {
        Javalin app = Mockito.mock(Javalin.class);
        UserController usrctr=new UserController(app);
        usrctr.registerRoutes(app);
        Mockito.inOrder(app).verify(app, VerificationModeFactory.calls(12)).get(Mockito.anyString(), Mockito.any(Handler.class), Mockito.any(Role.class));
    }


}
