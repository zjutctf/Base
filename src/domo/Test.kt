package domo
import modules.*

fun main() {

    val target = "F77219AD"

    "receipt.txt".read {
        val chars = "0123456789"
        for (c0 in chars) {
            for (c1 in chars) {
                for (c2 in chars) {
                    for (c3 in chars) {
                        it[157] = c0.toByte()
                        it[158] = c1.toByte()
                        it[159] = c2.toByte()
                        it[160] = c3.toByte()
                        if (it.crc32.bytes.hex == target.toLowerCase()) {
                            it.string.println
                            return@read
                        }
                    }
                }
            }
        }
    }
}

fun prefix(first: String): String {
    val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray()
    for (c0 in chars) {
        for (c1 in chars) {
            for (c2 in chars) {
                for (c3 in chars) {
                    if ("$first$c0$c1$c2$c3".bytes.sha256.hex.startsWith("00000")) {
                        "$first$c0$c1$c2$c3".println
                        "$c0$c1$c2$c3".println
                        return "$c0$c1$c2$c3"
                    }
                }
            }
        }
    }
    return ""
}