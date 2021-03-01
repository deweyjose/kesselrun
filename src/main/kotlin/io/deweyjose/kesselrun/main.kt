package io.deweyjose.kesselrun

import java.io.InputStreamReader

fun main(args: Array<String>) {
    val tokenizer = Tokenizer(InputStreamReader(System.`in`))
    while (tokenizer.getNextToken() != Token.TOK_EOF) {
        println(tokenizer.getToken())
    }
}
