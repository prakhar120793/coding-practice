package company;

import company.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyEmployeeHierarchy {
    private final Map<String, HierarchyNode> employeeToHierarchyMap;
    private final CEO ceo;

    protected CompanyEmployeeHierarchy(Map<String, HierarchyNode> employeeToHierarchyMap, CEO ceo) {
        this.employeeToHierarchyMap = employeeToHierarchyMap;
        this.ceo = ceo;
    }

    public static CompanyEmployeeHierarchy create(CEO ceo) {
        Map<String, HierarchyNode> hierarchyNodeMap = new HashMap<>();
        hierarchyNodeMap.put(ceo.getId(), new HierarchyNode(ceo, new ArrayList<>()));
        return new CompanyEmployeeHierarchy(hierarchyNodeMap, ceo);
    }


    public HierarchyNode printHierarchy(String employeeId) {
        if (this.employeeToHierarchyMap.containsKey(employeeId)) {
            return this.employeeToHierarchyMap.get(employeeId);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void addEmployee(EmployeeBase employeeBase) {
        HierarchyNode hierarchyNode = new HierarchyNode(employeeBase, new ArrayList<>());
        this.employeeToHierarchyMap.put(employeeBase.getId(), hierarchyNode);
    }

    public void assignManager(String managerId, String employeeId) {
        if (isAManager(managerId) && employeeNotCEO(employeeId)) {
            HierarchyNode managerHierarchyNode = this.employeeToHierarchyMap.get(managerId);
            HierarchyNode employeeHierarchyNode = this.employeeToHierarchyMap.get(employeeId);
            managerHierarchyNode.getChildren().add(employeeHierarchyNode);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private boolean isAManager(String managerId) {
        return this.employeeToHierarchyMap.containsKey(managerId) &&
                this.employeeToHierarchyMap.get(managerId).getEmployeeBase() instanceof Manager;
    }

    private boolean employeeNotCEO(String employeeId) {
        return this.employeeToHierarchyMap.containsKey(employeeId) &&
                !(this.employeeToHierarchyMap.get(employeeId).getEmployeeBase() instanceof CEO);
    }

    public void assignBonus(double bonusAmount) {
        assignBonusRecursively(bonusAmount, ceo.getId());
    }

    private void assignBonusRecursively(double bonusAmount, String employeeId) {
        if (this.employeeToHierarchyMap.containsKey(employeeId)) {
            HierarchyNode employeeHierarchyNode = this.employeeToHierarchyMap.get(employeeId);
            employeeHierarchyNode.setBonus(bonusAmount);
            List<HierarchyNode> children = employeeHierarchyNode.getChildren();
            int totalRatings = 0;
            for (HierarchyNode node : children) {
                totalRatings += node.getEmployeeBase().getRating();
            }
            for (HierarchyNode node : children) {
                assignBonusRecursively((bonusAmount * node.getEmployeeBase().getRating()) / totalRatings,
                        node.getEmployeeBase().getId());
            }
        }
    }

}
