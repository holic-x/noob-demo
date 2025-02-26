import com.noob.base.DemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@SpringBootTest(classes = DemoApplication.class)
public class FileUploadControllerTest {

    private static final String UPLOAD_URL = "http://localhost:8080/api/upload/chunk"; // 分片上传请求URL
    private static final String MERGE_URL = "http://localhost:8080/api/upload/merge"; // 分片合并请求URL

    // 模拟分片上传
    @Test
    public void testChunkUpload() throws IOException {
        // 源文件处理
        String sourcePath = "D:\\Desktop\\test\\upload\\";
        String fileName = "largefile.zip";
        File file = new File(sourcePath + fileName); // 要上传的大文件
        String fileMd5 = UUID.randomUUID().toString(); // 文件的唯一标识
        int chunkSize = 1024 * 1024; // 每个分片的大小（1MB）
        byte[] buffer = new byte[chunkSize];
        int chunks = (int) Math.ceil(file.length() / (double) chunkSize);

        // 基于RestTemplate（访问RESTful Web服务的客户端工具）处理HTTP请求和响应
        RestTemplate restTemplate = new RestTemplate();

        // 处理大文件（构建分片）
        try (FileInputStream fis = new FileInputStream(file)) {
            for (int i = 0; i < chunks; i++) {
                int bytesRead = fis.read(buffer);
                if (bytesRead == -1) break;

                // 创建临时分片文件
                File chunkFile = File.createTempFile("chunk", ".part");
                try (FileOutputStream fos = new FileOutputStream(chunkFile)) {
                    fos.write(buffer, 0, bytesRead);
                }

                // 构建分片上传请求
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.MULTIPART_FORM_DATA);

                MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
                body.add("file", new FileSystemResource(chunkFile));
                body.add("chunk", i);
                body.add("chunks", chunks);
                body.add("fileName", file.getName());
                body.add("fileMd5", fileMd5);

                HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

                // 上传分片
                ResponseEntity<String> response = restTemplate.postForEntity(UPLOAD_URL, requestEntity, String.class);
                System.out.println("Chunk " + i + " uploaded: " + response.getBody());

                // 删除临时分片文件
                chunkFile.delete();
            }
        }
        System.out.println("fileName:" + file.getName());
        System.out.println("fileMd5:" + fileMd5);

    }


    // 模拟分片合并（根据文件分片索引、文件名称）
    @Test
    public void testChunkMerge() throws IOException {
        // 定义文件名称和分片索引
        String fileName = "largefile-merge.zip";
        String fileMd5 = "dcdafca8-106a-4fb4-a5b5-6dd576f3c34b";

        // 基于RestTemplate（访问RESTful Web服务的客户端工具）处理HTTP请求和响应
        RestTemplate restTemplate = new RestTemplate();

        // 合并分片
        MultiValueMap<String, Object> mergeBody = new LinkedMultiValueMap<>();
        mergeBody.add("fileName", fileName);
        mergeBody.add("fileMd5", fileMd5);

        HttpEntity<MultiValueMap<String, Object>> mergeRequestEntity = new HttpEntity<>(mergeBody, null);
        ResponseEntity<String> mergeResponse = restTemplate.postForEntity(MERGE_URL, mergeRequestEntity, String.class);
        System.out.println("File merged: " + mergeResponse.getBody());
    }

}