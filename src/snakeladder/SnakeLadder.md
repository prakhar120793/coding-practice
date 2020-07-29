# Snake and Ladder

## Start from the point of view of the user
* The user for this is a set of player. (User 1) (In scope)
* Game Setter could also be a user. (Out of Scope)

## Classes are of 2 types (in simple terms):
* Action classes {Things happen in this} // Usually they have interfaces.
    * Users interact with action classes.
    * Action classes use models to model the objects/data structures. 
    * Action classes have API's/methods which are used by the user/client
* Model classes // Usually they have abstract classes.

## Action classes
* Game 

## Model classes / Entities (Are used to model real world entities)
* Snake
* Ladder
* Player
    * Player position is not part of Player class. It's not the responsibility of player to maintain its position. 
    Player position should be part of game. 
* Dice 
    * Dice could be an interface because there could be multiple types of dice.
* Board (Board has snakes and ladders)
    * int nextPosition(position, diceRoll)
    * Player position should not be in the board. It is not the responsibility of the board to hold the player position,
     board in itself is an independent entity. Current Player positions + Board make a game. 

## Java specific points
* Package names are in small case.

## Some important things to note
* Add validations in public methods. 