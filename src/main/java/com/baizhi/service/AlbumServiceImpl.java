package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.mapper.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class AlbumServiceImpl implements  AlbumService {
    @Autowired
    private AlbumMapper albumMapper;

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public List<Album> albumQueryAll() {
         List<Album> albums=albumMapper.albumQueryAll();
        System.out.println("albums:"+albums);
         return albums;
    }

    @Override
    public void addAlbum(Album album) {
        albumMapper.insert(album);
    }

    @Override
    public void selectUpdateAlbum(String aid) {
        Album album = albumMapper.selectByPrimaryKey(aid);
        album.setCount(album.getCount()+1);
        albumMapper.updateByPrimaryKey(album);
    }
}
