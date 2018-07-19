package pl.thor.app.util


import java.text.Normalizer
import java.util.regex.Pattern

object StringUtils {
    fun stripAccents(input: String?): String? {
        if (input == null) {
            return null
        }
        val pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")//$NON-NLS-1$
        val decomposed = StringBuilder(Normalizer.normalize(input, Normalizer.Form.NFD))
        convertRemainingAccentCharacters(decomposed)
        // Note that this doesn't correctly remove ligatures...
        return pattern.matcher(decomposed).replaceAll("")
    }

    private fun convertRemainingAccentCharacters(decomposed: StringBuilder) {
        for (i in 0 until decomposed.length) {
            if (decomposed[i] == '\u0141') {
                decomposed.deleteCharAt(i)
                decomposed.insert(i, 'L')
            } else if (decomposed[i] == '\u0142') {
                decomposed.deleteCharAt(i)
                decomposed.insert(i, 'l')
            }
        }
    }
}
