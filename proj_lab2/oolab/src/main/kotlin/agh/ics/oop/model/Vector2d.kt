package agh.ics.oop.model

import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

data class Vector2d(private val x: Int, private val y: Int) {

    fun getX(): Int = x

    fun getY(): Int = y

    override fun toString(): String = "($x,$y)"

    //potrzebne do punktu 3. części 1.
    operator fun compareTo(other: Vector2d): Int{
        if (x <= other.x && y < other.y || x < other.x && y <= other.y) return -1
        if (x >= other.x && y > other.y || x > other.x && y >= other.y) return 1
        return 0
    }

    //na podstawie punktu 2. z części 1. było:
    //fun precedes(other: Vector2d): Boolean = x <= other.x && y <= other.y
    fun precedes(other: Vector2d): Boolean = this < other || this == other

    //na podstawie punktu 2. z części 1. było:
    //fun follows(other: Vector2d): Boolean = x >= other.x && y >= other.y
    fun follows(other: Vector2d): Boolean = this > other || this == other

    //na podstawie punktu 2. z części 1. było:
    //fun add(other: Vector2d): Vector2d = Vector2d(x + other.x, y + other.y)
    operator fun plus(other: Vector2d) = Vector2d(x + other.x, y + other.y)

    //na podstawie punktu 2. z części 1. było:
    //fun subtract(other: Vector2d): Vector2d = Vector2d(x - other.x, y - other.y)
    operator fun minus(other: Vector2d) = Vector2d(x - other.x, y - other.y)

    fun upperRight(other: Vector2d): Vector2d = Vector2d(max(x, other.x), max(y, other.y))

    fun lowerLeft(other: Vector2d): Vector2d = Vector2d(min(x, other.x), min(y, other.y))

    fun opposite(): Vector2d = Vector2d(-x, -y)
}

fun MapDirection.toUnitVector(): Vector2d {
    val value = this.getValue()
    return Vector2d((-1 * (value % 2)).toDouble().pow((value / 2 + 2).toDouble()).toInt(),
        (-1 + value % 2).toDouble().pow((value / 2 + 2).toDouble()).toInt())
}
