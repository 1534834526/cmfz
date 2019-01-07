package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
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
        response.setContentType("application/vnd.ms-excel; charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=aa.xls");
        response.setCharacterEncoding("utf-8");
        OutputStream os = response.getOutputStream();
        workbook.write(os);
        os.flush();


    }


    @RequestMapping("/importAllAlbum")
    public void importExcel() throws IOException {

        // 获取本地 Excel 文件输入流，并创建工作薄对象
        File file = new File("e:\\用户.xls");
        if (!file.exists()) {
            file.mkdir();
        }
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
        // 获取工作表
        HSSFSheet sheet = workbook.getSheet("用户信息");
        // 声明行对象
        HSSFRow row = null;
        //注意：获取数据 需排除标题行 从数据行开始读取
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            // 获取当前工作表中的数据行信息 数据行索引从 1 开始
            row = sheet.getRow(i);
            System.out.println((int) row.getCell(0).getNumericCellValue() + " " + row.getCell(1).getStringCellValue() + " " + row.getCell(2).getDateCellValue());

        }
    }


}
