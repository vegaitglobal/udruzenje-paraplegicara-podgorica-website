package vegait.rs.osipodgorica.utils

import java.text.Normalizer
import java.util.Locale
import java.util.regex.Pattern

object SlugGenerator {
	val NON_LATIN: Pattern = Pattern.compile("[^\\w-]")
	val WHITESPACE: Pattern = Pattern.compile("[\\s]")

	fun generateSlug(word: String): String {
		val noWhitespace = WHITESPACE.matcher(word).replaceAll("-")
		val normalized = Normalizer.normalize(noWhitespace, Normalizer.Form.NFD)
		val slug = NON_LATIN.matcher(normalized).replaceAll("")
		return slug.lowercase(Locale.ENGLISH)
	}
}