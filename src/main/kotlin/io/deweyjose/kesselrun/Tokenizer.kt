package io.deweyjose.kesselrun

import java.io.InputStreamReader

enum class Token {
    TOK_EOF, TOK_DEF, TOK_EXTERN, TOK_IDENTIFIER, TOK_NUMBER, TOK_OP
}

class Tokenizer(private val reader: InputStreamReader) {

    private var currentToken: Token = Token.TOK_EOF

    fun getToken(): Token = currentToken

    fun getNextToken(): Token {
        currentToken = getTok()
        return currentToken
    }

    private fun getTok(): Token {
        var lastChar = reader.read().toChar()
        if (lastChar.toInt() == -1)
            return Token.TOK_EOF

        while (lastChar.isWhitespace()) {
            lastChar = reader.read().toChar()
            if (lastChar.toInt() == -1)
                return Token.TOK_EOF
        }

        if (lastChar.isLetter()) {
            var identifierString = ""

            while (lastChar.isLetterOrDigit()) {
                identifierString += lastChar.toString()
                lastChar = reader.read().toChar()
                if (lastChar.toInt() == -1)
                    return Token.TOK_EOF
            }

            println("identifier string $identifierString")

            return when (identifierString) {
                "def" -> Token.TOK_DEF
                "extern" -> Token.TOK_EXTERN
                else -> Token.TOK_IDENTIFIER
            }
        }

        if (lastChar.isDigitOrDecimalPoint()) {
            var numStr = ""

            do {
                numStr += lastChar
                lastChar = reader.read().toChar()
                if (lastChar.toInt() == -1)
                    return Token.TOK_EOF

            } while (lastChar.isDigitOrDecimalPoint())

            println("found a number ${numStr.toDouble()}")

            return Token.TOK_NUMBER
        }

        if (lastChar == '#') {
            var comment = ""
            do {
                lastChar = reader.read().toChar()
                if (lastChar.toInt() == -1)
                    return Token.TOK_EOF

                comment += lastChar
            } while (lastChar != '\r' && lastChar != '\n')

            println("found comment \"${comment.substring(0, comment.length-1)}\"")

            return getTok()
        }

        if (lastChar.toInt() == -1) {
            return Token.TOK_EOF
        }

        return Token.TOK_OP
    }
}