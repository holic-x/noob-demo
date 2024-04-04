package com.design.sm.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.dao.WarehouseDAO;
import com.design.sm.dao.impl.WarehouseDAOImpl;
import com.design.sm.model.Warehouse;
import com.design.sm.service.WarehouseService;

public class WarehouseServiceImpl implements WarehouseService{

	private WarehouseDAO dao = new WarehouseDAOImpl();
	@Override
	public Object getWarehouseName(String id) throws SQLException {
		return dao.getWarehouseName(id);
	}
	@Override
	public List<Warehouse> findAllWarehouseList() throws SQLException {
		return dao.findAllWarehouse();
	}
	@Override
	public Vector<Vector> findAllWarehouseVector() throws SQLException {
		List<Warehouse> list = dao.findAllWarehouse();
		return this.pack(list);
	}
	@Override
	public void addWarehouse(Warehouse w) throws SQLException {
		dao.addWarehouse(w);
	}
	@Override
	public void updateWarehouse(Warehouse w) throws SQLException {
		dao.updateWarehouse(w);
	}
	@Override
	public void deleteWarehouse(String id) throws SQLException {
		dao.deleteWarehouse(id);
	}
	@Override
	public Vector<Vector> getWarehouseByNameKeyword(String keyword)
			throws SQLException {
		List<Warehouse> list = dao.getWarehouseByNameKeyword(keyword);
		return this.pack(list);
	}
	
	public Vector<Vector> pack(List<Warehouse> list){
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Warehouse obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 3; i++) {
					temp.add(obj.getWarehouse_id());//²Ö¿âid
					temp.add(obj.getWarehouse_name());//²Ö¿âÃû³Æ
					temp.add(obj.getDelete_flag());//É¾³ý±êÊ¶
				}
				rows.add(temp);
			}
		}
		return rows;
	}
}
