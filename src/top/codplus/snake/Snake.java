package top.codplus.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

public class Snake {
	private int size, length;
	private SnakeUnit[] snake;
	private boolean left, right, up, down, live, gameOver;
	
	Snake(){
		this.size = 3;
		this.length = 64;
		snake = new SnakeUnit[this.length];
		snake[0] = new SnakeUnit(60, 50, Color.BLUE);
		snake[1] = new SnakeUnit(55, 50, Color.GREEN);
		snake[2] = new SnakeUnit(50, 50, Color.GREEN);
		live = right = true;
		left = up = down = gameOver = false;
	}
	
	public boolean isLive() {
		return live;
	}
	
	public boolean isGameOver() {
		return gameOver;
	}

	public void drawSelf(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		Color c = g2d.getColor();
		Font f = g2d.getFont();

		if(live) {
			move();
		}else {
			gameOver = true;
			g2d.setColor(Color.RED);			
			g2d.setFont(new Font("Times New Roman", Font.BOLD, 35));
			g2d.drawString("Game Over!", 110, 408);
			g2d.setFont(new Font("Times New Roman", Font.BOLD, 17));
			g2d.drawString("Press ENTER to restart.", 114, 434);
			g2d.setFont(new Font("Times New Roman", Font.BOLD, 14));
			g2d.drawString("[" + MyGameFrame.REFRESH_RATE + "] ms to move a step.", 202, 460);
			g2d.drawString("Press UP / DOWN to adjust.", 180, 478);
		}
		for (int i = this.size - 1; i >= 0; --i) {
			snake[i].drawSelf(g);
		}
		g2d.setColor(Color.RED);
		g2d.setFont(new Font("Times New Roman", Font.BOLD, 14));
		g2d.drawString("Score: " + (this.size - 3), 294, 372);
		
		g2d.setColor(c);
		g2d.setFont(f);
	}
	
	public Rectangle getHeadRect() {  // »ñµÃ¾ØÐÎ
		return new Rectangle(snake[0].getX(), snake[0].getY(), snake[0].getWidth(), snake[0].getHeight());
	}
	
	public void move() {
		for(int i = this.size - 1; i > 0; --i) {
			snake[i].setX(snake[i - 1].getX());
			snake[i].setY(snake[i - 1].getY());
		}
		SnakeUnit snakeHead = snake[0];
		if(right) {
			snakeHead.setX(snakeHead.getX() + Constant.OBJECT_WIDTH);
		}
		if(left) {
			snakeHead.setX(snakeHead.getX() - Constant.OBJECT_WIDTH);
		}
		if(down) {
			snakeHead.setY(snakeHead.getY() + Constant.OBJECT_HEIGHT);
		}
		if(up) {
			snakeHead.setY(snakeHead.getY() - Constant.OBJECT_HEIGHT);
		}
		snake[0] = snakeHead;
		toDie();
	}
	
	public void grow() {
		if (this.size == this.length) {
			this.length += 64;
			System.out.println(this.length);
			SnakeUnit[] newSnake = new SnakeUnit[this.length];
			System.arraycopy(snake, 0, newSnake, 0, size);
			snake = newSnake;
		}
		snake[this.size] = new SnakeUnit(snake[this.size - 1].getX(), snake[this.size - 1].getY(), Color.GREEN);
		this.size++;
		move();
	}
	
	public void changeDirection(KeyEvent e) {
		if (this.isGameOver()) return;
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A: {
			if(snake[0].getX() > snake[1].getX() && snake[0].getY() == snake[1].getY()) {
				break;
			}
			left = true;
			right = down = up = false;
			break;
		}
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W: {
			if(snake[0].getX() == snake[1].getX() && snake[0].getY() > snake[1].getY()) {
				break;
			}
			up = true;
			left = right = down = false;
			break;
		}
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D: {
			if(snake[0].getX() < snake[1].getX() && snake[0].getY() == snake[1].getY()) {
				break;
			}
			right= true;
			left = up = down = false;
			break;
		}
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S: {
			if(snake[0].getX() == snake[1].getX() && snake[0].getY() < snake[1].getY()) {
				break;
			}
			down = true;
			left = right = up = false;
			break;
		}
		}
	}
	
	public void toDie() {
		SnakeUnit snakeHead = snake[0];
		if (snakeHead.getX() <= 40 || snakeHead.getX() > 340) {
			live = false;
		}
		if (snakeHead.getY() <= 40 || snakeHead.getY() > 340) {
			live = false;
		}
		for(int i = 1; i < this.size; ++i) {
			if (snakeHead.peng(snake[i])) {
				live = false;
				break;
			}
		}
	}
	
	boolean isFoodOnSnake(Food food) {
		for (SnakeUnit snakeUnit: snake) {
			if (snakeUnit == null) {
				break;
			}
			if(food.getRect().intersects(snakeUnit.getRect())) {
				return true;
			}
		}
		return false;
	}
	
	
}
