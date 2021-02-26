package top.codplus.snake;

/**
 * 贪食蛇小游戏 游戏物品类
 * @author XYB314
 *
 */

import java.awt.*;

public class GameObject {
	protected int x, y;
	protected int width, height;
	protected Color color;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public GameObject() {
		super();
		relocate();
		this.width = Constant.OBJECT_WIDTH;
		this.height = Constant.OBJECT_HEIGHT;
	}
	public void drawSelf(Graphics g) {
		Color c = g.getColor();
		g.setColor(this.color);
		g.fillRect(x, y, width, height);
		g.setColor(c);
	}
	public Rectangle getRect() {  // 获得矩形
		return new Rectangle(x, y, width, height);
	}
	public void relocate() {
		this.x = (int)(Math.random() * 30) * 10 + 50;
		this.y = (int)(Math.random() * 30) * 10 + 50;
	}
}
