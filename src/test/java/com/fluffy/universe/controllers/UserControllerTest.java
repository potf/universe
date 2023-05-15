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


    private UserController userController;
    private Context context;

    @BeforeEach
    public void setup() {
        userController = new UserController(null); // Pass a Javalin instance if necessary
        context = Mockito.mock(Context.class);
    }

    @Test
    public void testSignUpPage() {
        // Arrange

        // Act
        userController.signUpPage(context);

        // Assert
        verify(context).render(eq("/views/pages/models/user/sign-up.vm"));
    }
}
