package com.design.sm.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.design.sm.dao.ProductDAO;
import com.design.sm.dao.SaleTempDAO;
import com.design.sm.dao.VendorDAO;
import com.design.sm.dao.impl.ProductDAOImpl;
import com.design.sm.dao.impl.SaleTempDAOImpl;
import com.design.sm.dao.impl.VendorDAOImpl;
import com.design.sm.model.Product;
import com.design.sm.model.SaleTemp;
import com.design.sm.service.SaleTempService;

public class SaleTempServiceImpl implements SaleTempService{

	private SaleTempDAO tempDAO = new SaleTempDAOImpl();
	private ProductDAO productDAO = new ProductDAOImpl();
	private VendorDAO vendorDAO = new VendorDAOImpl();
	@Override
	public void addSaleTemp(SaleTemp t) throws SQLException {
		tempDAO.addSaleTemp(t);
	}

	@Override
	public void update(SaleTemp t) throws SQLException {
		tempDAO.update(t);
	}

	@Override
	public void deleteSaleTemp(String id) throws SQLException {
		tempDAO.deleteSaleTemp(id);
	}

	@Override
	public List<SaleTemp> findAllSaleTempList() throws SQLException {
		return tempDAO.findAllSaleTempList();
	}

	@Override
	public Vector<Vector> pack(List<SaleTemp> list) throws SQLException {
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (SaleTemp obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 8; i++) {
					temp.add(obj.getProduct_id());//产品id
					temp.add(productDAO.getProductName(obj.getProduct_id()));//产品名称
					temp.add(obj.getQuantity());//操作数量
					temp.add(obj.getUnit_price());//单价
					temp.add(obj.getAmount());//总额
					Product prod = productDAO.getProduct(obj.getProduct_id());
					temp.add(prod.getVendor_id());//供应商id
					temp.add(vendorDAO.getVendorName(prod.getVendor_id()));//供应商名称
				}
				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public List<SaleTemp> getSaleTempListByProductId(String[] ids) throws SQLException {
		List<SaleTemp> list = new ArrayList<SaleTemp>();
		// 依次遍历
		for(String id : ids){
			SaleTemp temp = tempDAO.getSaleTempByProductId(id);
			list.add(temp);
		}
		return list;
	}

	@Override
	public SaleTemp getSaleTempByProductId(String id) throws SQLException {
		return tempDAO.getSaleTempByProductId(id);
	}

	@Override
	public double getAllAmount() throws SQLException {
		return Double.valueOf(tempDAO.getAllAmount().toString());
	}

	@Override
	public void truncateAllSaleTemp() throws SQLException {
		tempDAO.truncateAllSaleTemp();
	}
}
