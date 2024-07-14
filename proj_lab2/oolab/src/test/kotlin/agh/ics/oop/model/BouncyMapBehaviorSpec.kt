package agh.ics.oop.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class BouncyMapBehaviorSpec: BehaviorSpec ({
    given("Empty Bouncy Map and some animals") {
        val animal1 = Animal()
        val animal2 = Animal(Vector2d(3, 4))
        val animal3 = Animal(Vector2d(6, 8))
        val animal4 = Animal()
        val bouncyMap = BouncyMap(5, 5)

        `when` ("Added some animals to Bouncy Map") {
            bouncyMap.place(animal1)
            bouncyMap.place(animal2)
            bouncyMap.place(animal3)
            bouncyMap.place(animal4)

            then("There should be three animals on the map"){
                bouncyMap.objectAt(animal1.position) shouldBe animal1
                bouncyMap.objectAt(animal2.position) shouldBe animal2
                bouncyMap.objectAt(animal3.position) shouldBe null
                animal4.isAt(Vector2d(2, 2)) shouldBe false
            }
        }

        `when` ("Some animals moved"){
            animal4.position = Vector2d(0, 0)
            bouncyMap.place(animal4)
            bouncyMap.move(animal1, MoveDirection.BACKWARD)
            bouncyMap.move(animal2, MoveDirection.RIGHT)
            bouncyMap.move(animal3, MoveDirection.LEFT)
            bouncyMap.move(animal4, MoveDirection.FORWARD)

            then("Now it should look like that"){
                bouncyMap.objectAt(Vector2d(2, 1)) shouldBe animal1
                bouncyMap.objectAt(Vector2d(3, 4)) shouldBe animal2
                bouncyMap.objectAt(Vector2d(6, 8)) shouldNotBe animal3
                bouncyMap.objectAt(Vector2d(0, 1)) shouldBe animal4
            }
        }

        `when`("Some animals try to fight or escape..."){
            bouncyMap.move(animal1, MoveDirection.LEFT)
            bouncyMap.move(animal4, MoveDirection.RIGHT)
            bouncyMap.move(animal1, MoveDirection.FORWARD)
            bouncyMap.move(animal4, MoveDirection.FORWARD)

            then("...but neither can they fight..."){
                bouncyMap.objectAt(Vector2d(1, 1)) shouldBe animal1
                bouncyMap.objectAt(Vector2d(1, 1)) shouldNotBe animal4
            }

            bouncyMap.move(animal2, MoveDirection.FORWARD)
            bouncyMap.move(animal2, MoveDirection.FORWARD)
            bouncyMap.move(animal2, MoveDirection.FORWARD)
            bouncyMap.move(animal2, MoveDirection.LEFT)
            bouncyMap.move(animal2, MoveDirection.FORWARD)
            bouncyMap.move(animal2, MoveDirection.FORWARD)

            then("...nor escape"){
                bouncyMap.objectAt(Vector2d(5, 5)) shouldBe animal2
                bouncyMap.objectAt(Vector2d(6, 6)) shouldBe null
            }
        }
    }
})