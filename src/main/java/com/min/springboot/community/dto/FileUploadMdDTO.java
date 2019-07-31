package com.min.springboot.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadMdDTO {
    private Integer success;
    private String message;
    private String url;
}
