package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.fillers;

import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.initialiazers.DBNewInitializer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DBPositionFiller {
    private static final DBPositionFiller instance = new DBPositionFiller();

    private static final String DIRECTORY_WITH_FILES_PARAM_NAME = "../conf/file";
    private static final String FILE_WITH_POSITION_PARAM_NAME = "listPosition.xlsx";

    private DBPositionFiller(){
    }

    public void autoAddingPositions(){
        try (Connection con = DBNewInitializer.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO application.positions(\n" +
                     "name_position)\n" + "VALUES (?);")
        ){
            List<String> positions = getListOfPosition();

            for (String item : positions) {
                preparedStatement.setString(1, item);
                preparedStatement.executeUpdate();
            }

        }  catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
    }

    private List<String> getListOfPosition() {
        List<String> listPosition = new ArrayList<>();

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
                        listPosition.add(name);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Ошибка при чтении файла xlsx");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла xlsx");
        }

        return listPosition;
    }

    public Long[] getArrayOfPositionId(){
        List<Long> listOfPositionId = new ArrayList<>();
        try (Connection con = DBNewInitializer.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id FROM application.positions")
        ){
            while (resultSet.next()){
                listOfPositionId.add(resultSet.getLong(1));
            }

        }  catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        Long[] arrayOfPositionId = listOfPositionId.toArray(new Long[0]);
        return arrayOfPositionId;
    }

    public static DBPositionFiller getInstance(){
        return instance;
    }
}
