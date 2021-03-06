package by.it_academy.jd2.Mk_JD2_82_21_employees.service;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.AutoFiller;
import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Position;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IAutoFillerService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IDepartmentService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IEmployeeService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IPositionService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.file_storage.FileDepartmentStorage;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.file_storage.FileEmployeeStorage;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.file_storage.FilePositionStorage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class NewAutoFillerService implements IAutoFillerService {

    private static final int MAX_VALUE_OF_SALARY_PARAM_NAME = 100_000_000;

    private final IDepartmentService departmentService;
    private final IEmployeeService employeeService;
    private final IPositionService positionService;

    public NewAutoFillerService(IDepartmentService departmentService, IEmployeeService employeeService,
                                IPositionService positionService){
        this.departmentService = departmentService;
        this.employeeService = employeeService;
        this.positionService = positionService;
    }

    @Override
    public void fillDateBase(AutoFiller autoFiller) {
        List<String> listOfPositionNames = FilePositionStorage.getInstance().getListOfPositionNames();
        List<Position> listOfPositions = getListOfPositions(listOfPositionNames);
        positionService.autoAddingPositions(listOfPositions);
        Long[] arrayOfPositionId = getArrayOfPositionId();

        List<String> listOfDepartmentNames = FileDepartmentStorage.getInstance().getListOfDepartmentsNames();
        List<Department> listOfDepartments = getListOfDepartments(listOfDepartmentNames);
        departmentService.autoAddingDepartment(listOfDepartments);

        int count = listOfDepartmentNames.size();
       Long[] id = getArrayOfNewDepartmentId(count);

        List<Integer> listIndexParentalDepartmentId = FileDepartmentStorage.getInstance().getListOfParentalDepartment();
        Integer[] arrayIndexParentalDepartmentId = listIndexParentalDepartmentId.toArray(new Integer[0]);

        departmentService.autoAddingParentalDepartment(id, arrayIndexParentalDepartmentId);


        List<String> listOfEmployeeNames = FileEmployeeStorage.getInstance().getArrayOfNames();
        String[] arrayOfEmployee = listOfEmployeeNames.toArray(new String[0]);
        List<Employee> listOfEmployee = getListOfEmployee(autoFiller.getCount(), arrayOfEmployee,
                id, arrayOfPositionId);

       employeeService.autoAddingOfEmployees(listOfEmployee);
    }

    private List<Position> getListOfPositions(List<String> listOfPositionNames){
        List<Position> listOfPositions = new LinkedList<>();
        listOfPositionNames.forEach(x -> {
            Position position = new Position();
            position.setName(x);
            listOfPositions.add(position);
        });
        return listOfPositions;
    }

    private Long[] getArrayOfPositionId(){
        List<Position> listOfPositions = positionService.getListOfPositions();
        List<Long> listOfPositionId = new LinkedList<>();
        listOfPositions.forEach(x -> listOfPositionId.add(x.getId()));
        return listOfPositionId.toArray(new Long[0]);
    }

    private List<Department> getListOfDepartments(List<String> listOfDepartmentsName) {
        List<Department> listOfDepartments = new LinkedList<>();

        listOfDepartmentsName.forEach(x -> {
            Department department = new Department();
            department.setName(x);
            listOfDepartments.add(department);
        });
        return listOfDepartments;
    }

    private Long[] getArrayOfNewDepartmentId(int count) {
        List<Long> listId = departmentService.getListOfDepartmentId();
        Long[] arrayId = listId.toArray(new Long[0]);
        return Arrays.copyOfRange(arrayId, arrayId.length - count, arrayId.length);
    }

    private List<Employee> getListOfEmployee(int countEmployee, String[] arrayOfEmployeeName,
                                             Long[] arrayOfDepartmentId, Long[] arrayOfPositionId) {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < countEmployee; i++) {
            Employee employee = new Employee();
            employee.setName(autoGenerateEmployeeName(arrayOfEmployeeName));
            employee.setSalary(autoGenerateSalary());

            Department department = departmentService
                    .getDepartment(autoGenerateDepartment(arrayOfDepartmentId));
            employee.setDepartment(department);

            Position position = positionService.getPosition(autoGeneratePosition(arrayOfPositionId));
            employee.setPosition(position);

            employees.add(employee);
        }
        return employees;
    }

    private String autoGenerateEmployeeName(String[] array) {
        int a = (int) (Math.random() * array.length);
        return array[a];
    }

    private double autoGenerateSalary() {
        double b = Math.random() * MAX_VALUE_OF_SALARY_PARAM_NAME;
        return BigDecimal.valueOf(b).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private long autoGenerateDepartment(Long[] departmentId) {
        int c = (int) (Math.random() * (departmentId.length - 1));
        return departmentId[c];
    }

    private long autoGeneratePosition(Long[] positionId) {
        int d = (int) (Math.random() * (positionId.length - 1));
        return positionId[d];
    }
}
