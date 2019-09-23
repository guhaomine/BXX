package com.hjm.service;

import com.github.pagehelper.PageInfo;
import com.hjm.common.ServerResponse;
import com.hjm.pojo.Client;

import java.util.List;

/**
 * @author hjm
 */
public interface IBill_SepService {
    ServerResponse joinBill(Integer billId, Integer clientId);
    ServerResponse quitBill(Integer billId, Integer clientId);
    ServerResponse<PageInfo> listAllBillByClientID(Integer clientId, Integer pageNum, Integer pageSize);
    ServerResponse<PageInfo> listActiveBillByClientID(Integer clientId,Integer pageNum,Integer pageSize);
    ServerResponse<List<Client>> listBillClient(Integer billId);
    Integer checkLeader(Integer billId,Integer clientId);
}
