package choresModule;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ChoresUtils {
	
	
	//public ArrayList<Chore> chores = new ArrayList<Chore>();


	//Might have to make a constructor for this class
	////import all chores
	////import all categories
	
	/*//
	SORTING & FILTERING
	///*
	 * Sort Alphabetically
	 * Sort by amount of time
	 * Sort by payment amount
	 * 
	 * Filter Completed/Not Completed
	 * Filter paid or not
	 * Filter by Category
	 * 
	 */
	
	public static List<Chore> sortAlphabetically(List<Chore> chores) {
	//	//making a string arrayList because collection sort only works on java made objects
	//	ArrayList<String> choresString = new ArrayList<>();
	//	for (int i=0;i<chores.size();i++) {
	//		//using a for loop to convert each element from chores into a string
	//		choresString.add(chores.get(i).toString());
	//	}
	//	//sorting the string arrayList by alphabets
	//    Collections.sort(choresString);
		
		List<Chore> secondaryList = new ArrayList<>(chores);
		Collections.sort(secondaryList, Comparator.comparing(Chore::getName));
		return secondaryList;
	}
	
	public static List<Chore> sortByTime(List<Chore> chores) {
		//chore.getTime();
		
		//import 
		
		//return sorted list
	//	ArrayList<Double> choresTime = new ArrayList<>();
	//	for (int i=0;i<chores.size();i++) {
	//		choresTime.add(chores.get(i).getTime());
	//	}
	//	Collections.sort(choresTime);
		
		List<Chore> secondaryList = new ArrayList<>(chores);
		Collections.sort(secondaryList, Comparator.comparing(Chore::getTime));
		return secondaryList;
		
	}
	
	public static List<Chore> sortByPayment(List<Chore> chores) {
		//chore.getPayment();
		
		//import 
		
		//return sorted list
	//	ArrayList<Double> choresPayment = new ArrayList<>();
	//	for (int i=0;i<chores.size();i++) {
	//		choresPayment.add(chores.get(i).getPayment());
	//	}
	//	Collections.sort(choresPayment);
		
		List<Chore> secondaryList = new ArrayList<>(chores);
		Collections.sort(secondaryList, Comparator.comparing(Chore::getPayment));
		return secondaryList;
	}
	
	public static List<Chore> filterCompleted(List<Chore> chores) {
		//chore.isCompleted();
		
		//import 
		
		//return filtered list
		
		/*We can add an option where if a person clicks on a button
		 * It will display the ones that are completed and vice versa.
		 * For that we need some more code classes, and once that is done
		 * we can edit this method by adding an if statement*/
	//	ArrayList<Boolean> choresComplete = new ArrayList<>();
	//	for (int i=0;i<chores.size();i++) {
	//		choresComplete.add(chores.get(i).isCompleted());
	//	}
	//	Collections.sort(choresComplete);
		
		List<Chore> secondaryList = new ArrayList<>();
		for(int i = 0; i < chores.size(); i++) {
			if(chores.get(i).isCompleted()) {
				secondaryList.add(chores.get(i));
			}
		}
		return secondaryList;
	}
	
	public static List<Chore> filterNotCompleted(List<Chore> chores) {
		
		List<Chore> secondaryList = new ArrayList<>();
		for(int i = 0; i < chores.size(); i++) {
			if(!chores.get(i).isCompleted()) {
				secondaryList.add(chores.get(i));
			}
		}
		return secondaryList;
	}
	
	public static List<Chore> filterPaid(List<Chore> chores) {
		//chore.isPaid();
		
		//import 
	
		//return filtered list
		/*We can add an option where if a person clicks on a button
		 * It will display the ones that are paid and vice versa.
		 * For that we need some more code classes, and once that is done
		 * we can edit this method by adding an if statement*/
	//	ArrayList<Boolean> choresPaid = new ArrayList<>();
	//	for (int i=0;i<chores.size();i++) {
	//		choresPaid.add(chores.get(i).isPaid());
	//	}
	//	Collections.sort(choresPaid);
		
		List<Chore> secondaryList = new ArrayList<>();
		for(int i = 0; i < chores.size(); i++) {
			if(chores.get(i).isPaid()) {
				secondaryList.add(chores.get(i));
			}
		}
		return secondaryList;
	}
	
	public static List<Chore> filterNotPaid(List<Chore> chores) {
		
		List<Chore> secondaryList = new ArrayList<>();
		for(int i = 0; i < chores.size(); i++) {
			if(!chores.get(i).isPaid()) {
				secondaryList.add(chores.get(i));
			}
		}
		return secondaryList;
	}
	
	public static List<Chore> filterCategory(String category, List<Chore> chores) {
		//chore.getCatogory();
		
		//import 
		
		//return filtered list
		/*We can add an option where if a person clicks on a button
		 * It will display the specified category.
		 * For that we need some more code classes, and once that is done
		 * we can edit this method by adding an if statement*/
	//	ArrayList<String> choresCategory = new ArrayList<>();
	//	for (int i=0;i<chores.size();i++) {
	//		choresCategory.add(chores.get(i).getCategory());
	//	}
	//	Collections.sort(choresCategory);
	
		List<Chore> secondaryList = new ArrayList<>();
		for(int i = 0; i < chores.size(); i++) {
			if(chores.get(i).getCategory().equals(category)) {
				secondaryList.add(chores.get(i));
			}
		}
		return secondaryList;
	}
	
	public static void createExcelSheet(List<Chore> chores) {
        try {
            // Prompt user to choose a file location
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");
            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath() + ".xlsx";

                try (Workbook workbook = new XSSFWorkbook()) {
                    Sheet sheet = workbook.createSheet("Chores");

                    // Create header row
                    Row headerRow = sheet.createRow(0);
                    headerRow.createCell(0).setCellValue("Name");
                    headerRow.createCell(1).setCellValue("Category");
                    headerRow.createCell(2).setCellValue("Time (minutes)");
                    headerRow.createCell(3).setCellValue("Payment");
                    headerRow.createCell(4).setCellValue("Completed");
                    headerRow.createCell(5).setCellValue("Paid");

                    // Fill data rows
                    int rowNum = 1;
                    for (Chore chore : chores) {
                        Row row = sheet.createRow(rowNum++);
                        row.createCell(0).setCellValue(chore.getName());
                        row.createCell(1).setCellValue(chore.getCategory());
                        row.createCell(2).setCellValue(chore.getTime());
                        row.createCell(3).setCellValue(chore.getPayment());
                        row.createCell(4).setCellValue(chore.isCompleted() ? "Yes" : "No");
                        row.createCell(5).setCellValue(chore.isPaid() ? "Yes" : "No");
                    }

                    // Write the workbook to the file
                    try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                        workbook.write(fileOut);
                    }

                    JOptionPane.showMessageDialog(null, "Excel sheet created successfully!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
