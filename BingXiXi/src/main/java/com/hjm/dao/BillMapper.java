package com.hjm.dao;

import com.hjm.pojo.Bill;
import com.hjm.pojo.BillExample;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbggenerated Mon Sep 02 19:12:28 CST 2019
     */
    int countByExample(BillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbggenerated Mon Sep 02 19:12:28 CST 2019
     */
    int deleteByExample(BillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbggenerated Mon Sep 02 19:12:28 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbggenerated Mon Sep 02 19:12:28 CST 2019
     */
    int insert(Bill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbggenerated Mon Sep 02 19:12:28 CST 2019
     */
    int insertSelective(Bill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbggenerated Mon Sep 02 19:12:28 CST 2019
     */
    List<Bill> selectByExample(BillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbggenerated Mon Sep 02 19:12:28 CST 2019
     */
    Bill selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbggenerated Mon Sep 02 19:12:28 CST 2019
     */
    int updateByExampleSelective(@Param("record") Bill record, @Param("example") BillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbggenerated Mon Sep 02 19:12:28 CST 2019
     */
    int updateByExample(@Param("record") Bill record, @Param("example") BillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbggenerated Mon Sep 02 19:12:28 CST 2019
     */
    int updateByPrimaryKeySelective(Bill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbggenerated Mon Sep 02 19:12:28 CST 2019
     */
    int updateByPrimaryKey(Bill record);

    List<Bill> selectActiveBillByClientId(Integer clientId);

    List<Bill> selectAllBillByClientId(Integer clientId);
}