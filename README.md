# Snake-Game
Java Basic Project - (LinkedList)

## Key Concepts Used

<img width="548" alt="{F197137E-145C-46AA-B3AF-792C82F6F1C3}" src="https://github.com/user-attachments/assets/35583e0d-d1a0-4e79-baea-f96b421c91cd">

### 1. Object-Oriented Programming (OOP)
- **Encapsulation**: The game's logic is encapsulated in methods and classes to ensure modularity and clarity.  
  **Example**: Methods like `move()`, `checkCollision()`, and `checkFood()` manage specific aspects of the game.
- **Abstraction**: Complex operations, such as handling user input and updating the game, are hidden behind simple method calls.

### 2. Event-Driven Programming
- The game responds to player inputs (arrow keys) using `KeyListener`.
- A Swing `Timer` triggers periodic updates to manage the real-time gameplay experience.  
  **Example**: The `actionPerformed()` method is called by the `Timer` to update the game at regular intervals.

### 3. Graphical User Interface (GUI)
- Built using the **Swing Framework**:
  - **`JPanel`**: Used for the drawable game area.
  - **`JFrame`**: Provides the main application window.
- Graphics rendering is achieved via the `paintComponent()` method, where game elements like the snake and food are drawn.

### 4. Data Structures
- **`LinkedList`**: Represents the snake as a sequence of connected body segments.  
  **Example**: The head is added at the beginning (`addFirst()`), and the tail is removed (`removeLast()`) to simulate movement.
- **`Point` Class**: Stores the x and y coordinates of the snake's segments and the food.

### 5. Game Logic and State Management
- **Collision Detection**:
  - Checks if the snake collides with walls or itself, ending the game.
- **Game State**:
  - Managed using a boolean variable `running` to track whether the game is active.
- **Food Consumption**:
  - Detects when the snake eats food and grows its length.

### 6. Randomization
- **`Math.random()`**: Generates random coordinates for the food, ensuring it spawns within the game boundaries and doesn't overlap with the snake.

### 7. Swing Timer
- The `Timer` is used to handle real-time updates, moving the snake at regular intervals.  
  **Example**: The delay in the timer can dynamically adjust to increase or decrease the game's speed.

### 8. Dynamic Difficulty Adjustment
- As the snake grows longer, the game's speed increases by reducing the `Timer` delay.  
  This feature makes the game more challenging over time.

### 9. Collision Detection
The game detects when:
- The snake collides with the game boundaries.
- The snake collides with itself (self-intersection).

### 10. Modular Design
The code is divided into reusable methods to improve readability and maintainability:
- **`move()`**: Updates the snake's position.
- **`checkFood()`**: Handles food consumption and respawning.
- **`checkCollision()`**: Ends the game if the snake collides with walls or itself.

### 11. Graphics
- The game uses the `Graphics` object to render the snake, food, and game board.  
  **Example**: Drawing rectangles to represent the snake segments and the food.

### 12. Error Handling and Termination
- Displays a dialog box (`JOptionPane`) to notify the player when the game ends due to a collision.

