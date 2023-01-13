package com.pengjl.service;

import com.pengjl.utils.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    ResponseResult uploadImg(MultipartFile img);
}
