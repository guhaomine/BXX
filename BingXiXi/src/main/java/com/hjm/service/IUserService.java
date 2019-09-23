package com.hjm.service;

import com.hjm.common.ServerResponse;
import com.hjm.pojo.Client;

/**
 * @author hjm
 */
public interface IUserService {
    ServerResponse<Client> login(String username, String password);
    ServerResponse<String> register(Client client);
    ServerResponse<String> checkValid(String username);
    ServerResponse<String> resetPassword(String oldPassword,String newPassword,Client client);
    ServerResponse<Client> updateInformation(Client client);
    ServerResponse<Client> getInformation(Integer id);
    ServerResponse checkAdminRole(Client client);
    ServerResponse getInfoByUsername(String username);
}
