package com.timbuchalka;

// Create a new class for a bank account
// Create fields for the account number, balance, customer name, email and phone number.
//
// Create getters and setters for each field
// Create two additional methods
// 1. To allow the customer to deposit funds (this should increment the balance field).
// 2. To allow the customer to withdraw funds. This should deduct from the balance field,
// but not allow the withdrawal to complete if their are insufficient funds.
// You will want to create various code in the Main class (the one created by IntelliJ) to
// confirm your code is working.
// Add some System.out.println's in the two methods above as well.

public class Main {

    public static void main(String[] args) {
        Accout bobsAccount = new Accout("12345", 0, "bob",
                "bob@gamil.com", "321-301-2212");
        bobsAccount.withdrawal(100.0);

        bobsAccount.deposit(50.0);
        bobsAccount.withdrawal(100.0);

        bobsAccount.deposit(51);
        bobsAccount.withdrawal(100.0);

        Accout timsAccount = new Accout("Tim",
                "tim@gmail.com","321-987-1234");
        System.out.println(timsAccount.getNumber() + " name " + timsAccount.getCustomerPhoneNumer());
        // Create a new class VipCustomer
        // it should have 3 fields name, credit limit, and email address.
        // create 3 constructors
        // 1st constructor empty should call the constructor with 3 parameters with default values
        // 2nd constructor should pass on the 2 values it receives and add a default value for the 3rd
        // 3rd constructor should save all fields.
        // create getters only for this using code generation of intellij as setters wont be needed
        // test and confirm it works.

        VipPerson person1 = new VipPerson();
        System.out.println(person1.getName());

        VipPerson person2 = new VipPerson("Bob", 25000.0);
        System.out.println(person2.getName());

        VipPerson person3 = new VipPerson("Tim", 1000.0, "tim@gmail.com");
        System.out.println(person3.getName() + "  " + person3.getEmailAddress());
    }
}
