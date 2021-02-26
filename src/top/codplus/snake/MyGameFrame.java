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
 * ̰ʳ��С��Ϸ ������
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
			while (true) { // �����ػ�����
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
		// ˫����
		Image iBuffer = null;
		Graphics gBuffer = null;
		if (iBuffer == null) {
		      iBuffer = createImage(this.getSize().width, this.getSize().height);
		      gBuffer = iBuffer.getGraphics();
		    }
		    gBuffer.setColor(getBackground());
		    gBuffer.fillRect(0, 0, this.getSize().width, this.getSize().height);
		// ��ֹ������˸
		super.paint(gBuffer);
		
		// ��¼ԭ������ɫ������
		Color c = gBuffer.getColor();
		Font f = gBuffer.getFont();
		
		// �����߿�
		gBuffer.setColor(Color.BLACK);
		gBuffer.drawRect(50, 50, 300, 300);
		
		food.drawSelf(gBuffer);
		
		// �ָ�ԭ������ɫ������
		gBuffer.setColor(c);
		gBuffer.setFont(f);
		g.drawImage(iBuffer, 0, 0, this);
	}
	
	public void lauchFrame() { // ���崰�ڳ�ʼ��
		this.setTitle("XYB��̰ʳ��");
		this.setVisible(true);
		this.setLocation(100, 100);
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		this.addWindowListener(new WindowAdapter() { // �رմ��ڼ���������
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		new PaintThread().start();  // �����ػ����ڵ��߳�
		addKeyListener(new KeyMonitor());
	}
	
	public static void main(String[] args) {
		MyGameFrame frame = new MyGameFrame();
		frame.lauchFrame();
	}
}
