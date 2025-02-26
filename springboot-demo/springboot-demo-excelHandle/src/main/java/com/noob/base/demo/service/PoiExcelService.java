package com.noob.base.demo.service;

import com.noob.base.demo.model.PoiUser;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 基于Apache POI 实现excel的处理
 */
@Service
public class PoiExcelService {

    /**
     * 读取excel文件，转化为数据列表
     */
    public List<PoiUser> readExcelFile(String filePath) {
        List<PoiUser> users = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(filePath)) {
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0) {
                    continue; // Skip header row
                }

                PoiUser user = new PoiUser();
                user.setName(row.getCell(0).getStringCellValue());
                user.setAge((int) row.getCell(1).getNumericCellValue());
                user.setEmail(row.getCell(2).getStringCellValue());

                users.add(user);
            }

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }


    /**
     * 将数据列表转化为excel数据并导出到指定目录
     */
    public void writeExcelFile(List<PoiUser> users, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Users");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Name");
            headerRow.createCell(1).setCellValue("Age");
            headerRow.createCell(2).setCellValue("Email");

            // Create data rows
            int rowNum = 1;
            for (PoiUser user : users) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(user.getName());
                row.createCell(1).setCellValue(user.getAge());
                row.createCell(2).setCellValue(user.getEmail());
            }

            // Write the output to a file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}