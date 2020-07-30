package company;

import company.model.CEO;
import company.model.Employee;

public class RunnerMainClass {
    public static void main(String[] args) {
        CompanyEmployeeHierarchy hierarchy = CompanyEmployeeHierarchy.create(new CEO("ceoId", 5));
        hierarchy.addEmployee(new Employee("id1", 5));
        hierarchy.assignManager("ceoId", "id1");
        hierarchy.assignBonus(1000);
    }
}
