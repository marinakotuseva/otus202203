package homework;


import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class CustomerService {
    private final Map<Customer, String> customers = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        Optional<Map.Entry<Customer, String>> smallestOpt = customers.entrySet().stream().findFirst()
            .map(entry -> new AbstractMap.SimpleImmutableEntry<>(entry.getKey(), entry.getValue()));

        if (!smallestOpt.isPresent()) return null;

        Map.Entry<Customer, String> currentData = smallestOpt.get();
        return new AbstractMap.SimpleEntry<>(getNewCustomer(currentData.getKey()), currentData.getValue());
    }

    private Customer getNewCustomer(Customer currentCustomer) {
        return new Customer(currentCustomer.getId(), currentCustomer.getName(), currentCustomer.getScores());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return customers.entrySet().stream()
            .filter(entry -> entry.getKey().getScores() > customer.getScores())
            .findFirst()
            .map(entry -> new AbstractMap.SimpleEntry<>(getNewCustomer(entry.getKey()), entry.getValue()))
            .orElse(null);
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }
}
