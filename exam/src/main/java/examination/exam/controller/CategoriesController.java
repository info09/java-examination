package examination.exam.controller;

import examination.exam.dto.ApiResponse;
import examination.exam.dto.PageResponse;
import examination.exam.dto.categories.CategoryCreateRequest;
import examination.exam.dto.categories.CategoryDto;
import examination.exam.dto.categories.CategorySearch;
import examination.exam.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoriesController {
    private final CategoryService categoryService;

    /**
     * Retrieve a list of all categories.
     *
     * @return A list of categories.
     */
    @GetMapping("/list")
     ApiResponse<List<CategoryDto>> getCategories() {
        return ApiResponse.<List<CategoryDto>>builder().result(categoryService.getAllCategories()).build();
    }


    /**
     * Retrieve a list of categories in a paged manner.
     *
     * @param name The name of the category to search.
     * @param page The page number of the category list to be retrieved.
     * @param size The number of categories to include in the page.
     * @return A paged list of categories that matches the search criteria.
     */
    @GetMapping("/paging")
    ApiResponse<PageResponse<CategoryDto>> getCategoriesPaging( @RequestParam(required = false) String name,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        var search = CategorySearch.builder()
                .name(name)
                .pageIndex(page)
                .pageSize(size)
                .build();

        return ApiResponse.<PageResponse<CategoryDto>>builder().result(categoryService.getCategoriesPaging(search)).build();
    }

    /**
     * Retrieve a category by its ID.
     *
     * @param id The ID of the category to be retrieved.
     * @return The category with the given ID.
     */
    @GetMapping("/{id}")
    ApiResponse<CategoryDto> getCategoryById(@PathVariable String id) {
        // Retrieve the category by its ID
        var category = categoryService.getCategoryById(id);

        // Return the category in the API response
        return ApiResponse.<CategoryDto>builder().result(category).build();
    }

    /**
     * Creates a new category with the given details.
     *
     * @param request The request containing the details of the category to be created.
     * @return The created category.
     */
    @PostMapping("")
    ApiResponse<CategoryDto> createPost(@RequestBody CategoryCreateRequest request) {
        return ApiResponse.<CategoryDto>builder().result(categoryService.createCategory(request)).build();
    }

    /**
     * Update a category with the given ID and details.
     *
     * @param id The ID of the category to be updated.
     * @param request The request containing the updated details of the category.
     * @return The updated category.
     */
    @PutMapping("/{id}")
    ApiResponse<CategoryDto> updatePost(@PathVariable String id, @RequestBody CategoryCreateRequest request) {
        return ApiResponse.<CategoryDto>builder().result(categoryService.updateCategory(id, request)).build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deletePost(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return ApiResponse.<Void>builder().build();
    }
}
