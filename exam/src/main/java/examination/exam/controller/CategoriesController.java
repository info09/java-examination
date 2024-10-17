package examination.exam.controller;

import examination.exam.dto.ApiResponse;
import examination.exam.dto.PageResponse;
import examination.exam.dto.categories.CategoryDto;
import examination.exam.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoryService categoryService;

//    @GetMapping("/paging")
//    ApiResponse<PageResponse<CategoryDto>> GetCategoriesPaging(){
//        return ApiResponse.<PageResponse<CategoryDto>>builder().build();
//    }
}
