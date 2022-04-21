# ECE651 RISK Game Project!

Welcome to RISK!

![alt text](RISKGameScreenShot.png)

This is a game where you fight your oponent and try to take over each other's territories. 
In order to play, download the repository, and start the server with:

```
  ./gradlew run-server
```

Next start each client to start the game:

```
  ./gradlew run-client
  ./gradlew run-client 
```

(two clients for 2 players)

### Game Rules:
1. Each player regenerates food and electricity after each round of actions
2. Players can Move only within their reachable, owned territories
3. Moving and Attacking costs food resources
4. Players can upgrade units at a territory using their electricity resource
5. First player to take over all territories wins!
