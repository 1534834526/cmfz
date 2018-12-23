package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;

public interface AlbumService {
    List<Album> albumQueryAll();

    void addAlbum(Album album);

    void selectUpdateAlbum(String aid);
}
