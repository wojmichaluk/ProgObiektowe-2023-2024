package agh.ics.oop.model

enum class MapDirection(private val value: Int) {
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3);

    fun getValue(): Int = value

    override fun toString(): String = when (this) {
            NORTH -> "N"
            EAST -> "E"
            SOUTH -> "S"
            WEST -> "W"
    }

    fun next(): MapDirection {
        val directions = entries.toTypedArray()
        return directions[(value + 1) % directions.size]
    }

   fun previous(): MapDirection {
        val directions = entries.toTypedArray()
        return directions[(value + directions.size - 1) % directions.size]
    }

    //na podstawie punktu 2. z części 1. było:
    /*fun toUnitVector(): Vector2d = Vector2d(
            (-1 * (value % 2)).toDouble().pow((value / 2 + 2).toDouble()).toInt(),
            (-1 + value % 2).toDouble().pow((value / 2 + 2).toDouble()).toInt())*/
}
