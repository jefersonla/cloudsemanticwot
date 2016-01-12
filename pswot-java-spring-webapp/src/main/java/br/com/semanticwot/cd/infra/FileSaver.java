package br.com.semanticwot.cd.infra;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Component
public class FileSaver { // Essa classe grava um arquivo no path da aplicação

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AmazonS3Client s3;

    public String writeLocal(String baseFolder, MultipartFile multipartFile) {
        String realPath = request.getServletContext().getRealPath("/"
                + baseFolder);
        try {
            new File(realPath).mkdir();
            String path = realPath + "/" + multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File(path));
            System.out.println(realPath + "/" + multipartFile.
                    getOriginalFilename());
            return baseFolder + "/" + multipartFile.getOriginalFilename();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String writeRemoteNinja(MultipartFile multipartFile) {
        try {
            s3.putObject("casadocodigo", multipartFile.getOriginalFilename(),
                    multipartFile.getInputStream(), new ObjectMetadata());
            return "http://localhost:9444/s3/casadocodigo/" + multipartFile.
                    getOriginalFilename() + "?noAuth=true";
        } catch (AmazonClientException | IOException e) {
            throw new RuntimeException(e);
        }

    }
    
    public String writeRemoteAmazon(MultipartFile multipartFile) {
        try {
            s3.putObject("casadocodigo", multipartFile.getOriginalFilename(),
                    multipartFile.getInputStream(), new ObjectMetadata());
            return "http://s3.amazonaws.com/casadocodigo/" + multipartFile.
                    getOriginalFilename();
        } catch (AmazonClientException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}
