package agh.ics.oop.model

import agh.ics.oop.randomFreePosition
import agh.ics.oop.randomPosition

class BouncyMap (private val width: Int, private val height: Int): IWorldMap {

    private var animals: MutableMap<Vector2d, Animal> = HashMap()

    override fun canMoveTo(position: Vector2d): Boolean =
        position.follows(Vector2d(0, 0)) && position.precedes(Vector2d(width, height))

    override fun place(animal: Animal) {
        if (!canMoveTo(animal.position)) return
        if (isOccupied(animal.position)){
            //Na podstawie punktu 5. z części 1. było:
            /*val randomPosition = Vector2d(Random.nextInt(0, width + 1), Random.nextInt(0, height + 1))
            animal.position = randomPosition
            when (animals.size == width * height){
                true -> animals[animal.position] = animal
                false -> place(animal)
            }*/
            //warto zauważyć, że tutaj losowanie odbywało się aż do skutku
            //(niedeterministycznie). W nowej wersji odbywa się tylko raz

            val possiblePosition = animals.randomFreePosition(Vector2d(width, height))
            when(possiblePosition){
                null -> {
                    val newPosition = animals.randomPosition()!!
                    animal.position = newPosition
                    animals[animal.position] = animal
                }
                else -> {
                    animal.position = possiblePosition
                    animals[animal.position] = animal
                }
            }
        }else {
            animals[animal.position] = animal
        }
    }

    override fun move (animal: Animal, direction: MoveDirection){
        if (animals.containsValue(animal)) {
            animals.remove(animal.position)
            animal.move(this, direction)
            place(animal)
        }
    }

    override fun isOccupied(position: Vector2d): Boolean = animals.containsKey(position)

    override fun objectAt(position: Vector2d): Animal? {
        return animals[position]
    }
}

