import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create()
                    .setDefaultRequestConfig(RequestConfig.custom()
                            .setConnectTimeout(5000)
                            .setSocketTimeout(30000)
                            .setRedirectsEnabled(false)
                            .build())
                    .build();

            HttpGet request = new HttpGet("https://api.nasa.gov/planetary/apod?api_key=Jz3LjrImddJiMm4v31UtjkGM8pnnvBefikxkODfJ");
            CloseableHttpResponse response = httpClient.execute(request);

            ObjectMapper objectMapper = new ObjectMapper();
            NasaApiResponse apiResponse = objectMapper.readValue(response.getEntity().getContent(), NasaApiResponse.class);

            String imageUrl = apiResponse.getUrl();
            HttpGet imageRequest = new HttpGet(imageUrl);
            CloseableHttpResponse imageResponse = httpClient.execute(imageRequest);

            Path tempFile = Files.createTempFile("apod-", null);
            String fileName = tempFile.getFileName().toString();

            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            imageResponse.getEntity().writeTo(fileOutputStream);

            fileOutputStream.close();
            imageResponse.close();
            response.close();
            httpClient.close();

            System.out.println("Файл сохранен: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
