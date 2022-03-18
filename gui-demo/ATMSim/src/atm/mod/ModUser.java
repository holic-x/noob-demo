package atm.mod;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 模拟用户信息
 */
public class ModUser {

    // 账户名
    private String userName;

    // 账户密码
    private String password;

    // 账户余额
    private BigDecimal amount;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public ModUser(String userName, String password, BigDecimal amount) {
        this.userName = userName;
        this.password = password;
        this.amount = amount;
    }



}
