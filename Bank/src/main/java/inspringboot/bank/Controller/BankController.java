package inspringboot.bank.Controller;

import inspringboot.bank.ApiResponse.ApiResponse;
import inspringboot.bank.Model.Bank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@RestController
public class BankController {
    ArrayList<Bank> customers = new ArrayList<>();


    @GetMapping("/get")
    public ArrayList<Bank> getAllCustomers(){
        return customers;
    }

    @PostMapping("/add")
    public ApiResponse addCustomer(@RequestBody Bank customer){
        customers.add(customer);
        return new ApiResponse("Customer added successfully");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateCustomer(@PathVariable int index, @RequestBody Bank customer){
        customers.set(index, customer);
        return new ApiResponse("Customer updated successfully");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteCustomer(@PathVariable int index){
        customers.remove(index);
        return new ApiResponse("Customer deleted successfully");
    }


    public ApiResponse deposit(@PathVariable int index, @RequestParam double amount){
        if(amount <= 0)
            return new ApiResponse("Amount must be greater than 0");
        Bank c = customers.get(index);
        c.setBalance(c.getBalance() + amount);
        return new ApiResponse("Deposited " + amount + " successfully. New balance: " + c.getBalance());
    }

    @PutMapping("/withdraw/{index}")
    public ApiResponse withdraw(@PathVariable int index, @RequestParam double amount){
        Bank c = customers.get(index);
        if(amount <= 0)
            return new ApiResponse("Amount must be greater than 0");
        if(amount > c.getBalance())
            return new ApiResponse("Insufficient balance");
        c.setBalance(c.getBalance() - amount);
        return new ApiResponse("Withdrawn " + amount + " successfully. New balance: " + c.getBalance());
    }

    private boolean valid(int index){
        return index >= 0 && index < customers.size();
    }


}
