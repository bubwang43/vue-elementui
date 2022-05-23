package com.cxytiandi.elementui.controller;

import com.cxytiandi.elementui.entity.GraphicsEsEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/pdfgraphic")
public class PdfGraphicController {
    @Resource
    private RestTemplate restTemplate;

    @Value("${es.demo.hostport}")
    private String esdemoHostPort;


    @GetMapping("/graphics")
    public Map<String, Object> search(@RequestParam("field") String field, @RequestParam("key") String key,
                                         @RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("field", field);
        map.put("key", key);
        map.put("pageNumber", pageNumber);
        map.put("pageSize", pageSize);
        String url = esdemoHostPort + "/pdfgraphic/graphics?field={field}&key={key}&pageNumber={pageNumber}&pageSize={pageSize}";
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(url, Map.class, map);
        return responseEntity.getBody();
    }

    @GetMapping("/graphics/should")
    public Map<String, Object> search(@RequestParam("content") String content,
                                      @RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);
        map.put("pageNumber", pageNumber);
        map.put("pageSize", pageSize);
        String url = esdemoHostPort + "/pdfgraphic/graphics/should?content={content}&pageNumber={pageNumber}&pageSize={pageSize}";
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(url, Map.class, map);
        return responseEntity.getBody();
    }

}
