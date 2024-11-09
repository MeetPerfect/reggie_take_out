package com.itheima.reggie.Controller;

/**
 * ClassName: CommonController
 * Package: com.itheima.reggie.Controller
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2024/11/9 20:54
 * @Version 1.0
 */

import com.itheima.reggie.Common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * 文件上传和下载
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Value("${reggie.path}")
    private String basePath;
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) {

        // file是一个临时文件，需要转存到指定位置，否则本次请求完成后临时文件会删除
        log.info("文件上传, {}", file.toString());

        // 获得原始文件名
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        // 使用UUID重新生成文件名, 防止文件名重复
        String fileName = UUID.randomUUID().toString() + suffix;

        // 判断当前文件是否存在
        File dir = new File(basePath);
        if(!dir.exists()) {
            dir.mkdirs();
        }

        try {
            // 将临时文件转存到指定位置
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e){
            e.printStackTrace();
        }

        return R.success(fileName);
    }

    /**
     * 文件下载
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {

        try {
            // 通过输入流，读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
            // 输出流，通过输出流将文件写回到浏览器
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }

            // 关闭资源
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
