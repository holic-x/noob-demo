package com.guigu.library.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.dao.ReaderClassifyDAO;
import com.guigu.library.dao.impl.ReaderClassifyDAOImpl;
import com.guigu.library.model.BookClassify;
import com.guigu.library.model.Books;
import com.guigu.library.model.ReaderClassify;
import com.guigu.library.model.StorageArea;
import com.guigu.library.service.ReaderClassifyService;

public class ReaderClassifyServiceImpl implements ReaderClassifyService{

	private ReaderClassifyDAO readerClassifyDAO = new ReaderClassifyDAOImpl();
	@Override
	public void updateReaderClassify(ReaderClassify rc) throws SQLException {
		readerClassifyDAO.updateReaderClassify(rc);
	}

	@Override
	public List<ReaderClassify> findAllReaderClassify() throws SQLException {
		return readerClassifyDAO.findAllReaderClassify();
	}

	@Override
	public Vector<Vector> pack(List<ReaderClassify> list) throws SQLException {
		// 手动封装查找到的数据信息
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (ReaderClassify obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 4; i++) {
					temp.add(obj.getClassify_num());// 分类编号
					temp.add(obj.getClassify_name());// 分类名称
					temp.add(obj.getMaximum());// 最大借阅数
					temp.add(obj.getTime_limit());// 借阅期限
				}
				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public String getClassifyNameByum(int num) throws SQLException {
		return readerClassifyDAO.getClassifyNameByum(num)+"";
	}

	@Override
	public ReaderClassify getReaderClassifyBynum(int num) throws SQLException {
		return readerClassifyDAO.getReaderClassifyBynum(num);
	}
}
