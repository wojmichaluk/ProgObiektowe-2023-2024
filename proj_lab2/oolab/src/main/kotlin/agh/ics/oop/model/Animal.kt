package agh.ics.oop.model

data class Animal (var position: Vector2d = Vector2d(2, 2)) {
    private var direction = MapDirection.NORTH

    fun getPositionX(): Int = position.getX()
    fun getPositionY(): Int = position.getY()

    override fun toString(): String = "Z $position"

    fun isAt(position: Vector2d): Boolean = this.position == position

    fun move(validator: IMoveValidator, direction: MoveDirection) {
        var newPosition = position
        val unitVectorMovement = this.direction.toUnitVector()
        when (direction) {
            MoveDirection.LEFT -> this.direction = this.direction.previous()
            MoveDirection.RIGHT -> this.direction = this.direction.next()
            MoveDirection.FORWARD -> newPosition += unitVectorMovement
            MoveDirection.BACKWARD -> newPosition -= unitVectorMovement
        }
        if (validator.canMoveTo(newPosition)) {
            position = newPosition
        }
    }
}


