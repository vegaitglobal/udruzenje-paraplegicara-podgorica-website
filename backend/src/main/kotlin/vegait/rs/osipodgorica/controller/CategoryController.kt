package vegait.rs.osipodgorica.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import vegait.rs.osipodgorica.dto.CreateCategoryRequest
import vegait.rs.osipodgorica.model.Category
import vegait.rs.osipodgorica.service.CategoryService

@RestController
@RequestMapping("/api/v1/categories")
@Transactional
class CategoryController(val categoryService: CategoryService) {

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun store(@ModelAttribute request: CreateCategoryRequest): Category {
        return categoryService.store(request)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id : Long): Category {
        return categoryService.get(id)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        return categoryService.delete(id)
    }

    @GetMapping
    fun index(): List<Category> {
        return categoryService.index()
    }
}