package com.design.sm.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.dao.SaleMasterDAO;
import com.design.sm.dao.SoldNoteDAO;
import com.design.sm.dao.impl.SaleMasterDAOImpl;
import com.design.sm.dao.impl.SoldNoteDAOImpl;
import com.design.sm.model.SaleMaster;
import com.design.sm.model.SoldNote;
import com.design.sm.service.SoldNoteService;

public class SoldNoteServiceImpl implements SoldNoteService{

	private SoldNoteDAO soldNoteDAO = new SoldNoteDAOImpl();
	private SaleMasterDAO saleMasterDAO = new SaleMasterDAOImpl();
	
	@Override
	public void addSoldNote(SoldNote sn) throws SQLException {
		soldNoteDAO.addSoldNote(sn);
	}

	@Override
	public List<SoldNote> findAllSoldNote() throws SQLException {
		return soldNoteDAO.findAllSoldNote();
	}

	@Override
	public Vector<Vector> pack(List<SoldNote> list) throws SQLException {
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (SoldNote obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 6; i++) {
					/**
					 * 显示 contact_id、contact_name、phone、email、 vendor_id
					 */
					temp.add(obj.getOrder_num());// 订单编号
					SaleMaster sm = saleMasterDAO.getSaleMasterByOrderNum(obj.getOrder_num());
					temp.add(sm.getHandle_time());//订单处理时间
					temp.add(obj.getActual_amount());// 订单实际金额
					temp.add(obj.getPayment());// 支付方式
					temp.add(this.getPaymentString(obj.getPayment()));// 支付方式
				}
				rows.add(temp);
			}
		}
		return rows;
	}
	/**
	 * 根据支付方式编号获取相应的内容
	 */
	public String getPaymentString(int payment){
		String str = "";
		if(payment==1){
			str = "现金支付";
		}else if(payment==2){
			str = "vip余额支付";
		}else if(payment==3){
			str = "第三方支付";
		}else if(payment==4){
			str = "信用卡支付";
		}
		return str;
	}

	@Override
	public SoldNote getSoldNoteByNum(String orderNum) throws SQLException {
		return soldNoteDAO.getSoldNoteByNum(orderNum);
	}
}
