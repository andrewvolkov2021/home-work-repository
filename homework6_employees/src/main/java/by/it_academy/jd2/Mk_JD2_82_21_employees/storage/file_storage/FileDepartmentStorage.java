package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.file_storage;

import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.api.IFileDepartmentStorage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;

public class FileDepartmentStorage implements IFileDepartmentStorage {

    private static final FileDepartmentStorage instance = new FileDepartmentStorage();

    private static final String DIRECTORY_WITH_FILES_PARAM_NAME = "../conf/file";
    private static final String FILE_WITH_DEPARTMENT_PARAM_NAME = "newListDepartment.xlsx";

    private FileDepartmentStorage() {
    }

    @Override
    public List<String> getListOfDepartmentsNames() {
        List<String> listDepartment = new ArrayList<>();

        try (FileInputStream inputStream = new FileInputStream(new File(DIRECTORY_WITH_FILES_PARAM_NAME,
                FILE_WITH_DEPARTMENT_PARAM_NAME));
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.iterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    CellType cellType = cell.getCellTypeEnum();
                    if (cellType == STRING) {
                        String name = cell.getStringCellValue();
                        if (!name.equals("")) {
                            listDepartment.add(name);
                        }
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Ошибка при чтении файла xlsx");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла xlsx");
        }
        return listDepartment;
    }

    @Override
    public List<Integer> getListOfParentalDepartment() {
        List<Integer> listParentalDepartment = new ArrayList<>();

        try (FileInputStream inputStream = new FileInputStream(new File(DIRECTORY_WITH_FILES_PARAM_NAME,
                FILE_WITH_DEPARTMENT_PARAM_NAME));
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.iterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    CellType cellType = cell.getCellTypeEnum();
                    if (cellType == NUMERIC) {
                        Integer parentalDepartment = (int) cell.getNumericCellValue();
                        if (!parentalDepartment.equals("")) {
                            listParentalDepartment.add(parentalDepartment);
                        }
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Ошибка при чтении файла xlsx");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла xlsx");
        }
        return listParentalDepartment;
    }



    public static FileDepartmentStorage getInstance(){
        return instance;
    }
}
