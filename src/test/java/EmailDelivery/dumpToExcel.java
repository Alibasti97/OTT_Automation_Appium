package EmailDelivery;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dumpToExcel {

    public static void writeToExcel(double loadTimeSeconds, LocalDateTime currentDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String currentDateStr = currentDateTime.format(formatter);

        String excelFileName = "LoadTime-Results-" + currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ".xlsx";
        String excelFilePath = excelFileName;

        try (Workbook workbook = getOrCreateWorkbook(excelFilePath)) {
            Sheet sheet = workbook.getSheet("Test Results");
            if (sheet == null) {
                sheet = workbook.createSheet("Test Results");
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("Current Date and Time");
                headerRow.createCell(1).setCellValue("Date");
                headerRow.createCell(2).setCellValue("Time");
                headerRow.createCell(3).setCellValue("Iteration No");
                headerRow.createCell(4).setCellValue("Load Time");

                // Apply table formatting to the header row
                CellStyle headerStyle = workbook.createCellStyle();
                headerStyle.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
                headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerStyle.setFont(headerFont);

                for (Cell cell : headerRow) {
                    cell.setCellStyle(headerStyle);
                }
            }

            int rowCount = sheet.getLastRowNum() + 1;
            Row row = sheet.createRow(rowCount);

            // Set Current Date and Time
            row.createCell(0).setCellValue(currentDateStr);

            // Split current date and time to date and time separately
            String[] dateTimeParts = currentDateStr.split(" ");
            row.createCell(1).setCellValue(dateTimeParts[0]); // Date
            row.createCell(2).setCellValue(dateTimeParts[1]); // Time

            // Incrementing the iteration number based on the number of rows
            int iterationNo = rowCount > 1 ? rowCount : 1;
            row.createCell(3).setCellValue(iterationNo);

            // Set Load Time
            Cell loadTimeCell = row.createCell(4);
            loadTimeCell.setCellValue(loadTimeSeconds);

            // Apply conditional formatting for load time
            CellStyle orangeStyle = workbook.createCellStyle();
            orangeStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
            orangeStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            CellStyle redStyle = workbook.createCellStyle();
            redStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            redStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            if (loadTimeSeconds > 15) {
                loadTimeCell.setCellStyle(redStyle);
            } else if (loadTimeSeconds > 11) {
                loadTimeCell.setCellStyle(orangeStyle);
            }

            // Write to file
            try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
                workbook.write(outputStream);
            }

        } catch (IOException | EncryptedDocumentException e) {
            e.printStackTrace();
        }
    }

    private static Workbook getOrCreateWorkbook(String excelFilePath) throws IOException {
        File file = new File(excelFilePath);
        if (file.exists()) {
            return WorkbookFactory.create(new FileInputStream(excelFilePath));
        } else {
            return new XSSFWorkbook();
        }
    }

//    public static void createChart(Sheet sheet, int rowCount) {
//        XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
//        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, rowCount, 15, rowCount+20);
//        XSSFChart chart = drawing.createChart(anchor);
//        chart.setTitleOverlay(false);
//        chart.setTitleText("Load Time Chart");
//
//        XDDFChartAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
//        bottomAxis.setTitle("Iteration No");
//
//        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
//        leftAxis.setTitle("Load Time");
//
//        XDDFNumericalDataSource<Double> xs = XDDFDataSourcesFactory.fromNumericCellRange((XSSFSheet) sheet, new CellRangeAddress(1, rowCount, 3, 3));
//        XDDFNumericalDataSource<Double> ys = XDDFDataSourcesFactory.fromNumericCellRange((XSSFSheet) sheet, new CellRangeAddress(1, rowCount, 4, 4));
//
//        XDDFLineChartData data = (XDDFLineChartData) chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);
//        XDDFLineChartData.Series series = (XDDFLineChartData.Series) data.addSeries(xs, ys);
//        series.setTitle("Load Time", null); // Title, cell reference for title
//        chart.plot(data);
//
//        // if you want to set the color of the line, you can do it like this:
//        // series.setSmooth(false); // set smooth to false so we can set FillProperties and StrokeProperties
//        // series.setMarkerStyle(MarkerStyle.NONE); // remove markers
//        // XDDFSolidFillProperties fill = new XDDFSolidFillProperties(XDDFColor.from(new byte[]{(byte) 255, 0, 0})); // set fill color
//        // XDDFStrokeProperties stroke = new XDDFStrokeProperties(); // set stroke properties
//        // stroke.setFillProperties(fill);
//        // series.setStrokeProperties(stroke);
//    }



}