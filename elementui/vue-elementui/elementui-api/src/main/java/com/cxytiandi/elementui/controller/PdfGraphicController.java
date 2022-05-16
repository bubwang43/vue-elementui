package com.cxytiandi.elementui.controller;

import com.cxytiandi.elementui.entity.GraphicsEsEntity;
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

    @GetMapping("/graphics")
    public Map<String, Object> search(@RequestParam("field") String field, @RequestParam("key") String key,
                                         @RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("field", field);
        map.put("key", key);
        map.put("pageNumber", pageNumber);
        map.put("pageSize", pageSize);

        ResponseEntity<Map> responseEntity = restTemplate.getForEntity("http://127.0.0.1:8089/pdfgraphic/graphics?field={field}&key={key}&pageNumber={pageNumber}&pageSize={pageSize}", Map.class, map);
        return responseEntity.getBody();
    }

}
