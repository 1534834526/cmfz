import com.baizhi.AppStart;
import com.baizhi.conf.ConvertFileSize;
import com.baizhi.conf.ValidateImageCodeUtils;
import com.baizhi.entity.Banner;
import com.baizhi.mapper.BannerMapper;
import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.UUID;

@SpringBootTest(classes = AppStart.class)
@RunWith(SpringRunner.class)
public class test {
    @Autowired
    private BannerMapper bannerMapper;

    @Test
    public void test1() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(6);
        list.add(7);


        Example example = new Example(Banner.class);
        example.createCriteria().andIn("id", list);
        bannerMapper.deleteByExample(example);
    }

    @Test
    public void test2() throws IOException {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        String securityCode = ValidateImageCodeUtils.getSecurityCode();
        System.out.println(securityCode);


        File file1 = new File("D:/images/1-1.png");
        if (file1.exists()) {
            FileInputStream fis = new FileInputStream(file1);
            System.out.println(fis.available());
        }

        String s = ConvertFileSize.convertFileSize(file1.length());
        System.out.println(s);
    }


    @Test
    public void test3() throws ParseException {
        /*int seconds = 540;
        int temp=0;
        StringBuffer sb=new StringBuffer();
        temp = seconds/3600;
        sb.append((temp<10)?"0"+temp+":":""+temp+":");

        temp=seconds%3600/60;
        sb.append((temp<10)?"0"+temp+":":""+temp+":");

        temp=seconds%3600%60;
        sb.append((temp<10)?"0"+temp:""+temp);

        System.out.println(sb.toString());*/
    }

    @Test
    public void importExcel() throws IOException {
        // 获取本地 Excel 文件输入流，并创建工作薄对象
       /* File file = new File("e:\\用户.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
        // 获取工作表
        HSSFSheet sheet = workbook.getSheet("用户信息");
        // 声明行对象
        HSSFRow row = null;
        //注意：获取数据 需排除标题行 从数据行开始读取
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            // 获取当前工作表中的数据行信息 数据行索引从 1 开始
            row = sheet.getRow(i);
            System.out.println((int)row.getCell(0).getNumericCellValue()+ " "  + row.getCell(1).getStringCellValue() + " "+ row.getCell(2).getDateCellValue());
        }*/

    }

    //java版发送消息到客户端
    @Test
    public void testGoEasy() {
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-317c21a7de5e43a79646b9c6255fd12b");
        goEasy.publish("my_channel", "Hello, GoEasy!!!!");
    }






}
