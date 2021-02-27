package top.codplus.snake;

import java.awt.Color;
import java.awt.Graphics;

public class SnakeUnit extends GameObject{
	public SnakeUnit(int _x, int _y, Color _color){
		super();
		this.x = _x;
		this.y = _y;
		this.color = _color;
	}

	public void drawSelf(Graphics g) {
		Color c = g.getColor();
		
		g.setColor(this.color);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		
		g.setColor(c);
	}
	
	public boolean peng(SnakeUnit other) {
		return this.x == other.x && this.y == other.y;
	}
}
