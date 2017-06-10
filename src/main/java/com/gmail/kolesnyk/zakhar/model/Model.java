package com.gmail.kolesnyk.zakhar.model;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Model {
    private String nameOutput;
    private List<String> list13Digits;


    public String parseFileFor13Digits(File file) {
        if (file == null) {
            return null;
        }
        if (!FilenameUtils.getExtension(file.getName()).toLowerCase().equals("txt")) {
            return "invalid type of file";
        }
        list13Digits = new ArrayList<String>();
        try {
            java.util.List<String> str = FileUtils.readLines(file);
            Pattern pattern = Pattern.compile("[0-9]{13}");
            for (String s : str) {
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()) {
                    list13Digits.add(matcher.group().trim());
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return file.getAbsolutePath();
    }

    public String writeXLSFile() throws IOException {
        if (list13Digits == null || nameOutput == null) {
            return "Files not loaded";
        }
        FileInputStream inputStream = new FileInputStream(nameOutput);
        HSSFWorkbook book = new HSSFWorkbook(inputStream);
        inputStream.close();
        HSSFSheet myExcelSheet = book.getSheetAt(0);
        int pointer = myExcelSheet.getLastRowNum();
        for (String s : list13Digits) {
            System.out.println(s);
            myExcelSheet.createRow(++pointer).createCell(0).setCellValue(s);
        }
        FileOutputStream outputStream = new FileOutputStream(nameOutput);
        book.write(outputStream);
        outputStream.close();
        return null;
    }

    public String setNameOutput(File file) {
        if (!FilenameUtils.getExtension(file.getName()).toLowerCase().equals("xls")) {
            return "invalid type of file";
        }
        return this.nameOutput = file.getAbsolutePath();
    }
}
