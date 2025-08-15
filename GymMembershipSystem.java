import java.util.ArrayList;
import java.util.Scanner;

// Base Membership class (Encapsulation)
class Membership {
    private String memberName;
    private String membershipType;
    private double monthlyFee;
    private double yearlyFee;

    public Membership(String memberName, String membershipType, double monthlyFee, double yearlyFee) {
        this.memberName = memberName;
        this.membershipType = membershipType;
        this.monthlyFee = monthlyFee;
        this.yearlyFee = yearlyFee;
    }

    public double calculateFee(int months, int years) {
        return (months * monthlyFee) + (years * yearlyFee);
    }

    public void displayDetails() {
        System.out.println("Member: " + memberName);
        System.out.println("Membership Type: " + membershipType);
        System.out.println("Monthly Fee: ₹" + monthlyFee + " | Yearly Fee: ₹" + yearlyFee);
    }
}

// Standard Membership (Inheritance)
class StandardMembership extends Membership {
    public StandardMembership(String memberName) {
        super(memberName, "Standard", 1000, 10000); // Fixed rates
    }
}

// Premium Membership (Inheritance + Polymorphism)
class PremiumMembership extends Membership {
    public PremiumMembership(String memberName) {
        super(memberName, "Premium", 2000, 18000); // Higher rates
    }

    // Overriding calculateFee (Polymorphism)
    @Override
    public double calculateFee(int months, int years) {
        double baseFee = super.calculateFee(months, years);
        double discount = baseFee * 0.1; // 10% discount for premium
        return baseFee - discount;
    }
}

public class GymMembershipSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Double> feeList = new ArrayList<>();

        System.out.println("💪 Welcome to Gym Membership Management System 🏋");

        String choice = null;
        do {
            System.out.print("\nEnter Member Name: ");
            String name = sc.nextLine();

            System.out.print("Choose Membership Type (Standard/Premium): ");
            String type = sc.nextLine();

            Membership membership;
            if (type.equalsIgnoreCase("Standard")) {
                membership = new StandardMembership(name);
            } else if (type.equalsIgnoreCase("Premium")) {
                membership = new PremiumMembership(name);
            } else {
                System.out.println("Invalid type! Please choose Standard or Premium.");
                continue;
            }

            membership.displayDetails();

            System.out.print("Enter number of months: ");
            int months = sc.nextInt();

            System.out.print("Enter number of years: ");
            int years = sc.nextInt();

            double fee = membership.calculateFee(months, years);
            feeList.add(fee);
            System.out.println("Membership Fee for " + name + ": ₹" + fee);

            sc.nextLine(); // consume newline
            System.out.print("\nDo you want to register another member? (yes/no): ");
            choice = sc.nextLine();

        } while (choice.equalsIgnoreCase("yes"));

        // Final Report
        double total = 0;
        for (double fee : feeList) {
            total += fee;
        }
        System.out.println("\n💰 Total Collected Membership Fee: ₹" + total);
        System.out.println("✅ Thank you for managing memberships with us!");

        sc.close();
    }
}