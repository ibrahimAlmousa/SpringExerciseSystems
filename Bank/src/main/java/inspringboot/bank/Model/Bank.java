package inspringboot.bank.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class Bank {

    private int id;
    private String username;
    private double balance;

}
