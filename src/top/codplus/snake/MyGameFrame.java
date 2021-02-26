package top.codplus.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 * 贪食蛇小游戏 主程序
 * @author XYB314
 *
 */

public class MyGameFrame extends JFrame{
	
	Food food = new Food();
	
	class KeyMonitor extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			
			}
		@Override
		public void keyReleased(KeyEvent e) {
			
		}
	}
	
	class PaintThread extends Thread {
		@Override
		public void run() {
			while (true) { // 反复重画窗口
				repaint();
				try {
					Thread.sleep(Constant.REFRESH_RATE);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void paint(Graphics g) {
		// 双缓冲
		Image iBuffer = null;
		Graphics gBuffer = null;
		if (iBuffer == null) {
		      iBuffer = createImage(this.getSize().width, this.getSize().height);
		      gBuffer = iBuffer.getGraphics();
		    }
		    gBuffer.setColor(getBackground());
		    gBuffer.fillRect(0, 0, this.getSize().width, this.getSize().height);
		// 防止窗口闪烁
		super.paint(gBuffer);
		
		// 记录原来的颜色和字体
		Color c = gBuffer.getColor();
		Font f = gBuffer.getFont();
		
		// 画出边框
		gBuffer.setColor(Color.BLACK);
		gBuffer.drawRect(50, 50, 300, 300);
		
		food.drawSelf(gBuffer);
		
		// 恢复原来的颜色和字体
		gBuffer.setColor(c);
		gBuffer.setFont(f);
		g.drawImage(iBuffer, 0, 0, this);
	}
	
	public void lauchFrame() { // 定义窗口初始化
		this.setTitle("XYB的贪食蛇");
		this.setVisible(true);
		this.setLocation(100, 100);
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		this.addWindowListener(new WindowAdapter() { // 关闭窗口即结束程序
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		new PaintThread().start();  // 启动重画窗口的线程
		addKeyListener(new KeyMonitor());
	}
	
	public static void main(String[] args) {
		MyGameFrame frame = new MyGameFrame();
		frame.lauchFrame();
	}
}
