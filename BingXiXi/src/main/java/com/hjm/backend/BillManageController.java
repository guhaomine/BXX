package com.hjm.backend;

import com.github.pagehelper.PageInfo;
import com.hjm.common.Const;
import com.hjm.common.ServerResponse;
import com.hjm.common.TokenCache;
import com.hjm.pojo.Bill;
import com.hjm.pojo.Client;
import com.hjm.service.IBillService;
import com.hjm.service.IBill_SepService;
import com.hjm.service.IUserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author hjm
 */
@RestController
@RequestMapping("/backend/bill")
public class BillManageController {
    @Autowired
    IUserService iUserService;
    @Autowired
    IBillService iBillService;
    @Autowired
    IBill_SepService iBill_sepService;
    @PostMapping("/createBill")
    public ServerResponse createBill(HttpSession session, @RequestBody Bill bill){
        try {
            ServerResponse<Integer> response = iBillService.saveOrUpdateBill(bill);
            Integer billId = response.getData();
            iBill_sepService.joinBill(billId,((Client) session.getAttribute(Const.CURRENT_USER)).getId());
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }
    @PatchMapping("/joinBill")
    public ServerResponse joinBill(HttpSession session, Integer id){
        try {
            return iBill_sepService.joinBill(id, ((Client) session.getAttribute(Const.CURRENT_USER)).getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }
    @PatchMapping("/quitBill")
    public ServerResponse quitBill(HttpSession session, Integer id){
        try {
            return iBill_sepService.quitBill(id, ((Client) session.getAttribute(Const.CURRENT_USER)).getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }
    @PatchMapping("/shutdownBill")
    public ServerResponse shutdownBill(HttpSession session, Integer id){
        try {
            if (iUserService.checkAdminRole((Client) session.getAttribute(Const.CURRENT_USER)).isSuccess()){
                return iBillService.shutdownBill(id, ((Client) session.getAttribute(Const.CURRENT_USER)).getId());
            }else {
                return ServerResponse.createByErrorCodeMessage(ServerResponse.ResponseCode.ILLEGAL_ARGUMENT.getCode(),ServerResponse.ResponseCode.ILLEGAL_ARGUMENT.getDesc());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }
    @GetMapping("/listMyBill")
    public ServerResponse<PageInfo> listMyBill(HttpSession session, Integer page, Integer size){
        try {
            return iBill_sepService.listAllBillByClientID(((Client) session.getAttribute(Const.CURRENT_USER)).getId(),page,size);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }

    @GetMapping("/listMyActiveBill")
    public ServerResponse<PageInfo> listMyActiveBill(HttpSession session, Integer page, Integer size){
        try {
            return iBill_sepService.listActiveBillByClientID(((Client) session.getAttribute(Const.CURRENT_USER)).getId(),page,size);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }
    @GetMapping("/listBillClient")
    public ServerResponse<List<Client>> listBillClient(HttpSession session, Integer id){
        try {
            return iBill_sepService.listBillClient(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }
}
