package com.hjm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjm.common.Const;
import com.hjm.common.ServerResponse;
import com.hjm.dao.BillMapper;
import com.hjm.dao.Bill_SepMapper;
import com.hjm.dao.ClientMapper;
import com.hjm.pojo.Bill_SepExample;
import com.hjm.pojo.Client;
import com.hjm.pojo.ClientExample;
import com.hjm.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hjm
 */
@Service
public class AdminService implements IAdminService {
    @Autowired
    ClientMapper clientMapper;
    @Autowired
    BillMapper billMapper;
    @Autowired
    Bill_SepMapper bill_sepMapper;

    @Override
    public ServerResponse<PageInfo> listAllClient(Integer pageNum, Integer pageSize) {
        pageNum = pageNum==null?1:pageNum;
        pageSize = pageSize==null?0:pageSize;
        PageHelper.startPage(pageNum,pageSize);
        ClientExample example = new ClientExample();
        ClientExample.Criteria criteria = example.createCriteria();
        criteria.andRightsEqualTo(Const.Role.ROLE_CLIENT);
        List<Client> list = clientMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(list);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse deleteClient(Integer id) {
        try {
            clientMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse deleteBill(Integer id) {
        try {
            billMapper.deleteByPrimaryKey(id);
            Bill_SepExample example = new Bill_SepExample();
            Bill_SepExample.Criteria criteria = example.createCriteria();
            criteria.andBillidEqualTo(id);
            bill_sepMapper.deleteByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess();
    }
}
