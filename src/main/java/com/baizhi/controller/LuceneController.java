package com.baizhi.controller;

import com.baizhi.entity.Product;
import com.baizhi.service.LuceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/search")
public class LuceneController {
    @Autowired
    private LuceneService luceneService;

    @RequestMapping("/insertProduct")
    public void testInsertProduct(MultipartFile file1, HttpSession session, Product product) {
        System.out.println("product----------");
        String realPath = session.getServletContext().getRealPath("/");
        String originalFileName = file1.getOriginalFilename();
        String filename = UUID.randomUUID() + originalFileName;
        File file = new File(realPath + "/images/" + filename);
        try {
            file1.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setProductImg("/images/" + filename);
        product.setId(UUID.randomUUID().toString());
        System.out.println(product.toString());
        luceneService.testInsert(product);
    }


    @RequestMapping("/searchProduct")
    public List<Product> queryProduct(String param) {

        System.out.println(param);
        List<Product> list = luceneService.testSearcher(param);
        System.out.println("=========" + list);
        return list;
    }


}
