package aiAgent.drugInfoCrawl;

import com.noob.base.aiAgent.drugInfoCrawl.entity.dto.DrugInfoDTO;
import com.noob.base.aiAgent.helper.DrugInfoGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.openjdk.jol.info.ClassLayout;

@Slf4j
@RunWith(JUnit4ClassRunner.class)
public class MonitorTest {

    @Test
    public void test_cal_entity() {
        DrugInfoDTO dto = DrugInfoGenerator.generateDrugList(1).get(0);
        // 打印单个DTO的内存布局
        System.out.println(ClassLayout.parseInstance(dto).toPrintable());
        // 打印DTO中String字段的内存布局
        System.out.println(ClassLayout.parseInstance(dto.getOwner()).toPrintable());
    }
}
