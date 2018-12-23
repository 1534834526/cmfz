package com.baizhi.controller;

import com.baizhi.conf.ConvertFileSize;
import com.baizhi.conf.TimeConvertMinute;
import com.baizhi.conf.ValidateImageCodeUtils;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.FileUtils;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.Date;

@Controller
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/addChapter")

    public void addChapter(MultipartFile file1, HttpSession session, Chapter chapter,String aid) throws IOException, TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, ParseException {

        String realPath = session.getServletContext().getRealPath("/music");
        String filename = file1.getOriginalFilename();
        File file = new File(realPath + "/" + filename);
        file1.transferTo(file);


        //音频文件时长的转换（拿到音频文件----获得秒--转成时分秒格式的日期）
        MP3File mp3File = (MP3File) AudioFileIO.read(file);
        MP3AudioHeader audioHeader = (MP3AudioHeader) mp3File.getAudioHeader();

        // 单位为秒
        int time = audioHeader.getTrackLength();
        Date date = TimeConvertMinute.getDate(time);
        chapter.setDuration(date);
        chapter.setUrl("/music/" + filename);
        String s = ConvertFileSize.convertFileSize(file1.getSize());
        chapter.setSize(s);
        chapter.setAid(aid);
        chapter.setId(ValidateImageCodeUtils.getSecurityCode());
        chapterService.addChapter(chapter);
        albumService.selectUpdateAlbum(aid);
    }

    @RequestMapping("/downloadChapter")
    public void downloadChapter(String musicUrl, HttpSession session, HttpServletResponse response) throws IOException {
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("\\");
        //替换为
        String replace = musicUrl.replace("/", "\\");
        System.out.println(realPath + replace);
        File file = new File(realPath + musicUrl);
        byte[] bytes = FileUtils.readFileToByteArray(file);
        //通过响应流响应到客户端，响应之前设置响应头
        response.setContentType(String.valueOf(MediaType.APPLICATION_OCTET_STREAM));
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(musicUrl, "UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        if (outputStream != null) {
            outputStream.flush();
        }
        if (outputStream != null) {
            outputStream.close();
        }
    }





}
