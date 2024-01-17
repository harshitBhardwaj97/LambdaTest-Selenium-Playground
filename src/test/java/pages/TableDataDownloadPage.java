package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import base.Base;

public class TableDataDownloadPage extends Base {

	@FindBy(xpath = "//span[.='Copy']")
	@CacheLookup
	private WebElement copyButton;

	@FindBy(xpath = "//span[.='CSV']")
	@CacheLookup
	private WebElement csvDownloadButton;

	@FindBy(xpath = "//span[.='Excel']")
	@CacheLookup
	private WebElement excelDownloadButton;

	@FindBy(xpath = "//input[@type='search']")
	@CacheLookup
	private WebElement searchBox;

	public void searchForText(String text) {
		searchBox.clear();
		searchBox.sendKeys(text);
	}

	public void clickButton(WebElement button) {
		button.click();
	}

	public WebElement getCopyButton() {
		return copyButton;
	}

	public WebElement getCSVDownloadButton() {
		return csvDownloadButton;
	}

	public WebElement getExcelDownloadButton() {
		return excelDownloadButton;
	}

	/*
	 * Verifies that correct content is copied, by matching it with expected copied
	 * content that is already defined in the resources directory
	 */
	public boolean checkCopyContentFunctionality(String copiedContent) {

		final String EXPECTED_CONTENT_PATH = Base.resourcesDirectory + "\\expectedCopiedContent.txt";

		boolean contentMatches = true; // Assume it to work properly by default

		try {

			List<String> lines = Files.readAllLines(Paths.get(EXPECTED_CONTENT_PATH));

			String[] expectedLines = lines.stream().toArray(String[]::new);
			String[] actualLines = copiedContent.split("\n");

			for (int i = 0; i < expectedLines.length; i++) {
				String expectedLine = expectedLines[i];
				String actualLine = actualLines[i];

				/*
				 * Compare the lines, by keeping in all white-spaces, tabs and line-breaks in
				 * mind
				 */
				if (expectedLine.equals(actualLine)) {
					System.out.println("Line " + (i + 1) + " is equal.");
				} else {
					System.out.println("Line " + (i + 1) + " is not equal.");
					contentMatches = false; // In they are not equal.
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Expected Copied Content File is not found, hence not able to complete this test !");
			contentMatches = false; // In case of exception
		}

		return contentMatches;
	}

	/*
	 * Verifies that correct CSV is downloaded, by matching it with expected content
	 * that is already defined in the resources directory.
	 */
	public boolean checkCSVDownloadFunctionality() {

		final String EXPECTED_CSV_CONTENT_PATH = Base.resourcesDirectory + "\\expectedDownloadedCSV.csv";
		final String ACTUAL_CSV_CONTENT_PATH = Base.downloadsDirectory
				+ "\\Selenium Grid Online  Run Selenium Test On Cloud.csv";

		Path filePath = FileSystems.getDefault().getPath(ACTUAL_CSV_CONTENT_PATH);

		// Wait max for 60 seconds to check CSV file is downloaded
		Wait<Path> wait = new FluentWait<>(filePath).withTimeout(Duration.ofSeconds(60))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(Exception.class);

		wait.until(path -> Files.exists(path));

		boolean csvContentMatches = true; // Assume it to work properly by default

		try {

			List<String> expectedCsvLines = Files.readAllLines(Paths.get(EXPECTED_CSV_CONTENT_PATH));
			List<String> actualCsvLines = Files.readAllLines(Paths.get(ACTUAL_CSV_CONTENT_PATH));

			for (int i = 0; i < expectedCsvLines.size(); i++) {
				String expectedCsvLine = expectedCsvLines.get(i);
				String actualCsvLine = actualCsvLines.get(i);

				/*
				 * Compare the lines, by keeping in all white-spaces, tabs and line-breaks in
				 * mind
				 */

				if (expectedCsvLine.equals(actualCsvLine)) {
					System.out.println("CSV Line " + (i + 1) + " is equal.");
				} else {
					System.out.println("CSV Line " + (i + 1) + " is not equal.");
					csvContentMatches = false; // In they are not equal.
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			csvContentMatches = false; // In case of exception
		}

		return csvContentMatches;
	}

	/*
	 * Verifies that correct XLSX is downloaded, by matching it with expected
	 * content that is already defined in the resources directory.
	 */
	public boolean checkXLSXDownloadFunctionality() {

		final String EXPECTED_XLSX_CONTENT_PATH = Base.resourcesDirectory + "\\expectedDownloadedXLSX.xlsx";
		final String ACTUAL_XLSX_CONTENT_PATH = Base.downloadsDirectory
				+ "\\Selenium Grid Online  Run Selenium Test On Cloud.xlsx";

		Path filePath = FileSystems.getDefault().getPath(ACTUAL_XLSX_CONTENT_PATH);

		// Wait max for 60 seconds to check XSLX file is downloaded
		Wait<Path> wait = new FluentWait<>(filePath).withTimeout(Duration.ofSeconds(60))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(Exception.class);

		wait.until(path -> Files.exists(path));

		boolean xlsxContentMatches = true; // Assume it to work properly by default

		try {

			xlsxContentMatches = compareExcelFiles(EXPECTED_XLSX_CONTENT_PATH, ACTUAL_XLSX_CONTENT_PATH);

		} catch (IOException e) {
			e.printStackTrace();
			xlsxContentMatches = false; // In case of exception
		}

		return xlsxContentMatches;
	}

	// To delete all files in beginning
	public void cleanDownloadDirectory(String downloadPath) {
		File downloadsDirectory = new File(downloadPath);

		if (downloadsDirectory.exists() && downloadsDirectory.isDirectory()) {
			File[] files = downloadsDirectory.listFiles();

			if (files != null) {
				for (File file : files) {
					if (file.isFile()) {
						file.delete();
					}
				}
			}
		}
	}

	/*
	 * Helper methods for comparing XLSX file defined here
	 */

	public boolean compareExcelFiles(String filePath1, String filePath2) throws IOException {
		try (FileInputStream fileInputStream1 = new FileInputStream(filePath1);
				FileInputStream fileInputStream2 = new FileInputStream(filePath2)) {

			Workbook workbook1 = WorkbookFactory.create(fileInputStream1);
			Workbook workbook2 = WorkbookFactory.create(fileInputStream2);

			int numberOfSheets1 = workbook1.getNumberOfSheets();
			int numberOfSheets2 = workbook2.getNumberOfSheets();

			if (numberOfSheets1 != numberOfSheets2) {
				return false; // Different number of sheets
			}

			for (int i = 0; i < numberOfSheets1; i++) {
				Sheet sheet1 = workbook1.getSheetAt(i);
				Sheet sheet2 = workbook2.getSheetAt(i);

				if (!compareSheets(sheet1, sheet2)) {
					return false; // Sheets are not equal
				}
			}

			return true; // All sheets are equal
		}
	}

	private boolean compareSheets(Sheet sheet1, Sheet sheet2) {
		int numberOfRows1 = sheet1.getPhysicalNumberOfRows();
		int numberOfRows2 = sheet2.getPhysicalNumberOfRows();

		if (numberOfRows1 != numberOfRows2) {
			return false; // Different number of rows found
		}

		for (int i = 0; i < numberOfRows1; i++) {
			Row row1 = sheet1.getRow(i);
			Row row2 = sheet2.getRow(i);

			if (!compareRows(row1, row2)) {
				return false; // Rows are not equal
			}
		}

		return true; // All rows are equal
	}

	private boolean compareRows(Row row1, Row row2) {
		int numberOfCells1 = row1.getPhysicalNumberOfCells();
		int numberOfCells2 = row2.getPhysicalNumberOfCells();

		if (numberOfCells1 != numberOfCells2) {
			return false; // Different number of cells
		}

		for (int i = 0; i < numberOfCells1; i++) {
			Cell cell1 = row1.getCell(i);
			Cell cell2 = row2.getCell(i);

			if (!compareCells(cell1, cell2)) {
				return false; // Cells are not equal
			}
		}

		return true; // All cells are equal
	}

	private boolean compareCells(Cell cell1, Cell cell2) {
		Object value1 = getCellValue(cell1);
		Object value2 = getCellValue(cell2);
		return Objects.equals(value1, value2);
	}

	private Object getCellValue(Cell cell) {

		if (cell == null) {
			return null;
		}

		switch (cell.getCellType()) {
		case NUMERIC:
			return cell.getNumericCellValue();
		case STRING:
			return cell.getStringCellValue();
		case BOOLEAN:
			return cell.getBooleanCellValue();
		case FORMULA:
			return cell.getCellFormula();
		case BLANK:
			return ""; // Treat blank cells as empty strings
		default:
			return null; // Unknown cell type
		}
	}

	public TableDataDownloadPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
