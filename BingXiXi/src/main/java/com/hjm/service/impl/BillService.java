package com.hjm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjm.common.ServerResponse;
import com.hjm.dao.BillMapper;
import com.hjm.dao.Bill_SepMapper;
import com.hjm.pojo.Bill;
import com.hjm.pojo.BillExample;
import com.hjm.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author hjm
 */
@Service
public class BillService implements IBillService {
    @Autowired
    BillMapper billMapper;
    @Autowired
    Bill_SepMapper bill_sepMapper;
    @Override
    public ServerResponse<Integer> saveOrUpdateBill(Bill bill) {
        Date date = new Date();
        bill.setStarttime(date);
        bill.setUpdatetime(date);
        bill.setStatus(1);
        Integer id = billMapper.insert(bill);
        return ServerResponse.createBySuccess(bill.getId());
    }

    @Override
    public ServerResponse<String> setBillStates(Integer id, Integer status) {
        billMapper.selectByPrimaryKey(id).setStatus(status);
        return ServerResponse.createBySuccess("拼单状态改变");
    }

    @Override
    public ServerResponse<Bill> manageBillDetail(Integer id) {
        return null;
    }

    @Override
    public ServerResponse<PageInfo> getActiveBillList(Integer page, Integer rows) {
        page = page==null?0:page;
        rows = rows==null?0:rows;
        PageHelper.startPage(page,rows);
        BillExample example = new BillExample();
        BillExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        List<Bill> list = billMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(list);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<PageInfo> searchBill(String keywords, Integer id, Integer page, Integer rows) {
        page = page==null?0:page;
        rows = rows==null?0:rows;
        PageHelper.startPage(page,rows);
        BillExample example = new BillExample();
        BillExample.Criteria criteria = example.createCriteria();
        criteria.andProductLike(keywords);
        List<Bill> list = billMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(list);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<Bill> getBillDetail(Integer id) {
        Bill bill = billMapper.selectByPrimaryKey(id);
        return ServerResponse.createBySuccess(bill);
    }

    @Override
    public ServerResponse<PageInfo> getBillByKeywordsShop(String keywords, Integer page, Integer rows) {
        page = page==null?0:page;
        rows = rows==null?0:rows;
        PageHelper.startPage(page,rows);
        BillExample example = new BillExample();
        BillExample.Criteria criteria = example.createCriteria();
        criteria.andShopEqualTo(keywords);
        List<Bill> list = billMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(list);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<String> shutdownBill(Integer billId, Integer clientId) {
        Bill bill = billMapper.selectByPrimaryKey(billId);
        bill.setUpdatetime(new Date());
        bill.setStatus(2);
        billMapper.updateByPrimaryKey(bill);
        return ServerResponse.createBySuccess();
    }
}
