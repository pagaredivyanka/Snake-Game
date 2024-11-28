import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class SnakeGame extends JPanel implements ActionListener {
    private final int TILE_SIZE = 15;  // Size of one tile
    private final int WIDTH = 40;     // Number of tiles in width
    private final int HEIGHT = 30;    // Number of tiles in height
    private final int SCREEN_WIDTH = WIDTH * TILE_SIZE;
    private final int SCREEN_HEIGHT = HEIGHT * TILE_SIZE;

    private LinkedList<Point> snake;
    private Point food;
    private String direction = "RIGHT";
    private boolean running = true;
    private Timer timer;

    public SnakeGame() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (!direction.equals("DOWN")) direction = "UP";
                        break;
                    case KeyEvent.VK_DOWN:
                        if (!direction.equals("UP")) direction = "DOWN";
                        break;
                    case KeyEvent.VK_LEFT:
                        if (!direction.equals("RIGHT")) direction = "LEFT";
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (!direction.equals("LEFT")) direction = "RIGHT";
                        break;
                }
            }
        });

        initGame();
    }

    private void initGame() {
        snake = new LinkedList<>();
        snake.add(new Point(WIDTH / 2, HEIGHT / 2));  // Snake starts in the center
        spawnFood();

        timer = new Timer(250, this);  // Initial speed: 200ms per update
        timer.start();
    }

    private void spawnFood() {
        int x = (int) (Math.random() * WIDTH);
        int y = (int) (Math.random() * HEIGHT);
        food = new Point(x, y);
    }


    // increase the speed of the snake as it eats each fruit 
    private void updateTimerSpeed() {
        int newDelay = Math.max(50, 200 - (snake.size() - 1) * 10);  // Decrease delay as snake grows
        timer.setDelay(newDelay);  // Update timer interval
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkCollision();
            checkFood();
        }
        repaint();
    }

    private void move() {
        Point head = snake.getFirst();
        int x = head.x;
        int y = head.y;

        switch (direction) {
            case "UP":    y--; break;
            case "DOWN":  y++; break;
            case "LEFT":  x--; break;
            case "RIGHT": x++; break;
        }

        snake.addFirst(new Point(x, y));  // Add new head
        snake.removeLast();               // Remove tail
    }

    private void checkCollision() {
        Point head = snake.getFirst();

        // Check wall collision
        if (head.x < 0 || head.x >= WIDTH || head.y < 0 || head.y >= HEIGHT) {
            running = false;
        }

        // Check self-collision
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                running = false;
                break;
            }
        }

        if (!running) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Game Over! Your score: " + (snake.size() - 1));
        }
    }

    private void checkFood() {
        Point head = snake.getFirst();
        if (head.equals(food)) {
            snake.addLast(new Point(-1, -1));  // Add a dummy tail to grow
            spawnFood();
            updateTimerSpeed();  // Adjust speed as snake grows
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (running) {
            // Draw food
            g.setColor(Color.RED);
            g.fillRect(food.x * TILE_SIZE, food.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

            // Draw snake
            g.setColor(Color.GREEN);
            for (Point p : snake) {
                g.fillRect(p.x * TILE_SIZE, p.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        } else {
            g.setColor(Color.WHITE);
            g.drawString("Game Over!", SCREEN_WIDTH / 2 - 40, SCREEN_HEIGHT / 2);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        SnakeGame game = new SnakeGame();

        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
