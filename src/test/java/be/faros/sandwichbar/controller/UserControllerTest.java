package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.AddressDTO;
import be.faros.sandwichbar.dto.UserinfoDTO;
import be.faros.sandwichbar.dto.request.UpdateUserRequest;
import be.faros.sandwichbar.service.AddressService;
import be.faros.sandwichbar.service.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static be.faros.sandwichbar.mother.AddressMother.createAddressDTO;
import static be.faros.sandwichbar.mother.UserMother.createUpdateUserRequest;
import static java.util.Collections.emptyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class UserControllerTest extends ControllerTestBase {

    @MockBean
    private UserServiceImpl userService;
    @MockBean
    private AddressService addressService;

    private final String GET_USERINFO_URL = "/userInfo";
    private final String UPDATE_USERINFO_URL = "/userInfo";

    @Test
    @DisplayName("Get userInfo as user")
    void getUserInfo_asUser_success() throws Exception {
        when(userService.getUserInfo(eq(user))).thenReturn(new UserinfoDTO("abc-001", "", user.getEmail(), "", createAddressDTO(), emptyList()));

        mvc.perform(MockMvcRequestBuilders.get(GET_USERINFO_URL)
                        .with(oidcLogin().oidcUser(user)))
                .andExpect(status().isOk());

        verify(userService).getUserInfo(user);
    }

    @Test
    @DisplayName("Get userInfo as admin")
    void getUserInfo_asAdmin_success() throws Exception {
        when(userService.getUserInfo(eq(admin))).thenReturn(new UserinfoDTO("abc-001", "", admin.getEmail(), "", createAddressDTO(), emptyList()));

        mvc.perform(MockMvcRequestBuilders.get(GET_USERINFO_URL)
                        .with(oidcLogin().oidcUser(admin)))
                .andExpect(status().isOk());

        verify(userService).getUserInfo(admin);
    }

    @Test
    @DisplayName("Get userInfo without authentication")
    void getUserInfo_accessDenied() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(GET_USERINFO_URL)
                        .with(oidcLogin()))
                .andExpect(status().isForbidden());

        verify(userService, never()).getUserInfo(admin);
    }

    @Test
    @DisplayName("Update userInfo as user")
    void updateUserInfo_succes() throws Exception {
        AddressDTO address = createAddressDTO();
        UpdateUserRequest updateUserRequest = createUpdateUserRequest(address);
        String valueAsJson = objectMapper.writeValueAsString(updateUserRequest);

        mvc.perform(MockMvcRequestBuilders.put(UPDATE_USERINFO_URL)
                        .with(oidcLogin().oidcUser(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(valueAsJson))
                .andExpect(status().isOk());

        verify(userService).updateUserInfo(eq(user), eq(updateUserRequest));
    }

    @Test
    @DisplayName("Update userInfo as admin")
    void updateUserInfo_wrongRole() throws Exception {
        AddressDTO address = createAddressDTO();
        UpdateUserRequest updateUserRequest = createUpdateUserRequest(address);
        String valueAsJson = objectMapper.writeValueAsString(updateUserRequest);

        mvc.perform(MockMvcRequestBuilders.put(UPDATE_USERINFO_URL)
                        .with(oidcLogin().oidcUser(admin))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(valueAsJson))
                .andExpect(status().isForbidden());

        verify(userService, never()).updateUserInfo(eq(admin), eq(updateUserRequest));
        verify(addressService, never()).handleAddress(eq(updateUserRequest.address()));
    }

    @Test
    @DisplayName("Update userInfo without authentication")
    void updateUserInfo_accessDenied() throws Exception {
        AddressDTO address = createAddressDTO();
        UpdateUserRequest updateUserRequest = createUpdateUserRequest(address);
        String valueAsJson = objectMapper.writeValueAsString(updateUserRequest);

        mvc.perform(MockMvcRequestBuilders.put(UPDATE_USERINFO_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(valueAsJson))
                .andExpect(status().is3xxRedirection());

        verify(userService, never()).updateUserInfo(eq(admin), eq(updateUserRequest));
        verify(addressService, never()).handleAddress(eq(updateUserRequest.address()));
    }

}