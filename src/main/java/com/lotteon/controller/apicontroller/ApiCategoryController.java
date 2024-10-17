package com.lotteon.controller.apicontroller;

import com.lotteon.dto.responseDto.GetCategoryDto;
import com.lotteon.service.category.CategoryProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ApiCategoryController {

    private final CategoryProductService categoryProductService;

    @GetMapping("/categories")
    public ResponseEntity<?> getCate(
            @RequestParam Long id
    ){
        System.out.println(id);
        List<GetCategoryDto> cates =categoryProductService.findCategory2(id);

        Map<String,Object> cates2 = categoryProductService.findCategory3(id);

        Map<String, Object> map = new HashMap<>();
        map.put("cates", cates);
        map.put("cates2", cates2);
        return ResponseEntity.ok(map);
    }
}
