package com.geostar.zrzy.sydzzw.utils;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class ExportExcelUtil {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void exportExcel(String[] headers, String[] fields, Collection<?> datas, HttpServletResponse response, String fileName) {
        HSSFWorkbook wb = exportExcel(headers, fields, "", datas);
        response.setContentType("application/octet-stream;charset=utf-8");
        if (StringUtils.isNotEmpty(fileName)) {
            fileName = fileName + ".xlsx";
        }else{
            fileName = getFileName();
        }
        OutputStream os = null;
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1"));
            os = response.getOutputStream();
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @param headers
     * @param fields
     * @param sheetName
     * @param datas
     * @return
     */
    private static HSSFWorkbook exportExcel(String[] headers, String[] fields, String sheetName, Collection<?> datas) {
        HSSFWorkbook wb = new HSSFWorkbook();
        if (ArrayUtils.isEmpty(headers)) return wb; //表头为空侧返回
        if (ArrayUtils.isEmpty(fields)) return wb;    //两个数组长度不同侧返回
        HSSFRow row = null;
        short rowNum = 0;
        //设置工作簿的名称
        String sheetTitle = StringUtils.isEmpty(sheetName) ? "Sheet1" : sheetName;
        HSSFSheet sheet = wb.createSheet();
        wb.setSheetName(0, sheetTitle);
        for(int i=0;i<headers.length;i++){
            sheet.setColumnWidth((short) i, (short) 256 * 30);// 设置列宽
        }
        //设置标题
        row = sheet.createRow(rowNum);
        setTitle(row, headers, wb);
        //设置样式
        HSSFCellStyle style = wb.createCellStyle();
        // 设置这些样式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  //设置单元格字符居中

        //生成一个字体
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setFontName("宋体");
        // 把字体应用到当前的样式
        style.setFont(font);
        for (Iterator<?> iter = datas.iterator(); iter.hasNext(); ) {
            Object exportEle = iter.next();
            // 行对象
            row = sheet.createRow(++rowNum);
            //设置对应值
            setRow(row, fields, exportEle,style);
        }
        return wb;
    }

    private static void setTitle(HSSFRow row, String[] headers, HSSFWorkbook wb) {
        HSSFCellStyle style = wb.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); //设置单元格背景颜色
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  //设置单元格字符居中

        //生成一个字体
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setFontName("宋体");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        for (int k = 0; k < headers.length; k++) {
            HSSFCell cell = row.createCell((short) k);
            cell.setCellStyle(style);
            cell.setCellValue(headers[k]);
        }
    }

    private static void setRow(HSSFRow row, String[] fields, Object exportModel, HSSFCellStyle style) {
        Object temp = null;
        for (int k = 0; k < fields.length; k++) {
            // Cell对象
            HSSFCell cell = row.createCell((short) k);
            cell.setCellStyle(style);
            //设置对应值
            try {
                //检查该实体是否有这个属性
                temp = PropertyUtils.getProperty(exportModel, fields[k]);
            } catch (Exception e) {
                e.getStackTrace();
                continue;
            }
            if (temp == null) {
                cell.setCellValue(StringUtils.EMPTY);
            } else {
                if (temp instanceof Date) {
                    cell.setCellValue(SDF.format(Date.class.cast(temp)));
                } else {
                    cell.setCellValue(temp.toString());
                }
            }
        }
    }

    private static String getFileName(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date()) + ".xlsx";
    }

}
