package top.codplus.snake;

/**
 * 贪食蛇小游戏 食物类
 * @author XYB314
 *
 */

import java.awt.Color;
import java.awt.Graphics;

public class Food extends GameObject{
	public Food(){
		super();
		this.color = Color.RED;
	}

	public void drawSelf(Graphics g) {
		Color c = g.getColor();
		
		g.setColor(this.color);
		g.fillRect(x + 2, y + 2, width - 4, height - 4);
		relocate();
		
		g.setColor(c);
	}
}
