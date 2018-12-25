package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/export")

public class ExportController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/exportAllAlbum")
    public void exportAllAlbum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Album> albums = albumService.albumQueryAll();

        for (int i = 0; i < albums.size(); i++) {

            String musicUrl = albums.get(i).getUrl();
            String musicUrl2 = request.getSession().getServletContext().getRealPath("/") + musicUrl;
            albums.get(i).setUrl(musicUrl2);
        }

        //方法2
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("所有专辑", "韩高明"),
                Album.class, albums);
        workbook.createCellStyle().setAlignment(HSSFCellStyle.ALIGN_CENTER);
        response.setContentType("application/vnd.ms-excel; charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=aa.xls");
        response.setCharacterEncoding("utf-8");
        OutputStream os = response.getOutputStream();
        workbook.write(os);
        os.flush();


    }
}
