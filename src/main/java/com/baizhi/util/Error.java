package com.baizhi.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {
    public Error(String errmsg) {
        errmsg = this.errmsg;
    }

    private String msg;
    private String errmsg;
}
