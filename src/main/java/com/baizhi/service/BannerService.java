package com.baizhi.service;

import com.baizhi.entity.Banner;
import com.baizhi.entity.BannerDto;
import com.baizhi.entity.ParamDto;

public interface BannerService {
    BannerDto bannerQueryAll(Integer page, Integer rows);

    void addBanner(Banner banner);

    void deleteBanner(ParamDto dto);

    void update(Banner banner);
}
