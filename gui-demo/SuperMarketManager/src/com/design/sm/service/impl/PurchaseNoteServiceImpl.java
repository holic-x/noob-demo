package com.design.sm.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.dao.PurchaseNoteDAO;
import com.design.sm.dao.StockMasterDAO;
import com.design.sm.dao.impl.PurchaseNoteDAOImpl;
import com.design.sm.dao.impl.StockMasterDAOImpl;
import com.design.sm.model.PurchaseNote;
import com.design.sm.model.StockMaster;
import com.design.sm.service.PurchaseNoteService;

public class PurchaseNoteServiceImpl implements PurchaseNoteService{

	private PurchaseNoteDAO soldNoteDAO = new PurchaseNoteDAOImpl();
	private StockMasterDAO stockMasterDAO = new StockMasterDAOImpl();
	
	@Override
	public void addPurchaseNote(PurchaseNote pn) throws SQLException {
		soldNoteDAO.addPurchaseNote(pn);
	}

	@Override
	public List<PurchaseNote> findAllPurchaseNote() throws SQLException {
		return soldNoteDAO.findAllPurchaseNote();
	}

	@Override
	public Vector<Vector> pack(List<PurchaseNote> list) throws SQLException {
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (PurchaseNote obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 5; i++) {
					temp.add(obj.getOrder_num());// 订单编号
					StockMaster sm = stockMasterDAO.getStockMasterByOrderNum(obj.getOrder_num());
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
			str = "转账支付";
		}
		return str;
	}

	@Override
	public PurchaseNote getPurchaseNoteByNum(String orderNum) throws SQLException {
		return soldNoteDAO.getPurchaseNoteByNum(orderNum);
	}
}
