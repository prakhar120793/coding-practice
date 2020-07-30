package company.model;

import java.util.List;

public class HierarchyNode {
    private final EmployeeBase employeeBase;
    private final List<HierarchyNode> children;

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    private double bonus;


    public HierarchyNode(EmployeeBase employeeBase, List<HierarchyNode> children) {
        this.employeeBase = employeeBase;
        this.children = children;
    }

    public EmployeeBase getEmployeeBase() {
        return employeeBase;
    }

    public List<HierarchyNode> getChildren() {
        return children;
    }

}
