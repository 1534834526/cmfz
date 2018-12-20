import com.baizhi.AppStart;
import com.baizhi.entity.Banner;
import com.baizhi.mapper.BannerMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;

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
}
