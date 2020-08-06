package pricecalculator;

public class PriceCalculator {

    public static double estimatedPrice(double costPricePerUnitWithoutGST, double expectedProfit, int numberOfUnits) {

        //formula - x*.12 (referral charges) + totalCostPrice (includes shipping charges and fixed cost) * 1.1 (profit
        // of 10%) + x*.05 (GST to be paid) = x
        //t*1.1 = x-.05x-.12x
        //t*1.1 = x(1-.17)
        //x = t* (1.1/.83)
        double shippingCost = 77;
        double totalCostPrice = (costPricePerUnitWithoutGST * numberOfUnits) + shippingCost;

        return totalCostPrice * ((1 + (expectedProfit/100)) / 0.83);

        //(60*1.05) + 77 + 2 + 220*.12
    }

    public static void main(String[] args) {
        System.out.println(estimatedPrice(105, 0, 3));

        //Frontline 59.5 - RN

    }
}
