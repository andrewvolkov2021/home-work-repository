package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.file_storage;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Position;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.api.IFilePositionStorage;
import org.apache.poi.ss.usermodel.Cell;
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

public class FilePositionStorage implements IFilePositionStorage {

    private static final FilePositionStorage instance = new FilePositionStorage();

    private static final String DIRECTORY_WITH_FILES_PARAM_NAME = "../conf/file";
    private static final String FILE_WITH_POSITION_PARAM_NAME = "listPosition.xlsx";

    private FilePositionStorage() {
    }

    @Override
    public List<String> getListOfPositionNames() {
        List<String> listPositionsName = new ArrayList<>();

        try (FileInputStream inputStream = new FileInputStream(new File(DIRECTORY_WITH_FILES_PARAM_NAME,
                FILE_WITH_POSITION_PARAM_NAME));
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                Iterator<Cell> cellIterator = row.iterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    String name = cell.getStringCellValue();
                    if (!name.equals("")) {
                        listPositionsName.add(name);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Ошибка при чтении файла xlsx");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла xlsx");
        }

        return listPositionsName;
    }

    public static FilePositionStorage getInstance(){
        return instance;
    }
}
