package com.guigu.library.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.dao.LibraryCardDAO;
import com.guigu.library.dao.ReaderClassifyDAO;
import com.guigu.library.dao.ReaderDAO;
import com.guigu.library.dao.UsersDAO;
import com.guigu.library.dao.impl.LibraryCardDAOImpl;
import com.guigu.library.dao.impl.ReaderClassifyDAOImpl;
import com.guigu.library.dao.impl.ReaderDAOImpl;
import com.guigu.library.dao.impl.UsersDAOImpl;
import com.guigu.library.model.Reader;
import com.guigu.library.model.Users;
import com.guigu.library.service.ReaderService;

public class ReaderServiceImpl implements ReaderService {

	private ReaderDAO readerDAO = new ReaderDAOImpl();
	private UsersDAO usersDAO = new UsersDAOImpl();
	private ReaderClassifyDAO readerClassifyDAO = new ReaderClassifyDAOImpl();
	private LibraryCardDAO libraryCardDAO = new LibraryCardDAOImpl();

	@Override
	public void addReader(Reader r) throws SQLException {
		readerDAO.addReader(r);
	}

	@Override
	public void updateReader(Reader r) throws SQLException {
		readerDAO.updateReader(r);
	}

	@Override
	public void deleteReader(String id) throws SQLException {
		readerDAO.deleteReader(id);
	}

	@Override
	public Reader getReaderById(String id) throws SQLException {
		return readerDAO.getReaderById(id);
	}

	@Override
	public List<Reader> findReaderUnion(int choose, Object... args)
			throws SQLException {
		// 0.所有内容 1.条形码 2.学工编号 3.身份证号 4.读者账号 5.借阅证编号
		String keyword;
		String text;
		if (choose == 0) {
			// 查找所有的内容
			return readerDAO.findAllReader();
		} else if (choose == 1) {
			// 根据条形码关键字查找读者信息
			keyword = args[0] + "";
			text = "%" + keyword + "%";
			return readerDAO.findReaderByBarcode(text);
		} else if (choose == 2) {
			// 根据学工编号关键字查找者信息
			keyword = args[0] + "";
			text = "%" + keyword + "%";
			return readerDAO.findReaderByAcademicNum(text);
		} else if (choose == 3) {
			// 根据身份证号关键字查找读者信息
			keyword = args[0] + "";
			text = "%" + keyword + "%";
			return readerDAO.findReaderByIdCard(text);
		} else if (choose == 4) {
			// 根据读者账号名称关键字查找读者信息
			keyword = args[0] + "";
			text = "%" + keyword + "%";
			return readerDAO.findReaderByAccount(text);
		} else if (choose == 5) {
			// 根据读者借阅证编号查找读者信息
			keyword = args[0] + "";
			text = "%" + keyword + "%";
			return readerDAO.findReaderByCardId(text);
		}
		return null;
	}

	@Override
	public Vector<Vector> pack(List<Reader> list) throws SQLException {
		// 手动封装查找到的数据信息
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Reader obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 16; i++) {
					temp.add(obj.getReader_id());// 读者id
					temp.add(obj.getBarcode());// 条形码
					temp.add(obj.getAcademic_num());// 学工编号
					temp.add(obj.getReader_name());// 姓名
					temp.add(obj.getReader_sex());// 性别
					temp.add(obj.getReader_birthday());// 出生日期
					temp.add(obj.getReader_idCard());// 身份证号
					temp.add(obj.getReader_phone());// 联系方式
					temp.add(obj.getReader_email());// 电子邮箱
					temp.add(obj.getReader_descr());// 备注
					temp.add(obj.getUser_id());// 读者账号id
					temp.add(usersDAO.getUsersName(obj.getUser_id()));// 读者账号
					temp.add(obj.getClassify_num());// 读者分类编号
					temp.add(readerClassifyDAO.getClassifyNameByum(obj
							.getClassify_num()));// 所属分类
					temp.add(obj.getCard_id());// 借阅证id
					temp.add(libraryCardDAO.getLibraryCardNumById(obj.getCard_id()));// 借阅证
				}
				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public Reader getReaderByUserId(String user_id) throws SQLException {
		return readerDAO.getReaderByUserId(user_id);
	}

	@Override
	public Reader getReaderByBarcode(String barcode) throws SQLException {
		return readerDAO.getReaderByBarcode(barcode);
	}
}
