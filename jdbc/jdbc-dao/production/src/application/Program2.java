package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("=== TESTER 1: department findById ===");
        Department department = departmentDao.findById(3);
        System.out.println(department);

        System.out.println();
        System.out.println("=== TESTER 2: department findAll ===");
        List<Department> list = departmentDao.findAll();
        list = departmentDao.findAll();
        for( Department obj: list){
            System.out.println(obj);
        }

        System.out.println();
        System.out.println("=== TESTER 3: department insert ===");
        Department newDepartment = new Department(null, "Greg");
        departmentDao.insert(newDepartment);
        System.out.println("Inserted! New id = " + newDepartment.getId());

        System.out.println();
        System.out.println("=== TESTER 4: department update ===");
        department = departmentDao.findById(1);
        department.setName("TI");
        departmentDao.update(department);
        System.out.println("Update completed");

        System.out.println();
        System.out.println("=== TESTER 5: department delete ===");
        System.out.println("Enter id for delete test: ");
        int id = sc.nextInt();
        departmentDao.deleteById(id);
        System.out.println("Delete completed! ");

        sc.close();
    }
}
