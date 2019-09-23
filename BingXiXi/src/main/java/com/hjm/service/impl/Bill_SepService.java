package com.hjm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjm.common.ServerResponse;
import com.hjm.dao.BillMapper;
import com.hjm.dao.Bill_SepMapper;
import com.hjm.dao.ClientMapper;
import com.hjm.pojo.*;
import com.hjm.service.IBill_SepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hjm
 */
@Service
public class Bill_SepService implements IBill_SepService {
    @Autowired
    BillMapper billMapper;
    @Autowired
    Bill_SepMapper bill_sepMapper;
    @Autowired
    ClientMapper clientMapper;
    @Override
    public ServerResponse joinBill(Integer billId, Integer clientId) {
        if (isRepeat(billId,clientId)){
            return ServerResponse.createByErrorCodeMessage(ServerResponse.ResponseCode.ILLEGAL_ARGUMENT.getCode(),ServerResponse.ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Bill_Sep bill_sep = new Bill_Sep();
        bill_sep.setBillid(billId);
        bill_sep.setClientid(clientId);
        bill_sep.setUpdatetime(new Date());

        Bill bill = billMapper.selectByPrimaryKey(billId);
        bill.setUpdatetime(new Date());
        billMapper.updateByPrimaryKey(bill);

        if (bill==null){
            return ServerResponse.createByErrorCodeMessage(ServerResponse.ResponseCode.ILLEGAL_ARGUMENT.getCode(),ServerResponse.ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        bill_sepMapper.insert(bill_sep);
        return ServerResponse.createBySuccess();
    }

    private boolean isRepeat(Integer billId, Integer clientId){
        Bill_SepExample example = new Bill_SepExample();
        Bill_SepExample.Criteria criteria = example.createCriteria();
        criteria.andBillidEqualTo(billId);
        criteria.andClientidEqualTo(clientId);
        List<Bill_Sep> list = bill_sepMapper.selectByExample(example);
        return list.size()>0?true:false;
    }

    @Override
    public ServerResponse quitBill(Integer billId, Integer clientId) {
        if (isRepeat(billId,clientId)){
            Bill_SepExample example = new Bill_SepExample();
            Bill_SepExample.Criteria criteria = example.createCriteria();
            criteria.andBillidEqualTo(billId);
            criteria.andClientidEqualTo(clientId);
            bill_sepMapper.deleteByExample(example);
            Bill bill = billMapper.selectByPrimaryKey(billId);
            bill.setUpdatetime(new Date());
            billMapper.updateByPrimaryKey(bill);
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByErrorCodeMessage(ServerResponse.ResponseCode.ILLEGAL_ARGUMENT.getCode(),ServerResponse.ResponseCode.ILLEGAL_ARGUMENT.getDesc());
    }

    @Override
    public ServerResponse<PageInfo> listAllBillByClientID(Integer clientId, Integer pageNum, Integer pageSize) {
        pageNum = pageNum==null?1:pageNum;
        pageSize = pageSize==null?0:pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<Bill> list = billMapper.selectAllBillByClientId(clientId);
        PageInfo pageInfo = new PageInfo(list);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<PageInfo> listActiveBillByClientID(Integer clientId, Integer pageNum, Integer pageSize) {
        pageNum = pageNum==null?1:pageNum;
        pageSize = pageSize==null?0:pageSize;
        PageHelper.startPage(pageNum,pageSize);
        List<Bill> list = billMapper.selectActiveBillByClientId(clientId);
        PageInfo pageInfo = new PageInfo(list);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<List<Client>> listBillClient(Integer billId) {
        Bill_SepExample example = new Bill_SepExample();
        Bill_SepExample.Criteria criteria = example.createCriteria();
        criteria.andBillidEqualTo(billId);
        List<Bill_Sep> list = bill_sepMapper.selectByExample(example);
        List<Client> resultList = new ArrayList<>();
        for (Bill_Sep bill_sep : list) {
            resultList.add(clientMapper.selectByPrimaryKey(bill_sep.getClientid()));
        }
        return ServerResponse.createBySuccess(resultList);
    }

    @Override
    public Integer checkLeader(Integer billId, Integer clientId) {
        Bill bill = billMapper.selectByPrimaryKey(billId);
        return bill.getLeaderid().compareTo(clientId);
    }
}
