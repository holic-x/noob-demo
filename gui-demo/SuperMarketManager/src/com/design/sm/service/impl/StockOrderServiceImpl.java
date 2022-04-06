package com.design.sm.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.dao.ProductDAO;
import com.design.sm.dao.StockMasterDAO;
import com.design.sm.dao.StockOrderDAO;
import com.design.sm.dao.VendorDAO;
import com.design.sm.dao.impl.ProductDAOImpl;
import com.design.sm.dao.impl.StockMasterDAOImpl;
import com.design.sm.dao.impl.StockOrderDAOImpl;
import com.design.sm.dao.impl.VendorDAOImpl;
import com.design.sm.model.Product;
import com.design.sm.model.StockOrder;
import com.design.sm.model.Temp;
import com.design.sm.service.StockOrderService;

public class StockOrderServiceImpl implements StockOrderService{
	private StockOrderDAO stockOrderDAO = new StockOrderDAOImpl();
	private StockMasterDAO stockMasterDAO = new StockMasterDAOImpl();
	private ProductDAO productDAO = new ProductDAOImpl();
	private VendorDAO vendorDAO = new VendorDAOImpl();

	@Override
	public void addStockOrder(StockOrder t) throws SQLException {
		stockOrderDAO.addStockOrder(t);
	}

	@Override
	public List<StockOrder> getStockOrderBySMId(String smId)
			throws SQLException {
		return stockOrderDAO.getStockOrderBySMId(smId);
	}
	
	@Override
	public Vector<Vector> pack(List<StockOrder> list) throws SQLException {
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (StockOrder obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 9; i++) {
					temp.add(obj.getStock_master_id());//订单id
					temp.add(stockMasterDAO.getSMOrderNumById(obj.getStock_master_id()));//订单编号
					temp.add(obj.getProduct_id());//产品id
					temp.add(productDAO.getProductName(obj.getProduct_id()));//产品名称
					temp.add(obj.getQuantity());//操作数量
					temp.add(obj.getUnit_price());//单价
					temp.add(obj.getAmount());//总额
					Product prod = productDAO.getProduct(obj.getProduct_id());
					temp.add(prod.getVendor_id());//供应商id
					temp.add(vendorDAO.getVendorName(prod.getVendor_id()));//供应商名称
//					temp.add(obj.getState());//删除标识（此处可以暂时不考虑）
				}
				rows.add(temp);
			}
		}
		return rows;
	}
}
