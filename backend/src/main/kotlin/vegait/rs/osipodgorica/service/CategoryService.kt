package vegait.rs.osipodgorica.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vegait.rs.osipodgorica.dto.CreateCategoryRequest
import vegait.rs.osipodgorica.dto.UpdateCategoryRequest
import vegait.rs.osipodgorica.model.Category
import vegait.rs.osipodgorica.repository.CategoryRepository

@Service
@Transactional
class CategoryService(
    val categoryRepo: CategoryRepository,
    val uploadService: ImageUploadService,
    val imageUploadService: ImageUploadService,
) {

    fun store(request: CreateCategoryRequest): Category {
        val category = categoryRepo.save(Category(name = request.name))

        val imagePath = uploadService.store(request.thumbnail, "categories/" + category.id)
        category.relativeUrl = imagePath

        return categoryRepo.save(category)
    }

    fun update(request: UpdateCategoryRequest, id: Long) =
        Category(
            id = id,
            name = request.name,
            relativeUrl = request.relativeUrl,
        ).let { newCategory ->
            categoryRepo.save(newCategory)
        }

    fun get(id: Long): Category {
        return categoryRepo.findById(id).orElseThrow()
    }

    fun index(): List<Category> {
        return categoryRepo.findAll()
    }

    fun delete(id: Long) = runCatching {
        categoryRepo.deleteById(id)
    }.mapCatching {
//        imageUploadService.d
    }
}
