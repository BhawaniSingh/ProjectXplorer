package org.bhawanisingh.projectxplorer.api.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class PleaseWait extends JComponent implements Runnable, MouseListener, KeyListener, FocusListener {

	private static final long serialVersionUID = -3781071463506069338L;
	private static Paint gradient = new GradientPaint(10f, 0f, Color.DARK_GRAY, 21f, 0f, Color.BLACK);
	private Area[] tickerOuter = null;
	private Area[] tickerInner = null;
	private boolean cont;
	private Thread waitThread;
	private JLabel focus;

	public PleaseWait() {
		focus = new JLabel();
		focus.setOpaque(false);
		this.add(focus);
		addListeners();
	}

	private void addListeners() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				tickerOuter = buildTicker(true);
				tickerInner = buildTicker(false);
			}

		});
	}

	public void stop() {
		cont = false;
		removeKeyListener(this);
		removeMouseListener(this);
		removeFocusListener(this);
		setVisible(false);
	}

	public void start() {
		cont = true;
		addKeyListener(this);
		addMouseListener(this);
		addFocusListener(this);
		focus.requestFocusInWindow();
		tickerOuter = buildTicker(true);
		tickerInner = buildTicker(false);
		setVisible(true);
		waitThread = new Thread(this);
		waitThread.start();
	}

	@Override
	public void run() {
		while (cont) {
			try {
				Thread.sleep(100);
				rotateTicker();
				this.repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (this) {
				if (!isShowing()) {
					cont = false;
				}
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		paintComponents(g);
		g.dispose();
	}

	@Override
	public void paintComponents(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		graphics.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		graphics.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		graphics.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		graphics.setFont(new Font(getFont().getName(), Font.ITALIC, 50));
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
		graphics.setPaint(PleaseWait.gradient);

		for (int i = 0; i < 11; i++) {
			graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, i * 0.1f));
			graphics.fill(tickerOuter[i]);
			graphics.fill(tickerInner[i]);

		}
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	private void rotateTicker() {
		int barsCount = 11;
		Area tempOuter = tickerOuter[0];
		Area tempInner = tickerInner[barsCount - 1];
		for (int i = 0; i < barsCount - 1; ++i) {
			tickerOuter[i] = tickerOuter[i + 1];
			tickerInner[barsCount - (i + 1)] = tickerInner[barsCount - (i + 2)];
		}
		tickerOuter[barsCount - 1] = tempOuter;
		tickerInner[0] = tempInner;

	}

	private Area[] buildTicker(boolean outer) {
		int barsCount = 11;
		AffineTransform toCenter;
		AffineTransform toBorder;
		AffineTransform toCircle;
		Area[] ticker = new Area[barsCount];
		Point2D.Double center = new Point2D.Double((double) getWidth() / 2, (double) getHeight() / 2);
		double fixedAngle = 2.0 * Math.PI / barsCount;

		for (double i = 0.0; i < barsCount; i++) {
			Area primitive = buildPrimitive(outer);
			toCenter = AffineTransform.getTranslateInstance(center.getX(), center.getY());
			if (outer) {
				toBorder = AffineTransform.getTranslateInstance(60, 20.0);
			} else {
				toBorder = AffineTransform.getTranslateInstance(20, -10.0);
			}

			toCircle = AffineTransform.getRotateInstance(-i * fixedAngle, center.getX(), center.getY());

			AffineTransform toWheel = new AffineTransform();
			toWheel.concatenate(toCenter);
			toWheel.concatenate(toBorder);

			primitive.transform(toWheel);
			primitive.transform(toCircle);

			ticker[(int) i] = primitive;
		}

		return ticker;
	}

	private Area buildPrimitive(boolean outer) {
		Rectangle2D.Double body;
		Ellipse2D.Double head;
		Ellipse2D.Double tail;
		if (outer) {
			body = new Rectangle2D.Double(3, 0, 30, 6);
			head = new Ellipse2D.Double(0, 0, 6, 6);
			tail = new Ellipse2D.Double(30, 0, 6, 6);
		} else {
			body = new Rectangle2D.Double(3, 0, 15, 6);
			head = new Ellipse2D.Double(0, 0, 6, 6);
			tail = new Ellipse2D.Double(15, 0, 6, 6);
		}

		Area tick = new Area(body);
		tick.add(new Area(head));
		tick.add(new Area(tail));
		return tick;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.err.print(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void focusGained(FocusEvent e) {
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (isVisible()) {
			focus.requestFocusInWindow();
		}

	}

	public Thread getWaitThread() {
		return waitThread;
	}

}