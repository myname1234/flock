package DataAnalysis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/** */
/**
 * <p>
 * Title: LoonFramework
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * <p>
 * Company: LoonFramework
 * </p>
 * 
 * @author chenpeng
 * @email��ceponline@yahoo.com.cn
 * @version 0.1
 */
public class ExampleSlider extends JPanel {
	/** */
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public ExampleSlider() {
		// �趨������
		super(new BorderLayout());
		// �趨������
		ChangeListener listener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (e.getSource() instanceof JSlider) {
					System.out.println("�̶�: " + ((JSlider) e.getSource()).getValue());
				}
			}
		};
		// �趨JSlider1
		JSlider s1 = new JSlider(0, 100, 0);
		// ע���Զ���ui
		s1.setUI(new MySliderUI());
		// ���̶�
		s1.setMajorTickSpacing(10);
		// �ο̶�
		s1.setMinorTickSpacing(5);
		// �趨Ϊ��ʾ
		s1.setPaintTicks(true);
		s1.setPaintLabels(true);
		// ����slider1
		s1.addChangeListener(listener);
		// �趨JSlider2
		JSlider s2 = new JSlider(0, 100, 0);
		// ʹ��MetalSliderUIΪui
		s2.setUI(new javax.swing.plaf.metal.MetalSliderUI() {
			protected void paintHorizontalLabel(Graphics g, int v, Component l) {
				JLabel lbl = (JLabel) l;
				lbl.setForeground(Color.green);
				super.paintHorizontalLabel(g, v, lbl);
			}
		});

		s2.setForeground(Color.BLUE);
		s2.setMajorTickSpacing(10);
		s2.setMinorTickSpacing(5);
		s2.setPaintTicks(true);
		s2.setPaintLabels(true);
		s2.addChangeListener(listener);

		// ʹ�ú�ʽ����
		Box box = Box.createVerticalBox();
		box.add(Box.createVerticalStrut(5));
		box.add(s1);
		box.add(Box.createVerticalStrut(5));
		box.add(s2);
		box.add(Box.createVerticalGlue());
		add(box, BorderLayout.CENTER);
		add(Box.createHorizontalStrut(5), BorderLayout.WEST);
		add(Box.createHorizontalStrut(5), BorderLayout.EAST);
		// �趨�����С
		setPreferredSize(new Dimension(240, 100));
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				createUI();
			}
		});
	}

	public static void createUI() {
		JFrame frame = new JFrame("�����̶�����");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(new ExampleSlider());
		frame.setResizable(false);
		frame.pack();
		// ����
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}

class MySliderUI extends javax.swing.plaf.metal.MetalSliderUI {
	/** */
	/**
	 * ����ָʾ��
	 */
	public void paintThumb(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// �����Բ��Ϊ��ǰthumbλ��
		g2d.fillOval(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height);
		// Ҳ������ͼ(��������¼�ת��image�������ֲ�ͬ״̬)
		// g2d.drawImage(image, thumbRect.x, thumbRect.y,
		// thumbRect.width,thumbRect.height,null);
	}

	/** */
	/**
	 * ���ƿ̶ȹ켣
	 */
	public void paintTrack(Graphics g) {
		int cy, cw;
		Rectangle trackBounds = trackRect;
		if (slider.getOrientation() == JSlider.HORIZONTAL) {
			Graphics2D g2 = (Graphics2D) g;
			cy = (trackBounds.height / 2) - 2;
			cw = trackBounds.width;

			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.translate(trackBounds.x, trackBounds.y + cy);

			// ������Ϊ��ɫ
			g2.setPaint(Color.GRAY);
			g2.fillRect(0, -cy, cw, cy * 2);

			int trackLeft = 0;

			int trackRight = 0;

			trackRight = trackRect.width - 1;

			int middleOfThumb = 0;

			int fillLeft = 0;

			int fillRight = 0;

			// ���껻��
			middleOfThumb = thumbRect.x + (thumbRect.width / 2);
			middleOfThumb -= trackRect.x;

			if (!drawInverted()) {
				fillLeft = !slider.isEnabled() ? trackLeft : trackLeft + 1;
				fillRight = middleOfThumb;
			} else {
				fillLeft = middleOfThumb;
				fillRight = !slider.isEnabled() ? trackRight - 1 : trackRight - 2;
			}
			// �趨����
			g2.setPaint(new GradientPaint(0, 0, new Color(0, 100, 100), cw, 0, new Color(0, 255, 100), true));
			g2.fillRect(0, -cy, fillRight - fillLeft, cy * 2);

			g2.setPaint(slider.getBackground());
			Polygon polygon = new Polygon();
			polygon.addPoint(0, cy);
			polygon.addPoint(0, -cy);
			polygon.addPoint(cw, -cy);
			g2.fillPolygon(polygon);
			polygon.reset();

			g2.setPaint(Color.WHITE);
			g2.drawLine(0, cy, cw - 1, cy);

			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
			g2.translate(-trackBounds.x, -(trackBounds.y + cy));
		} else {
			super.paintTrack(g);
		}
	}
}
