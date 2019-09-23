package com.hjm.service;

import com.github.pagehelper.PageInfo;
import com.hjm.common.ServerResponse;

/**
 * @author hjm
 */
public interface IAdminService {
    ServerResponse<PageInfo> listAllClient(Integer page, Integer size);
    ServerResponse deleteClient(Integer id);
    ServerResponse deleteBill(Integer id);
}
