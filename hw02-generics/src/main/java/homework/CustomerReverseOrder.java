package homework;


import com.google.common.collect.Iterables;

import java.util.LinkedHashSet;
import java.util.Set;

public class CustomerReverseOrder {
    Set<Customer> customers = new LinkedHashSet<>();

    public void add(Customer customer) {
        customers.add(customer);
    }

    public Customer take() {
        Customer last = Iterables.getLast(customers);
        customers.remove(last);
        return last;
    }
}
