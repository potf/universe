package com.fluffy.universe.controllers;

import com.fluffy.universe.models.Role;
import com.fluffy.universe.services.PostService;
import com.fluffy.universe.utils.SessionUtils;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HomeControllerTest {

    private HomeController homeController;
    @Mock private Javalin application;
    @Mock private PostService postService;
    @Mock private Context context;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        homeController = new HomeController(application);
    }

    @Test
    void homePage_shouldRenderHomePageTemplate() {
        // Arrange
        when(context.queryParam("page")).thenReturn("1");
        when(context.queryParam("size")).thenReturn("10");
        when(postService.getPostCount()).thenReturn(15);
        when(postService.getUserPosts(1, 10)).thenReturn(getMockPosts());
        when(SessionUtils.getCurrentModel(context)).thenReturn(new HashMap<>());

        // Act
        homeController.homePage(context);

        // Assert
        verify(context).render(eq("/views/pages/home.vm"), anyMap());
    }

    @Test
    void homePage_shouldSetPaginationAttributesInModel() {
        // Arrange
        when(context.queryParam("page")).thenReturn("1");
        when(context.queryParam("size")).thenReturn("10");
        when(postService.getPostCount()).thenReturn(15);
        when(postService.getUserPosts(1, 10)).thenReturn(getMockPosts());
        Map<String, Object> model = new HashMap<>();
        when(SessionUtils.getCurrentModel(context)).thenReturn(model);

        // Act
        homeController.homePage(context);

        // Assert
        assertEquals(getMockPosts(), model.get("posts"));
        assertEquals(15, model.get("paginationRecordCount"));
        assertEquals(10, model.get("paginationPageSize"));
        assertEquals(1, model.get("paginationCurrentPage"));
        assertEquals(2, model.get("paginationSpread"));
        assertEquals("/", model.get("paginationBaseURL"));
        assertEquals(Arrays.asList(4, 8, 12), model.get("paginationPageSizeOptions"));
    }

    @Test
    void homePage_shouldHandleInvalidPageNumber() {
        // Arrange
        when(context.queryParam("page")).thenReturn("999");
        when(context.queryParam("size")).thenReturn("10");
        when(postService.getPostCount()).thenReturn(15);
        when(postService.getUserPosts(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        Map<String, Object> model = new HashMap<>();
        when(SessionUtils.getCurrentModel(context)).thenReturn(model);

        // Act
        homeController.homePage(context);

        // Assert
        assertEquals(2, model.get("paginationCurrentPage"));
    }

    @Test
    void homePage_shouldHandleInvalidPageSize() {
        // Arrange
        when(context.queryParam("page")).thenReturn("1");
        when(context.queryParam("size")).thenReturn("20"); // Invalid size
        when(postService.getPostCount()).thenReturn(15);
        when(postService.getUserPosts(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        Map<String, Object> model = new HashMap<>();
        when(SessionUtils.getCurrentModel(context)).thenReturn(model);

        // Act
        homeController.homePage(context);

        // Assert
        assertEquals(4, model.get("paginationPageSize"));
    }

    @Test
    void registerRoutes_shouldRegisterRoutesForHomePage() {
        // Arrange
        Role[] roles = Role.values();

        // Act
        homeController.registerRoutes(application);

        // Assert
        verify(application).get("/", homeController::homePage, roles);
        verify(application).get("/home", homeController::homePage, roles);
    }

    @Test
    void registerRoutes_shouldRegisterRoutesWithCorrectRoles() {
        // Arrange
        Role[] expectedRoles = Role.values();

        // Act
        homeController.registerRoutes(application);

        // Assert
        verify(application).get("/", homeController::homePage, expectedRoles);
        verify(application).get("/home", homeController::homePage, expectedRoles);
    }

    @Test
    void registerRoutes_shouldThrowExceptionForNullJavalinApplication() {
        // Arrange
        Javalin nullApplication = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> homeController.registerRoutes(nullApplication));
    }

    private List<Map<String, Object>> getMockPosts() {
        // Return a list of mock posts
        List<Map<String, Object>> posts = new ArrayList<>();
        // Add mock post 1
        Map<String, Object> post1 = new HashMap<>();
        post1.put("id", 1);
        post1.put("title", "Post 1");
        post1.put("content", "Content of Post 1");
        posts.add(post1);
        // Add mock post 2
        Map<String, Object> post2 = new HashMap<>();
        post2.put("id", 2);
        post2.put("title", "Post 2");
        post2.put("content", "Content of Post 2");
        posts.add(post2);
        return posts;
    }
}

