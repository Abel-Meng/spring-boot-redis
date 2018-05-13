package com.mh.redis.domin;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 孟浩
 * @date 2018/5/10  14:29.
 */
@Data
public class User implements Serializable{

    private static final long serialVersionUID = -709707413614457251L;

    private String name;
    private String age;
    private String sex;
    private String address;
}
