package com.labprog.labprog.services;

import com.labprog.labprog.DTO.ProductSkuForm;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;

@Service
public class UploadImageService {

    String apiKey = "a5c607b8a43586db5e6f2b79d84c816b";
    String uploadUrl = "https://api.imgbb.com/1/upload?key=" + apiKey;

    public String saveImage(ProductSkuForm form) throws IOException {

        try {

            MultipartFile file = form.getImage();

            if (file.isEmpty()) {
                return null;
            }

            String base64Image = Base64.getEncoder().encodeToString(file.getBytes());

            MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
            requestBody.add("image", base64Image);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> response = restTemplate.postForEntity(uploadUrl, requestEntity, Map.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                Map<String, Object> data = (Map<String, Object>) Objects.requireNonNull(response.getBody()).get("data");
                System.out.println((String) data.get("url"));
                return (String) data.get("url");
            } else {
                throw new RuntimeException("Falha ao enviar imagem para ImgBB");
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar a imagem: " + e.getMessage());
        }
    }
}
