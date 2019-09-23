package com.hjm.portal;

import com.github.pagehelper.PageInfo;
import com.hjm.common.ServerResponse;
import com.hjm.pojo.Bill;
import com.hjm.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hjm
 */
@RestController
@RequestMapping("/portal/bill")
public class BillController {
    @Autowired
    IBillService iBillService;
    @GetMapping("/detail")
    public ServerResponse<Bill> detail(Integer id){
        try {
            return iBillService.getBillDetail(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }

    @GetMapping("/listByShop")
    public ServerResponse<PageInfo> listByShop(String shop, Integer page, Integer rows){
        try {
            return iBillService.getBillByKeywordsShop(shop,page,rows);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }
    @GetMapping("/getActiveList")
    public ServerResponse<PageInfo> getActiveList(Integer page, Integer rows){
        try {
            return iBillService.getActiveBillList(page,rows);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }
    @GetMapping("/searchBill")
    ServerResponse<PageInfo> searchBill(String keywords,Integer id,Integer page,Integer rows){
        try {
            return iBillService.searchBill(keywords,id,page,rows);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }
}
