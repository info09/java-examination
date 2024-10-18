package examination.exam.service;

import examination.exam.dto.PageResponse;
import examination.exam.dto.categories.CategoryCreateRequest;
import examination.exam.dto.categories.CategoryDto;
import examination.exam.dto.categories.CategorySearch;
import examination.exam.entity.category_aggregate.Category;
import examination.exam.exception.AppException;
import examination.exam.exception.ErrorCode;
import examination.exam.entity.category_aggregate.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDto> getAllCategories(){
        var categories = categoryRepository.findAll();
        return categories.stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    public CategoryDto getCategoryById(String id){
        var category = categoryRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
        return category.getCategoryDto();
    }

    public CategoryDto getCategoryByName(String name){
        var category = categoryRepository.findByName(name).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
        return category.getCategoryDto();
    }

    public PageResponse<CategoryDto> getCategoriesPaging(CategorySearch search){
        Pageable pageable = PageRequest.of(search.getPageIndex() - 1, search.getPageSize());
        Category categoryExample = new Category();
        categoryExample.setName(search.getName());
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Category> example = Example.of(categoryExample, matcher);

        var list = categoryRepository.findAll(example, pageable);

        return PageResponse.<CategoryDto>builder()
                .currentPage(search.getPageIndex())
                .totalPage(list.getTotalPages())
                .pageSize(search.getPageSize())
                .totalElement(list.getTotalElements())
                .data(list.stream().map(Category::getCategoryDto).collect(Collectors.toList())).build();
    }

    public CategoryDto createCategory(CategoryCreateRequest request) {
        var category = Category.builder()
                .name(request.getName())
                .urlPath(request.getUrlPath())
                .build();

        category = categoryRepository.save(category);
        return category.getCategoryDto();
    }

    public CategoryDto updateCategory(String id, CategoryCreateRequest request) {
        var category = categoryRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));

        category.setName(request.getName());
        category.setUrlPath(request.getUrlPath());
        category = categoryRepository.save(category);

        return category.getCategoryDto();
    }

    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }
}
