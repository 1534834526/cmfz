package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.entity.BannerDto;
import com.baizhi.entity.ParamDto;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;

@RestController
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("/bannerQuery")
    public BannerDto bannerQueryAll(Integer page, Integer rows) {
        BannerDto dto = bannerService.bannerQueryAll(page, rows);
        return dto;
    }

    @RequestMapping("/addBanner")
    public void addBanner(HttpSession session, MultipartFile file1, Banner banner) throws Exception {
        String realPath = session.getServletContext().getRealPath("/images");
        System.out.println("file1" + file1);
        String filename = file1.getOriginalFilename();
        File file = new File(realPath + "/" + filename);
        file1.transferTo(file);
        banner.setImgPath("/images/" + filename);
        bannerService.addBanner(banner);


    }

    @RequestMapping("/deleteBanner")
    public void deleteBanner(ParamDto dto) {
        System.out.println("dto:" + dto);
        bannerService.deleteBanner(dto);
    }

    @RequestMapping("/updateBanner")
    public void deleteBanner(Banner banner) {

        bannerService.update(banner);
    }
}
