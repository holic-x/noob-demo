package com.guigu.library.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.dao.BookClassifyDAO;
import com.guigu.library.dao.BooksDAO;
import com.guigu.library.dao.StorageAreaDAO;
import com.guigu.library.dao.impl.BookClassifyDAOImpl;
import com.guigu.library.dao.impl.BooksDAOImpl;
import com.guigu.library.dao.impl.StorageAreaDAOImpl;
import com.guigu.library.model.BookClassify;
import com.guigu.library.model.Books;
import com.guigu.library.model.StorageArea;
import com.guigu.library.service.BooksService;

public class BooksServiceImpl implements BooksService {

	private BooksDAO booksDAO = new BooksDAOImpl();
	private BookClassifyDAO bookClassifyDAO = new BookClassifyDAOImpl();
	private StorageAreaDAO storageAreaDAO = new StorageAreaDAOImpl();

	@Override
	public void addBooks(Books book) throws SQLException {
		booksDAO.addBooks(book);
	}

	@Override
	public void updateBooks(Books book) throws SQLException {
		booksDAO.updateBooks(book);
	}

	@Override
	public void deleteBooks(String id) throws SQLException {
		booksDAO.deleteBooks(id);
	}

	@Override
	public Books getBooksById(String id) throws SQLException {
		return booksDAO.getBooksById(id);
	}

	@Override
	public List<Books> findBooksUnion(int field, int match, Object... args)
			throws SQLException {
		/**
		 * 根据检索字段和匹配方式进行检索 1.如果检索字段为“所有内容”则认为是检索所有图书，后方的匹配方式默认失效
		 * 2.如果检索字段不为“所有内容”则根据检索字段结合匹配方式进行图书信息的检索 a.field=1：书名 b.field=2：isbn
		 * c.field=3：索书号 d.field=4：图书分类 e.field=5：作者 f.field=6：译者 g.field=7：出版社
		 * 对应匹配方式：0.前方一致 1.完全匹配 2.任意匹配
		 */
		// 检索字段为所有内容
		if (field == 0) {
			return booksDAO.findAllBooks();
		} else {
			String keyword = args[0] + "";
			String text = "";
			// 先判断选择的匹配方式
			if (match == 0) {
				text = keyword + "%";
			} else if (match == 1) {
				text = keyword;
			} else if (match == 2) {
				text = "%" + keyword + "%";
			}
			if (field == 1) {
				return booksDAO.findBooksByName(text);
			} else if (field == 2) {
				return booksDAO.findBooksByISBN(text);
			} else if (field == 3) {
				return booksDAO.findBooksByCallno(text);
			} else if (field == 4) {
				return booksDAO.findBooksByClassify(text);
			} else if (field == 5) {
				return booksDAO.findBooksByAuthor(text);
			} else if (field == 6) {
				return booksDAO.findBooksByTranslator(text);
			} else if (field == 7) {
				return booksDAO.findBooksByPress(text);
			}
		}
		return null;
	}

	@Override
	public Vector<Vector> pack(List<Books> list) throws SQLException {
		// 手动封装查找到的数据信息
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Books obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 24; i++) {
					// 根据主键id获取相应的分类、存储区域对象
					BookClassify bc = bookClassifyDAO.getBookClassifyByNum(obj.getClassify_num());
					StorageArea sa = storageAreaDAO.getStorageAreaByNum(obj.getArea_num());
					temp.add(obj.getBook_id()); // 图书id
					temp.add(obj.getBarcode()); // 条形码
					temp.add("ISBN "+obj.getIsbn());    // ISBN 国际统一标准书号
					temp.add(obj.getCallno());  // 索书号
					temp.add(obj.getBook_name());  // 书名 
					temp.add(obj.getClassify_num()); // 图书分类id
					temp.add(bc.getClassify_name());// 图书分类名称
					temp.add(obj.getArea_num()); // 存储区域编号
					temp.add(sa.getArea_name());// 存储区域名称
					temp.add(obj.getAuthor()); // 作者
					temp.add(obj.getTranslator()); // 译者
					temp.add(obj.getPublish_date());// 出版日期
					temp.add(obj.getPress()); // 出版社
					temp.add(obj.getPrice()); // 价格
					temp.add(obj.getFormat()); // 规格
					temp.add(obj.getEntry_date());// 录入日期
					temp.add(obj.getPut_on_date());// 上架日期
					temp.add(obj.getAbstract_descr());// 提要文摘附注
					temp.add(obj.getProposal_reader());// 使用对象附注
					temp.add(obj.getBorrow_flag());// 借阅标识
					temp.add(this.getBorrowState(obj.getBorrow_flag()));// 借阅状态
					temp.add(obj.getPut_on_flag());// 上架标识
					temp.add(this.getPutOnState(obj.getPut_on_flag()));// 上架状态
					temp.add(obj.getDelete_flag());// 删除标识
				}
				rows.add(temp);
				// 隐藏5 7 11 13 14 15 16 17 18 19 21 23
			}
		}
		return rows;
	}
	
	/**
	 * 根据借阅标识获取相应的借阅状态
	 */
	public String getBorrowState(int borrow_flag){
		String state = "";
		if(borrow_flag==0){
			state = "可借阅";
		}else if(borrow_flag==1){
			state = "已借出";
		}
		return state;
	}
	
	/**
	 * 根据上架标识获取相应的上架状态
	 */
	public String getPutOnState(int puton_flag){
		String state = "";
		if(puton_flag==0){
			state = "即将上架";
		}else if(puton_flag==1){
			state = "已上架";
		}
		return state;
	}

	@Override
	public String getBookSeq() throws SQLException {
		return booksDAO.getBookSeq()+"";
	}

	@Override
	public Books getBookByBarcode(String barcode) throws SQLException {
		return booksDAO.getBookByBarcode(barcode);
	}
}
