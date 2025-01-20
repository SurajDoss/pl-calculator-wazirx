package org.example;

import java.io.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        readerMain();
    }

    public static void readerMain() {
        String filePath = "/Users/suraj-13251/Downloads/t1.xlsx"; // Replace with your Excel file path

        List<Trade> profitAndLossList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            // Skip the header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            Long counter = 0L;
            // Process each row
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Trade pnl = parseRowToProfitAndLoss(row, counter);
                if (pnl != null) {
                    counter++;
                    profitAndLossList.add(pnl);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        calculator(profitAndLossList);
    }

    private static void calculator(List<Trade> profitAndLossList){
        // HashMap to store PLObject by market
        Map<String, ProfitLoss> marketToPLMap = new HashMap<>();
        for(Trade trade : profitAndLossList){
            if(marketToPLMap.get(trade.getMarket()) == null){
                insertNewProfitLoss(  marketToPLMap, trade);
            }else{
                 ProfitLoss profitLoss = marketToPLMap.get(trade.getMarket());
                updateNewProfitLoss( marketToPLMap, trade, profitLoss);
            }
        }

        writeInExcel(marketToPLMap);
    }
   // id | Market	| Buy in Total |	Quantity Bought	| Buy Charges in INR |	Buy TDS in Amount |	Buy TDS in INR
   // | Sell In Total	| Quantity Sold |	Sell Charges INR	| Sell TDS in Amount |	Sell TDS in INR |
   // P/L	| Total Charges |	Total TDS INR |	Total Charges and Tax	| Net P/L |	Quantity in Reserve |	Value INR	 | Status
    private static void insertNewProfitLoss( Map<String, ProfitLoss> marketToPLMap, Trade trade){
        ProfitLoss profitLoss = new ProfitLoss();
        //id
        profitLoss.setId(trade.getId());

        if("Buy".equals(trade.getTradeType())){
            //buy in total
            profitLoss.setBuyInTotal(trade.getTotal());
            //quantity bought
            profitLoss.setQuantityBought(trade.getVolume());
            //buy chargerges in INR
            profitLoss.setBuyChargesInINR(trade.getFeeAmount());
            //buy tds
            profitLoss.setBuyTdsInINR(trade.getTdsInINR());
        }else if("Sell".equals(trade.getTradeType())){
            //sell in total
            profitLoss.setSellInTotal(trade.getTotal());
            //quantity sold
            profitLoss.setQuantitySold(trade.getVolume());
            //sell charges
            profitLoss.setSellChargesInINR(trade.getFeeAmount());
            //sell chanrges in tds
            profitLoss.setSellTdsInINR(trade.getTdsInINR());
        }
        marketToPLMap.put(trade.getMarket(), profitLoss);
    }

    private static void updateNewProfitLoss( Map<String, ProfitLoss> marketToPLMap, Trade trade, ProfitLoss profitLoss){
        if("Buy".equals(trade.getTradeType())){
            //buy in total
            profitLoss.setBuyInTotal( profitLoss.getBuyInTotal() + trade.getTotal());
            //quantity bought
            profitLoss.setQuantityBought(profitLoss.getQuantityBought() + trade.getVolume());
            //buy chargerges in INR
            profitLoss.setBuyChargesInINR( profitLoss.getBuyChargesInINR() + trade.getFeeAmount());
            //buy tds
            profitLoss.setBuyTdsInINR( profitLoss.getBuyTdsInINR() +  trade.getTdsInINR());
        }else if("Sell".equals(trade.getTradeType())){
            //sell in total
            profitLoss.setSellInTotal(profitLoss.getSellInTotal() +  trade.getTotal());
            //quantity sold
            profitLoss.setQuantitySold( profitLoss.getQuantitySold() + trade.getVolume());
            //sell charges
            profitLoss.setSellChargesInINR( profitLoss.getSellChargesInINR() + trade.getFeeAmount());
            //sell chanrges in tds
            profitLoss.setSellTdsInINR(profitLoss.getSellTdsInINR() +  trade.getTdsInINR());
        }
        marketToPLMap.put(trade.getMarket(), profitLoss);
    }

    private static Trade parseRowToProfitAndLoss(Row row, Long counter) {
        try {
            Date date = row.getCell(0).getDateCellValue();
            String market = row.getCell(1).getStringCellValue();
            double price = row.getCell(2).getNumericCellValue();
            double volume = row.getCell(3).getNumericCellValue();
            double total = row.getCell(4).getNumericCellValue();
            String tradeType = row.getCell(5).getStringCellValue();
            String feePaidIn = row.getCell(6).getStringCellValue();
            double feeAmount = row.getCell(7).getNumericCellValue();
            String tdsPaidIn = row.getCell(8).getStringCellValue();
            double tdsAmount = row.getCell(9).getNumericCellValue();
            double tdsInINR = row.getCell(10).getNumericCellValue();

            return new Trade( counter,  date, market, price, volume, total, tradeType, feePaidIn, feeAmount, tdsPaidIn, tdsAmount, tdsInINR);
        } catch (Exception e) {
            System.err.println("Error parsing row: " + e.getMessage());
            return null; // Return null for invalid rows
        }
    }

    private static void writeInExcel(Map<String, ProfitLoss> profitLossMap){

        // Create a new workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Profit and Loss Analysis");

        // Define column headers
        String[] headers = {
                "Market", "ID", "Buy In Total", "Quantity Bought", "Buy Charges in INR",
                "Buy TDS in Amount", "Buy TDS in INR", "Sell In Total", "Quantity Sold",
                "Sell Charges INR", "Sell TDS in Amount", "Sell TDS in INR", "P/L",
                "Total Charges", "Total TDS INR", "Total Charges and Tax", "Net P/L",
                "Quantity in Reserve", "Value INR", "Status", "Profit / Loss"
        };

        // Create the header row
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        // Fill the sheet with data
        int rowIndex = 1;
        for (Map.Entry<String, ProfitLoss> entry : profitLossMap.entrySet()) {
            ProfitLoss pl = entry.getValue();
            Row row = sheet.createRow(rowIndex++);

            row.createCell(0).setCellValue(entry.getKey());  // Market
            row.createCell(1).setCellValue(pl.getId());  // ID
            row.createCell(2).setCellValue(pl.getBuyInTotal());  // Buy In Total
            row.createCell(3).setCellValue(pl.getQuantityBought());  // Quantity Bought
            row.createCell(4).setCellValue(pl.getBuyChargesInINR());  // Buy Charges in INR
            row.createCell(5).setCellValue(pl.getBuyTdsInAmount());  // Buy TDS in Amount
            row.createCell(6).setCellValue(pl.getBuyTdsInINR());  // Buy TDS in INR
            row.createCell(7).setCellValue(pl.getSellInTotal());  // Sell In Total
            row.createCell(8).setCellValue(pl.getQuantitySold());  // Quantity Sold
            row.createCell(9).setCellValue(pl.getSellChargesInINR());  // Sell Charges INR
            row.createCell(10).setCellValue(pl.getSellTdsInAmount());  // Sell TDS in Amount
            row.createCell(11).setCellValue(pl.getSellTdsInINR());  // Sell TDS in INR
            row.createCell(12).setCellValue(pl.getProfitOrLoss());  // P/L
            row.createCell(13).setCellValue(pl.getTotalCharges());  // Total Charges
            row.createCell(14).setCellValue(pl.getTotalTdsInINR());  // Total TDS INR
            row.createCell(15).setCellValue(pl.getTotalChargesAndTax());  // Total Charges and Tax
            row.createCell(16).setCellValue(pl.getNetProfitOrLoss());  // Net P/L
            row.createCell(17).setCellValue(pl.getQuantityInReserve());  // Quantity in Reserve
            row.createCell(18).setCellValue(pl.getValueInINR());  // Value INR
            row.createCell(19).setCellValue(pl.getStatus());  // Status
            row.createCell(20).setCellValue(pl.getProfitOrLossString());  // Status
        }

        // Write the workbook to a file
        File file = new File("/Users/suraj-13251/Downloads/ProfitAndLossAnalysis.xlsx");
        try (FileOutputStream fileOut = new FileOutputStream(file)) {
            workbook.write(fileOut);
            workbook.close();  // Close the workbook
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Excel file created successfully!");
    }
}
