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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.UUID;

@Controller
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/addChapter")

    public void addChapter(MultipartFile file1, HttpSession session, Chapter chapter,String aid) throws IOException, TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, ParseException {

        //创建文件上传保存的位置realPath=../music
        String realPath = session.getServletContext().getRealPath("/music");
        File file2 = new File(realPath);
        if (!file2.exists()) {
            file2.mkdir();
        }

        //时间戳或UUID+1.mp3
        String filename = file1.getOriginalFilename();
        UUID uuid = UUID.randomUUID();
        /*Date date1 = new Date();
        long time1 = date1.getTime();*/

        //上传文件
        File file3 = new File(realPath + "/" + uuid + filename);
        file1.transferTo(file3);

        //音频文件时长的转换（拿到音频文件----获得秒--转成时分秒格式的日期）

        //音频文件file3（拿到上传的文件位置）
        MP3File mp3File = (MP3File) AudioFileIO.read(file3);
        MP3AudioHeader audioHeader = (MP3AudioHeader) mp3File.getAudioHeader();
        // 单位为秒
        int time = audioHeader.getTrackLength();
        //转成时分秒日期--再转成字符串存入数据库
        String date = TimeConvertMinute.getDate(time).toString();


        //保存数据库
        chapter.setId(ValidateImageCodeUtils.getSecurityCode());
        chapter.setDuration(date);
        chapter.setUrl(uuid + filename);
        String s = ConvertFileSize.convertFileSize(file1.getSize());
        chapter.setSize(s);
        chapter.setAid(aid);

        chapterService.addChapter(chapter);
        albumService.selectUpdateAlbum(aid);
    }


    @RequestMapping(value = "/downloadChapter", method = RequestMethod.GET)
    public void downloadChapter(String musicUrl, String title, HttpSession session, HttpServletResponse response) throws IOException {
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("/music/");

        File file = new File(realPath + musicUrl);

        //数据库中截取后的音频名  时间戳，但高并发会有问题,使用uuid
        String musicUrl2 = musicUrl.substring(36);

        byte[] bytes = FileUtils.readFileToByteArray(file);
        //通过响应流响应到客户端，响应之前设置响应头

        response.setContentType("audio/mpeg");
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(musicUrl2, "UTF-8"));
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
