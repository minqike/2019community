package com.min.springboot.community.dto;

import lombok.Data;

@Data
public class GithubUser {
    private  Long id;
    private  String name;
    private  String bio;
}