package com.example.demo.converte;

import com.example.demo.email.Email;
import com.example.demo.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@Component
public class MultipartFileToString implements Converter<MultipartFile,String> {

    static{
        String user_home = System.getProperty("user.home");
        String separator = System.getProperty("file.separator");
        File path = new File(user_home+separator+"upload_file");
        if(!path.exists()) if(!path.mkdirs()) throw new RuntimeException("无法创建文件上传临时目录");
        System.setProperty("upload.path",path.getPath());
    }

    @Value("${upload.path}")
    private String upload_path;
    @Value("${file.separator}")
    private String separator;

    @Override
    public String convert(MultipartFile multipartFile) {
        File ff = new File(upload_path+separator+multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(ff);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return ff.getPath();
    }
}
