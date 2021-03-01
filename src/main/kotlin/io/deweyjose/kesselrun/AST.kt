package io.deweyjose.kesselrun.ast

import java.util.*

sealed class Expression
data class NumberExpression(val value: Double): Expression()
data class VariableExpression(val value: String): Expression()
data class BinaryExpression(val op: Char, val lhs: Expression, val rhs: Expression) : Expression()
data class CallExpression(val callee: String, val args: Vector<Expression>) : Expression()

data class Prototype(val name: String, val args: Vector<String>)
data class Function(val prototype: Prototype, val body: Expression)