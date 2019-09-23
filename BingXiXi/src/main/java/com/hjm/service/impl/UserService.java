package com.hjm.service.impl;

import com.hjm.common.Const;
import com.hjm.common.ServerResponse;
import com.hjm.dao.ClientMapper;
import com.hjm.pojo.Client;
import com.hjm.pojo.ClientExample;
import com.hjm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hjm
 */
@Service
public class UserService implements IUserService {
    @Autowired
    ClientMapper clientMapper;
    @Override
    public ServerResponse<Client> login(String username, String password) {
        ClientExample example = new ClientExample();
        ClientExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password);
        List<Client> list = clientMapper.selectByExample(example);
        return list.size()==0? ServerResponse.createByError():ServerResponse.createBySuccess(list.get(0));
    }

    @Override
    public ServerResponse<String> register(Client client) {
        client.setRights(Const.Role.ROLE_CLIENT);
        clientMapper.insert(client);
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServerResponse<String> checkValid(String username) {
        ClientExample example = new ClientExample();
        ClientExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<Client> list = clientMapper.selectByExample(example);
        return list.size()==0? ServerResponse.createByErrorCodeMessage(ServerResponse.ResponseCode.ILLEGAL_ARGUMENT.getCode(),"用户名已被使用"):ServerResponse.createBySuccessMessage("用户名可以使用");
    }

    @Override
    public ServerResponse<String> resetPassword(String oldPassword, String newPassword, Client client) {
        if (client.getId()!=null){
            Client queryClient = clientMapper.selectByPrimaryKey(client.getId());
            if (client.getPassword().equals(oldPassword)&&client.getPassword().equals(queryClient.getPassword())){
                client.setPassword(newPassword);
                clientMapper.updateByPrimaryKeySelective(client);
                return ServerResponse.createBySuccess("修改密码成功");
            }else {
                return ServerResponse.createByErrorCodeMessage(ServerResponse.ResponseCode.NEED_LOGIN.getCode(), ServerResponse.ResponseCode.NEED_LOGIN.getDesc());
            }
        }else {
            return ServerResponse.createByErrorCodeMessage(ServerResponse.ResponseCode.NEED_LOGIN.getCode(),ServerResponse.ResponseCode.NEED_LOGIN.getDesc());
        }
    }

    @Override
    public ServerResponse<Client> updateInformation(Client client) {
        client.setPassword(null);
        clientMapper.updateByPrimaryKeySelective(client);
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse<Client> getInformation(Integer id) {
        return ServerResponse.createBySuccess(clientMapper.selectByPrimaryKey(id));
    }

    @Override
    public ServerResponse checkAdminRole(Client client) {
        ClientExample example = new ClientExample();
        ClientExample.Criteria criteria = example.createCriteria();
        criteria.andRightsEqualTo(Const.Role.ROLE_ADMIN);
        List<Client> list = clientMapper.selectByExample(example);
        return list.size()==0? ServerResponse.createByErrorCodeMessage(ServerResponse.ResponseCode.ILLEGAL_ARGUMENT.getCode(),"角色不是管理员"):ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse getInfoByUsername(String username) {
        ClientExample example = new ClientExample();
        ClientExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<Client> list = clientMapper.selectByExample(example);
        return list.size()==0? ServerResponse.createByErrorCodeMessage(ServerResponse.ResponseCode.ILLEGAL_ARGUMENT.getCode(),ServerResponse.ResponseCode.ILLEGAL_ARGUMENT.getDesc()):ServerResponse.createBySuccess();
    }
}
