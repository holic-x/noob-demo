package com.guigu.library.service.impl;

import java.sql.SQLException;

import com.guigu.library.dao.LibraryCardDAO;
import com.guigu.library.dao.impl.LibraryCardDAOImpl;
import com.guigu.library.model.LibraryCard;
import com.guigu.library.service.LibraryCardService;

public class LibraryCardServiceImpl implements LibraryCardService {

	private LibraryCardDAO libraryCardDAO = new LibraryCardDAOImpl();
	
	@Override
	public void addLibraryCard(LibraryCard lc) throws SQLException {
		libraryCardDAO.addLibraryCard(lc);
	}

	@Override
	public void updateLibraryCard(LibraryCard lc) throws SQLException {
		libraryCardDAO.updateLibraryCard(lc);
	}

	@Override
	public void deleteLibraryCard(String card_id) throws SQLException {
		libraryCardDAO.deleteLibraryCard(card_id);
	}

	@Override
	public LibraryCard getLibraryCardById(String card_id) throws SQLException {
		return libraryCardDAO.getLibraryCardById(card_id);
	}

	@Override
	public LibraryCard getLibraryCardByNum(String card_num) throws SQLException {
		return libraryCardDAO.getLibraryCardByNum(card_num);
	}

	@Override
	public String getLibraryCardNumById(String card_id) throws SQLException {
		return libraryCardDAO.getLibraryCardNumById(card_id)+"";
	}

	@Override
	public String getLibraryCardSeq() throws SQLException {
		return libraryCardDAO.getLibraryCardSeq()+"";
	}
	
}
