package com.baizhi.service;

import com.baizhi.entity.Banner;
import com.baizhi.entity.BannerDto;
import com.baizhi.entity.ParamDto;
import com.baizhi.mapper.BannerMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public BannerDto bannerQueryAll(Integer page, Integer rows) {
        BannerDto dto = new BannerDto();
        //pagehelper插件的分页  需导入jar包
        PageHelper.startPage(page,rows);
        List<Banner> banners = bannerMapper.selectAll();
        int i = bannerMapper.selectCount(new Banner());
        dto.setRows(banners);
        dto.setTotal(i);
        System.out.println("12345678912345678");

        //通用mapper的rowbounds方式分页
        /* BannerDto dto = new BannerDto();
        int i = bannerMapper.selectCount(new Banner());
        dto.setTotal(i);
        List<Banner> banners = bannerMapper.selectByRowBounds(new Banner(), new RowBounds((page - 1) * rows, rows));
        dto.setRows(banners);*/

        return dto;
    }


    @Override
    public void addBanner(Banner banner) {
        bannerMapper.insert(banner);
    }

    @Override
    public void deleteBanner(ParamDto dto) {
        List<Integer> ids = dto.getIds();
        Example example = new Example(Banner.class);
        example.createCriteria().andIn("id", ids);
        bannerMapper.deleteByExample(example);


    }

    @Override
    public void update(Banner banner) {
        bannerMapper.updateByPrimaryKey(banner);
        //bannerMapper.updateByPrimaryKeySelective(banner);
    }
}
