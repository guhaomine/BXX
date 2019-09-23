package com.hjm.service;

import com.github.pagehelper.PageInfo;
import com.hjm.common.ServerResponse;
import com.hjm.pojo.Bill;

/**
 * @author hjm
 */
public interface IBillService {
    ServerResponse<Integer> saveOrUpdateBill(Bill bill);
    ServerResponse<String> setBillStates(Integer id,Integer status);
    ServerResponse<Bill> manageBillDetail(Integer id);
    ServerResponse<PageInfo> getActiveBillList(Integer page, Integer rows);
    ServerResponse<PageInfo> searchBill(String keywords,Integer id,Integer page,Integer rows);
    ServerResponse<Bill> getBillDetail(Integer id);
    ServerResponse<PageInfo> getBillByKeywordsShop(String keywords,Integer page,Integer rows);
    ServerResponse<String> shutdownBill(Integer billId,Integer clientId);
}
