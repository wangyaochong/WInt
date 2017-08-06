package program.repository;
import program.entity.Customer;
public class ICustomerRepoImpl implements ICustomerRepoSelf<Customer,String> {
    public Customer testAdd(){
        return new Customer();
    }
}
