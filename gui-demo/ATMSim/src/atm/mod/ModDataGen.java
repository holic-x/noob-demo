package atm.mod;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 模拟数据生成器
 */
public class ModDataGen {

    /**
     * 模拟ATM用户账号列表信息
     * @return
     */
    public static List<ModUser> modUserList(){
        List<ModUser> modUserList = new ArrayList<>();
        modUserList.add(new ModUser("1111","1111",new BigDecimal("10000.00")));
        return modUserList;
    }


}
