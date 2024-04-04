package com.guigu.library.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.dao.BookClassifyDAO;
import com.guigu.library.dao.impl.BookClassifyDAOImpl;
import com.guigu.library.model.BookClassify;
import com.guigu.library.service.BookClassifyService;

public class BookClassifyServiceImpl implements BookClassifyService {

	private BookClassifyDAO bookClassifyDAO = new BookClassifyDAOImpl();

	@Override
	public void addBookClassify(BookClassify bc) throws SQLException {
		bookClassifyDAO.addBookClassify(bc);
	}

	@Override
	public void updateBookClassify(BookClassify bc,String old_num) throws SQLException {
		bookClassifyDAO.updateBookClassify(bc,old_num);
	}

	@Override
	public void deleteBookClassify(String classify_num) throws SQLException {
		bookClassifyDAO.deleteBookClassify(classify_num);
	}

	@Override
	public List<BookClassify> findBookClassifyUnion(String type, Object... args)
			throws SQLException {
		if (type.equals("all")) {
			return bookClassifyDAO.findAllBookClassify();
		} else if (type.equals("level")) {
			return bookClassifyDAO.findBookClassifyByLevel(Integer
					.valueOf(args[0] + ""));
		} else if (type.equals("parent_classify_num")) {
			return bookClassifyDAO.findChildBookClassifyByParent(args[0] + "");
		} else if (type.equals("keyword")) {
			return bookClassifyDAO.findBookClassifyByKeyWord(args[0] + "");
		}
		return null;
	}

	@Override
	public Vector<Vector> pack(List<BookClassify> list) throws SQLException {
		// 手动封装查找到的数据信息
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (BookClassify obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 5; i++) {
					temp.add(obj.getClassify_num());// 类型编号
					temp.add(obj.getClassify_name());// 类型名称
					temp.add(obj.getCurrent_level());// 当前类型等级
					if(obj.getParent_num()==null){
						temp.add("无");// 父级类型编号
					}else{
						temp.add(obj.getParent_num());// 父级类型编号
					}
				}
				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public BookClassify getBookClassifyByNum(String classify_num)
			throws SQLException {
		return bookClassifyDAO.getBookClassifyByNum(classify_num);
	}

}
