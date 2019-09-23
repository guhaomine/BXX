package com.hjm.backend;

import com.github.pagehelper.PageInfo;
import com.hjm.common.Const;
import com.hjm.common.ServerResponse;
import com.hjm.pojo.Client;
import com.hjm.service.IAdminService;
import com.hjm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author hjm
 */
@RestController
@RequestMapping("/backend/client")
public class ClientManagerController {
    @Autowired
    IUserService iUserService;
    @Autowired
    IAdminService iAdminService;
    @GetMapping("/admin_login")
    public ServerResponse<Client> admin_login(String username, String password, HttpSession session){
        try {
            ServerResponse<Client> response = iUserService.login(username, password);
            if (response.isSuccess() && iUserService.checkAdminRole(response.getData()).isSuccess()){
                session.setAttribute(Const.CURRENT_USER,response.getData());
                return response;
            }else{
                return ServerResponse.createByErrorCodeMessage(
                        ServerResponse.ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                        ServerResponse.ResponseCode.ILLEGAL_ARGUMENT.getDesc());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }
    @GetMapping("/checkUser")
    public ServerResponse checkUser(String username,HttpSession session){
        try {
            return iUserService.checkValid(username);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }
    @DeleteMapping("/deleteClient")
    public ServerResponse deleteClient(HttpSession session,Integer id){
        try {
            return iAdminService.deleteClient(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }
    @DeleteMapping("/deleteBill")
    public ServerResponse deleteBill(HttpSession session,Integer id){
        try {
            return iAdminService.deleteBill(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }
    @GetMapping("/listAllClient")
    public ServerResponse<PageInfo> listAllClient(HttpSession session,Integer page,Integer size){
        try {
            ServerResponse response = iUserService.checkAdminRole((Client) session.getAttribute(Const.CURRENT_USER));
            if (response.isSuccess()){
                return iAdminService.listAllClient(page,size);
            }
            return ServerResponse.createByErrorCodeMessage(ServerResponse.ResponseCode.ILLEGAL_ARGUMENT.getCode(),ServerResponse.ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }

}
