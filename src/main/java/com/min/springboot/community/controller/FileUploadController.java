package com.min.springboot.community.controller;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.min.springboot.community.dto.FileUploadMdDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class FileUploadController {

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;

    @PostMapping("file/upload")
    @ResponseBody
    public FileUploadMdDTO uplaod(@RequestParam("editormd-image-file") MultipartFile file) throws FileNotFoundException {
        if (file.isEmpty()) {
            return new FileUploadMdDTO(0, "上传失败，请选择文件", "");
        }
        //获取文件保存的绝对目录
        String filePath = uploadFolder;
        //获取文件后缀
        String fileExt;
        String fileName = file.getOriginalFilename();
        String[] filesplit = fileName.split("\\.");
        if (filesplit.length>1){
            fileExt = filesplit[filesplit.length - 1];
        }else{
            return new FileUploadMdDTO(0, "上传失败，文件名有问题", "");
        }
        //根据hutool工具类雪花算法获取作为新文件名
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        long id = snowflake.nextId();
        //文件名+后缀
        fileName= id + "." + fileExt;
        File dest = new File(filePath + fileName);
        try {
            //保存文件
            file.transferTo(dest);
            //返回json结果
            return new FileUploadMdDTO(1, "上传成功", staticAccessPath +fileName);
        } catch (IOException e) {
            return new FileUploadMdDTO(0, "上传失败，请选择文件", "");
        }
    }
}
