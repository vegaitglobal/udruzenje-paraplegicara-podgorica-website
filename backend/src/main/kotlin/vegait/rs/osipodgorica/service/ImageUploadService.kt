package vegait.rs.osipodgorica.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@Service
class ImageUploadService(
    @Value("\${app.file.upload-dir:./uploads}")
    var uploadDir: String,
) {
    val rootLocation: Path = Paths.get(uploadDir)

    // currently in case of failure in some cases the folder will remain behind but we could have a cron to delete empty folders
    fun store(file: MultipartFile, folderName: String): String {
        // file name format: timestamp_originalName
        val name = generateFileName(file)
        val destinationFile = generateDestinationPath(folderName, name)

        try {
            Files.createDirectories(this.rootLocation.resolve("$folderName/$name"))
            val inputStream: InputStream = file.inputStream
            Files.copy(inputStream, destinationFile.toAbsolutePath(), StandardCopyOption.REPLACE_EXISTING)
        } catch (e: IOException) {
            throw RuntimeException("There was an error uploading the file to $folderName/$name", e)
        }

        return destinationFile.toString()
    }

    private fun generateDestinationPath(folderName: String, name: String): Path {
        return this.rootLocation.resolve(Paths.get("$folderName/$name")).normalize()
    }

    private fun generateFileName(file: MultipartFile): String {
        return System.currentTimeMillis().toString() + "_" + file.originalFilename
    }

    fun deleteFile(path: String) {
        val file = File(path)
        if (file.exists()) {
            file.delete()
        }
    }

    fun deleteFolder(folderName: String) {
        val folder = File(this.rootLocation.resolve(folderName).toString())
        if (folder.exists()) {
            for (file in folder.listFiles()) {
                file.delete()
            }
            folder.delete()
        }
    }

    // in case of exception we will delete any files uploaded beforehand
    fun storeAll(newImages: List<MultipartFile>, folderName: String): List<String> {
        val storedUrls: MutableList<String> = mutableListOf()
        try {
            for (file in newImages) {
                val url = store(file, folderName)
                storedUrls.add(url)
            }
        } catch (ex: RuntimeException) {
            for (url in storedUrls) {
                deleteFile(url)
            }

            throw RuntimeException("There was an error uploading files to $folderName/", ex)
        }

        return storedUrls
    }
}
