package com.company.service;

import com.company.dto.AttachDTO;
import com.company.entity.AttachEntity;
import com.company.exps.ItemNotFoundEseption;
import com.company.repository.AttachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class AttachService {
    @Autowired
    private AttachRepository attachRepository;

    @Value("${attach.folder}")
    private String attachFolder;
    @Value("${server.url}")
    private String serverUrl;



    public AttachDTO saveToSystem(MultipartFile file) {
        try {
            // zari.jpg
            String pathFolder = getYmDString(); // 2022/06/20
            String uuid = UUID.randomUUID().toString(); //  asdas-dasdas-dasdasd-adadsd
            String extension = getExtension(file.getOriginalFilename()); // jpg

            String fileName = uuid + "." + extension; //  asdas-dasdas-dasdasd-adadsd.jpg


            File folder = new File(attachFolder + pathFolder); // attaches/2022/06/20
            if (!folder.exists()) {
                folder.mkdirs();
            }
            byte[] bytes = file.getBytes();
            // attaches/2022/06/20/asdas-dasdasd-asdas0asdas.jpg
            Path path = Paths.get(attachFolder + pathFolder + "/" + fileName);
            Files.write(path, bytes);

            AttachEntity entity = new AttachEntity();
            entity.setId(uuid);
            entity.setExtention(extension);
            entity.setOriginName(file.getOriginalFilename());
            entity.setSize(file.getSize());
            entity.setPath(pathFolder);
            attachRepository.save(entity);

            AttachDTO dto = new AttachDTO();
            dto.setUrl(serverUrl + "attach/open/" + entity.getId());
            // http://localhost:8081/attach/open/8bd17c91-c1ac-494c-800d-dffd61307ef5
            return dto;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public byte[] loadImage(String id) {
        byte[] imageInByte;
        String path = getFileFullPath(get(id));

        BufferedImage originalImage;
        try {
            originalImage = ImageIO.read(new File(path));
        } catch (Exception e) {
            return new byte[0];
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(originalImage, "png", baos);
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imageInByte;
    }

    public byte[] open_general(String id) {
        byte[] data;
        try {
            // fileName -> zari.jpg
            String path = getFileFullPath(get(id));
            Path file = Paths.get(path);
            data = Files.readAllBytes(file);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public ResponseEntity<Resource> download(String id) {
        try {
            AttachEntity entity = get(id);
            String path = getFileFullPath(entity);
            Path file = Paths.get(path);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + entity.getOriginName() + "\"").body(resource);
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public String delete(String id) {
        Optional<AttachEntity> byId = attachRepository.findById(id);
        if (!byId.isPresent()) {
            throw new ItemNotFoundEseption("Mazgimisan bunaqa attach yo'q");
        }
        AttachEntity entity = byId.get();
        String path = getFileFullPath(entity);

        try {
            Files.delete(FileSystems.getDefault().getPath(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        attachRepository.deleteById(entity.getId());
        return "successfully deleted";
    }


    public String getExtension(String fileName) { // mp3/jpg/npg/mp4.....
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex + 1);
    }

    public String getYmDString() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DATE);

        return year + "/" + month + "/" + day; // 2022/04/23
    }


    private AttachEntity get(String id) {
        return attachRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundEseption("Attach Not Found");
        });
    }

    private String getFileFullPath(AttachEntity entity) {
        return attachFolder + entity.getPath() + "/" + entity.getId() + "." + entity.getExtention();
    }

    public   PageImpl<AttachDTO> pagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AttachEntity> attachPage = attachRepository.pagination(pageable);
        List<AttachDTO> dtoList = new LinkedList<>();
        attachPage.getContent().forEach(attach -> {
            dtoList.add(entityToDto(attach));
        });
        return  new PageImpl(dtoList,pageable, attachPage.getTotalElements());
  }

    public   AttachDTO entityToDto(AttachEntity entity){
        AttachDTO dto = new AttachDTO();
        dto.setId(entity.getId());
        dto.setSize(entity.getSize());
        dto.setUrl(serverUrl + "attach/open/" + entity.getId());
        dto.setOriginalName(entity.getOriginName());
        return  dto;
    }
    public   AttachDTO getAttach(String id){

        AttachDTO dto = new AttachDTO();
        dto.setId(id);
        dto.setUrl(serverUrl + "attach/open/" + id);

        return  dto;
    }

}
