package com.fluffy.universe.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoleTest {

    @Test
    public void testGetId() {
        Role role = Role.GUEST;
        Integer expectedId = 0;

        Integer actualId = role.getId();

        Assertions.assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetName() {
        Role role = Role.USER;
        String expectedName = "User";

        String actualName = role.getName();

        Assertions.assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetRoleById() {
        Integer roleId = 1;
        Role expectedRole = Role.USER;

        Role actualRole = Role.getRoleById(roleId);

        Assertions.assertEquals(expectedRole, actualRole);
    }

    @Test
    public void testGetRoleByIdWithNullId() {
        Integer roleId = null;
        Role expectedRole = Role.GUEST;

        Role actualRole = Role.getRoleById(roleId);

        Assertions.assertEquals(expectedRole, actualRole);
    }

    @Test
    public void testGetRoleByIdWithInvalidId() {
        Integer roleId = 2;

        Assertions.assertThrows(RuntimeException.class, () -> {
            Role.getRoleById(roleId);
        });
    }
}
