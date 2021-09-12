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

public class DBDepartmentFiller {
    private static DBDepartmentFiller instance = new DBDepartmentFiller();

    private static final String DIRECTORY_WITH_FILES_PARAM_NAME = "../conf/file";
    private static final String FILE_WITH_DEPARTMENT_PARAM_NAME = "listDepartment.xlsx";

    private DBDepartmentFiller(){
    }

    public void autoAddingDepartment(){
        autoAddingDepartmentName();
        Long[] id = getArrayOfDepartmentId();
        autoAddingParentalDepartment(id);
    }

    private void autoAddingDepartmentName(){
        try (Connection con = DBNewInitializer.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO application.departments(\n" +
                     "name_department)\n" + "VALUES (?);")
        ){
            List<String> positions = getListOfDepartment();

            for (String item : positions) {
                preparedStatement.setString(1, item);

                preparedStatement.executeUpdate();
            }

        }  catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
    }

    private List<String> getListOfDepartment() {
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
                    String name = cell.getStringCellValue();
                    if (!name.equals("")) {
                        listDepartment.add(name);
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

    public Long[] getArrayOfDepartmentId(){
        List<Long> listOfDepartmentId = new ArrayList<>();
        try (Connection con = DBNewInitializer.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id FROM application.departments")
        ){
            while (resultSet.next()){
                listOfDepartmentId.add(resultSet.getLong(1));
            }

        }  catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        Long[] arrayOfDepartmentId = listOfDepartmentId.toArray(new Long[0]);
        return arrayOfDepartmentId;
    }


    private void autoAddingParentalDepartment(Long[] array){
        try (Connection con = DBNewInitializer.getConnection();
        ){
            for (int i = 0; i < array.length; i++) {
                long departmentId = array[i];
                int a = (int) (Math.random() * (array.length - 1));
                long parentalId = array[a];

                try (Statement statement = con.createStatement();
                ){
                    statement.executeUpdate("UPDATE application.departments SET parental_department = " + parentalId +
                            "WHERE id = " + departmentId);
                }
            }

        }  catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
    }

    public static DBDepartmentFiller getInstance(){
        return instance;
    }
}
