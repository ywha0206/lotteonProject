package com.lotteon.service.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
public class ImageService {

    @Value("${file.upload-dir}")
    private String uploadPath;

    private String upload(MultipartFile file) { //실질적 업로드 메서드

        String oName = file.getOriginalFilename();
        String ext = oName.substring(oName.lastIndexOf("."));
        String sName = UUID.randomUUID().toString() + ext;

        // 업로드 경로 설정
        File fileUploadPath = new File(uploadPath);
        // 파일 저장
        try {
            file.transferTo(new File(fileUploadPath, sName));
            return sName;
        } catch (IOException e) {
            log.error(e);
        }
        return null;
    }

    public String uploadImage(MultipartFile file) { // 단일 이미지 업로드

        if (!file.isEmpty()) {
            return upload(file);
        }
        return null;
    }

    public List<String> uploadImages(List<MultipartFile> files) { // 여러 이미지 업로드

        // 첨부 파일 정보 객체 리스트 생성
        List<String> uploadedFiles = new ArrayList<>();

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String path = upload(file);
                if(path != null) {
                    uploadedFiles.add(path);
                }
            }
        }
        log.info(uploadedFiles);
        return uploadedFiles;
    }
}
