package com.hjm.portal;

import com.hjm.common.Const;
import com.hjm.common.ServerResponse;
import com.hjm.pojo.Client;
import com.hjm.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @author hjm
 */
@CrossOrigin(allowCredentials = "true",maxAge = 3600)
@RestController
@RequestMapping("/portal/client")
public class ClientController {
    @Autowired
    private IUserService iUserService;
    @GetMapping("/login")
    public ServerResponse<Client> login(String username, String password, HttpSession session){
        try {
            LogManager.getLogger().debug(session.getId());
            ServerResponse<Client> response = iUserService.login(username, password);
            if (response.isSuccess()){
                session.setAttribute(Const.CURRENT_USER,response.getData());
            }
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }
    @GetMapping("/logout")
    public ServerResponse<String> logout(HttpSession session){
        try {
            session.removeAttribute(Const.CURRENT_USER);
            session.removeAttribute(Const.USERNAME);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }
    @PostMapping("/register")
    public ServerResponse<String> register(@RequestBody Client client){
        try {
            return iUserService.register(client);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }
    @GetMapping("/checkValid")
    public ServerResponse<String> checkValid(String username){
        try {
            return iUserService.checkValid(username);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }
    @GetMapping("/getUserInfo")
    public ServerResponse<Client> getUserInfo(HttpSession session){
        try {
            String username = (String) session.getAttribute(Const.USERNAME);
            if (username!=null){
                return iUserService.getInfoByUsername(username);
            }else {
                return ServerResponse.createByErrorCodeMessage(
                        ServerResponse.ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                        ServerResponse.ResponseCode.ILLEGAL_ARGUMENT.getDesc());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }
    @PatchMapping("/resetPassword")
    public ServerResponse<String> resetPassword(HttpSession session,String oldPassword,String newPassword){
        try {
            Client client = (Client) session.getAttribute(Const.CURRENT_USER);
            ServerResponse<String> response = iUserService.resetPassword(oldPassword, newPassword, client);
            if (response.isSuccess()){
                client.setPassword(newPassword);
                session.setAttribute(Const.CURRENT_USER,client);
            }
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }
    @PutMapping("/update_information")
    public ServerResponse<Client> update_information(HttpSession session,@RequestBody Client client){
        try {
            client.setId(((Client)session.getAttribute(Const.CURRENT_USER)).getId());
            ServerResponse<Client> response = iUserService.updateInformation(client);
            if (response.isSuccess()){
                session.setAttribute(Const.CURRENT_USER,client);
                return response;
            }else {
                return ServerResponse.createByErrorCodeMessage(
                        ServerResponse.ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                        ServerResponse.ResponseCode.ILLEGAL_ARGUMENT.getDesc());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }
    @GetMapping("/get_information")
    public ServerResponse<Client> get_information(HttpSession session){
        try {
            return iUserService.getInformation(((Client)session.getAttribute(Const.CURRENT_USER)).getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }

}
