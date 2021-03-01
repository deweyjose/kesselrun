package io.deweyjose.kesselrun

fun Char.isDigitOrDecimalPoint(): Boolean {
    return this.isDigit() || this == '.'
}
