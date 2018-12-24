package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;

/**
 * @author HGM
 */
@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/albumQueryAll")
    public List<Album> albumQueryAll(){
        return albumService.albumQueryAll();
    }

    @RequestMapping("/addAlbum")
    public void addAlbum(MultipartFile file1, HttpSession session, Album album) throws IOException {
        System.out.println("file1" + file1);
        String realPath = session.getServletContext().getRealPath("/images");
        String filename = file1.getOriginalFilename();
        UUID uuid = randomUUID();
        File file = new File(realPath + "/" + uuid + filename);
        file1.transferTo(file);
        album.setUrl("/images/" + uuid + filename);
        album.setCount(0);
        albumService.addAlbum(album);
    }
}
