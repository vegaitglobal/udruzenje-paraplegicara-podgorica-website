package vegait.rs.osipodgorica.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
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
        runCatching {
            categoryRepo.findById(id).get()
        }.mapCatching { oldCategory ->
            Category(
                id = id,
                name = oldCategory.name,
                relativeUrl = request.thumbnail?.storeNewIcon(id) ?: oldCategory.relativeUrl,
            )
        }.mapCatching { category ->
            categoryRepo.save(category)
        }

    private fun MultipartFile.storeNewIcon(id: Long): String? =
        runCatching {
            imageUploadService.deleteFile("categories/$id")
        }.mapCatching {
            imageUploadService.store(this, "categories/$id")
        }.getOrNull()

    fun get(id: Long): Category {
        return categoryRepo.findById(id).orElseThrow()
    }

    fun index(): List<Category> {
        return categoryRepo.findAll()
    }

    fun delete(id: Long) {
        runCatching {
            categoryRepo.deleteById(id)
        }.mapCatching {
            imageUploadService.deleteFolder("categories/$id")
        }
    }
}
