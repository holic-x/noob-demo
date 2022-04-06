package com.design.sm.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.dao.UnitsDAO;
import com.design.sm.dao.impl.UnitsDAOImpl;
import com.design.sm.model.Category;
import com.design.sm.model.Units;
import com.design.sm.service.UnitsService;

public class UnitsServiceImpl implements UnitsService {
	private UnitsDAO unitsDAO = new UnitsDAOImpl();

	// 根据单位id获取单位名称
	@Override
	public Object getUnitsName(String id) throws SQLException {
		return unitsDAO.getUnitsName(id);
	}

	// 查找所有的单位信息列表
	@Override
	public List<Units> findAllUnitsList() throws SQLException {
		return unitsDAO.findAllUnits();
	}

	// 查找所有的单位信息记录
	@Override
	public Vector<Vector> findAllUnitsVector() throws SQLException {
		return this.pack(unitsDAO.findAllUnits());
	}

	// 添加单位信息
	@Override
	public void addUnits(Units u) throws SQLException {
		unitsDAO.addUnits(u);
	}

	// 修改单位信息
	@Override
	public void updateUnits(Units u) throws SQLException {
		unitsDAO.updateUnits(u);
	}

	// 删除单位信息
	@Override
	public void deleteUnits(String id) throws SQLException {
		unitsDAO.deleteUnits(id);
	}

	// 通过单位名称关键字查找信息
	@Override
	public Vector<Vector> getUnitsByNameKeyword(String keyword)
			throws SQLException {
		return this.pack(unitsDAO.getUnitsByNameKeyword(keyword));
	}

	// 将List<Units>封装为Vector<Vector>类型
	public Vector<Vector> pack(List<Units> list) {
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Units obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 3; i++) {
					temp.add(obj.getUnits_id());// 单位id
					temp.add(obj.getUnits_name());// 单位名称
					temp.add(obj.getDelete_flag());// 删除标识
				}
				rows.add(temp);
			}
		}
		return rows;
	}
}
