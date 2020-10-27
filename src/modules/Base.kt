package modules

import java.io.File
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.file.Files
import java.nio.file.Paths
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import java.util.zip.CRC32


fun ByteArray.b64decode(output: (ByteArray) -> Unit): ByteArray {
    val o: ByteArray = Base64.getDecoder().decode(this)
    output.invoke(o)
    return o
}

fun ByteArray.b64encode(output: (ByteArray) -> Unit): ByteArray {
    val o: ByteArray = Base64.getEncoder().encode(this)
    output.invoke(o)
    return o
}

fun ByteArray.string(output: (String) -> Unit): String {
    val o = String(this)
    output.invoke(String(this))
    return o
}

fun String.bytes(output: (ByteArray) -> Unit): ByteArray {
    val o = this.toByteArray()
    output.invoke(o)
    return o
}

fun Long.bytes(output: (ByteArray) -> Unit): ByteArray {
    val bytes = ByteArray(4)
    bytes[0] = (this shr 24).toByte()
    bytes[1] = (this shr 16).toByte()
    bytes[2] = (this shr 8).toByte()
    bytes[3] = (this shr 0).toByte()
    output.invoke(bytes)
    return bytes
}

fun String.file(output: (ByteArray) -> Unit): ByteArray {
    val bytes = Files.readAllBytes(Paths.get(this))
    output.invoke(bytes)
    return bytes
}

fun String.read(output: (ByteArray) -> Unit): ByteArray {
    val bytes = File(this).readBytes()
    output.invoke(bytes)
    return bytes
}

fun String.println() {
    println(this)
}

fun String.print() {
    print(this)
}

val String.print
    get() = print(this)

val String.println
    get() = println(this)

val String.bytes
    get() = this.toByteArray()

val ByteArray.string
    get() = String(this)

val ByteArray.b64decode: ByteArray
    get() = Base64.getDecoder().decode(this)

val ByteArray.b64encode: ByteArray
    get() = Base64.getEncoder().encode(this)

val String.md5: String
    get() = this.toByteArray().md5.hex

val String.sha1: String
    get() = this.toByteArray().sha1.hex

val String.sha256: String
    get() = this.toByteArray().sha256.hex

val ByteArray.md5: ByteArray
    get() {
        return try {
            val md5 = MessageDigest.getInstance("MD5")
            md5.digest(this)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            byteArrayOf()
        }
    }

val ByteArray.sha1: ByteArray
    get() {
        return try {
            val md5 = MessageDigest.getInstance("SHA-1")
            md5.digest(this)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            byteArrayOf()
        }
    }

val ByteArray.sha256: ByteArray
    get() {
        return try {
            val md5 = MessageDigest.getInstance("SHA-256")
            md5.digest(this)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            byteArrayOf()
        }
    }

val ByteArray.sha512: ByteArray
    get() {
        return try {
            val md5 = MessageDigest.getInstance("SHA-512")
            md5.digest(this)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            byteArrayOf()
        }
    }

val ByteArray.crc32: Long
    get() {
        val crc = CRC32()
        crc.update(this)
        return crc.value
    }

val Long.bytes: ByteArray
    get() {
        val bytes = ByteArray(4)
        bytes[0] = (this shr 24).toByte()
        bytes[1] = (this shr 16).toByte()
        bytes[2] = (this shr 8).toByte()
        bytes[3] = (this shr 0).toByte()
        return bytes
    }

val HEX_CHAR = arrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')

val ByteArray.hex: String
    get() = this.joinToString(separator = "") { "${HEX_CHAR[it.toInt().shr(4).and(0xf)]}${HEX_CHAR[it.toInt().and(0xf)]}" }

val String.file: ByteArray
    get() = Files.readAllBytes(Paths.get(this))

val String.read: ByteArray
    get() {
        val file = File(this)
        return file.readBytes()
    }

val String.urlencode: String
    get() = URLEncoder.encode(this, Charsets.UTF_8)

val String.urldecode: String
    get() = URLDecoder.decode(this, Charsets.UTF_8)