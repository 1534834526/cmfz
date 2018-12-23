import com.baizhi.conf.ConvertFileSize;
import com.baizhi.entity.Banner;
import com.baizhi.mapper.BannerMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/*
@SpringBootTest(classes = AppStart.class)
@RunWith(SpringRunner.class)*/
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
        /*UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        String securityCode = ValidateImageCodeUtils.getSecurityCode();
        System.out.println(securityCode);
        */

        File file1 = new File("D:/images/1-1.png");
     /*   if(file1.exists()) {
            FileInputStream fis = new FileInputStream(file1);
            System.out.println(fis.available());
        }*/

        String s = ConvertFileSize.convertFileSize(file1.length());
        System.out.println(s);
    }


    @Test
    public void test3() throws ParseException {
       /* int seconds=540;
        int temp=0;
        StringBuffer sb=new StringBuffer();
        temp = seconds/3600;
        sb.append((temp<10)?"0"+temp+":":""+temp+":");

        temp=seconds%3600/60;
        sb.append((temp<10)?"0"+temp+":":""+temp+":");

        temp=seconds%3600%60;
        sb.append((temp<10)?"0"+temp:""+temp);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = dateFormat.parse(sb.toString());
         System.out.println(date);*/
    }

    @Test
    public void test4(){

    }



}
