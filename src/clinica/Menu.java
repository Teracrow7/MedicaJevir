package clinica;

import java.awt.Color; 
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

 
public class Menu {
	
	ImageIcon LogoIcon = new ImageIcon("LogoClinica.png");
	JFrame menuFrame = new JFrame();
	BGPanel bgPanel = new BGPanel();
	
	
	JLabel LogoLabel = new JLabel();
	JPanel LogoPanel = new JPanel();
	Menu(){
		
	InitializeFrame();	
		
	}


	private void InitializeFrame() {
		
		//bgPanel.setBounds(0, 0, 300, 200);
		
		
		
		
		
		LogoLabel.setIcon(LogoIcon);
		LogoPanel.add(LogoLabel);
		LogoPanel.setBounds(10, 50, 250, 250);
		LogoPanel.setBackground(new Color(196, 235, 188));
		//LogoPanel.addMouseListener(this);
		
		menuFrame.setSize(600,400);
		//menuFrame.setDefaultLookAndFeelDecorated(true);
		menuFrame.getRootPane().putClientProperty("JRootPane.titleBarBackground", Color.white);
		menuFrame.getRootPane().putClientProperty("JRootPane.titleBarForeground", Color.black);
		//menuFrame.setUndecorated(true);
		//menuFrame.setOpacity((float) 0.3);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setResizable(false);
		menuFrame.setLocationRelativeTo(null);
		menuFrame.add(new EliminarButton());
		menuFrame.add(new PacNuevoButton());
		menuFrame.add(new ChecarPacienteButton());
		menuFrame.add(new CitasButton());
		menuFrame.add(LogoPanel);
		menuFrame.add(bgPanel);
		menuFrame.setVisible(true);
		
	}
	
	@SuppressWarnings("serial")
	public class BGPanel extends JPanel{	
		private Color color3 = new Color(38, 56, 84);
		private Color color4 = new Color(38, 56, 84);
		
		//private Color color1 = new Color(60, 59, 63);
		//private Color color2 = new Color(96, 92, 60);
		
		private Color color1 = new Color(196, 235, 188);
		private Color color2 = new Color(196, 235, 188);

		BGPanel(){
			
			this.setPreferredSize(new Dimension(600,400));
		
		}
		public void paint(Graphics g) {
			super.paint(g);
			
			Graphics2D g2D = (Graphics2D) g;
			Graphics2D g2D1 = (Graphics2D) g;
			Graphics2D g2D2 = (Graphics2D) g;
			int w = getWidth();
			int h = getHeight();

			GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
			
			GradientPaint gp2 = new GradientPaint(250, 0, color3, 0, h, color4);

			int[] xPoints = {0,290,290,0};
			int[] yPoints = {h,h,0,0};
			g2D.setPaint(gp);
			g2D.fillPolygon(xPoints, yPoints, 4);
			
			int[] x1Points = {290,w,w,290};
			int[] y2Points = {0,0,h,h};
			g2D1.setPaint(gp2);
			g2D1.fillPolygon(x1Points,y2Points,4);

		}
		
		
	}
	
	public class CitasButton extends JButton implements ActionListener{

		private Color color1 = new Color(196, 235, 188);
		private Color color2 = new Color(196, 235, 188);
	    public CitasButton() {
				
	    		setText("Citas");
	    		setBounds(380,30,130,20);
				this.addActionListener(this);	
				this.setFocusable(false);
				this.setContentAreaFilled(false);
				this.setForeground(Color.black);
				this.setCursor(new Cursor(Cursor.HAND_CURSOR));
				this.setBorder(new EmptyBorder(10,20,10,20));
			}
			
			public void paint(Graphics g) {
				int w = getWidth();
				int h = getHeight();
				BufferedImage img = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2D = img.createGraphics();
				g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				//g2D.setColor(Color.red);
				GradientPaint gp = new GradientPaint(0,0,color1,w,0,color2);
				g2D.setPaint(gp);
				g2D.fillRoundRect(0, 0, w, h, h, h);		
				g.drawImage(img, 0, 0, null);
				
				super.paintComponent(g);
				
			}

		@Override
		public void actionPerformed(ActionEvent e) {
			Citas citas = new Citas();
			citas.Init();
			
		}
	}
	
	
	public class ChecarPacienteButton extends JButton implements ActionListener{
		private Color color1 = new Color(196, 235, 188);
		private Color color2 = new Color(196, 235, 188);
	    public ChecarPacienteButton() {
				
	    		setBounds(380,110,130,20);
				this.addActionListener(this);	
				this.setText("Checar Datos");
				this.setFocusable(false);
				this.setContentAreaFilled(false);
				this.setForeground(Color.black);
				this.setCursor(new Cursor(Cursor.HAND_CURSOR));
				this.setBorder(new EmptyBorder(10,20,10,20));
			}
			
			public void paint(Graphics g) {
				int w = getWidth();
				int h = getHeight();
				BufferedImage img = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2D = img.createGraphics();
				g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				//g2D.setColor(Color.red);
				GradientPaint gp = new GradientPaint(0,0,color1,w,0,color2);
				g2D.setPaint(gp);
				g2D.fillRoundRect(0, 0, w, h, h, h);		
				g.drawImage(img, 0, 0, null);
				
				super.paintComponent(g);
				
			}

		@Override
		public void actionPerformed(ActionEvent e) {
			ChecarDatosPaciente checkData = new ChecarDatosPaciente();
			
		}
	}
	
	
	
	
	@SuppressWarnings("serial")
	public class PacNuevoButton extends JButton implements ActionListener {
		private Color color1 = new Color(196, 235, 188);
		private Color color2 = new Color(196, 235, 188);
		public PacNuevoButton() {
			
			setBounds(380,200,130,20);
			this.addActionListener(this);	
			this.setText("Paciente Nuevo");
			this.setFocusable(false);
			this.setContentAreaFilled(false);
			this.setForeground(Color.black);
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
			this.setBorder(new EmptyBorder(10,20,10,20));
		}
		
		public void paint(Graphics g) {
			int w = getWidth();
			int h = getHeight();
			BufferedImage img = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2D = img.createGraphics();
			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			//g2D.setColor(Color.red);
			GradientPaint gp = new GradientPaint(0,0,color1,w,0,color2);
			g2D.setPaint(gp);
			g2D.fillRoundRect(0, 0, w, h, h, h);		
			g.drawImage(img, 0, 0, null);
			
			super.paintComponent(g);
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==this) {
				PacienteNuevo pacNuevo = new PacienteNuevo();
				pacNuevo.Initialize();
				//System.out.println("Hola");

			}
			
		}	
	}
	
	
	public class EliminarButton extends JButton implements ActionListener{

		private Color color1 = new Color(196, 235, 188);
		private Color color2 = new Color(196, 235, 188);
	    public EliminarButton() {
				
	    		setText("Eliminar");
	    		setBounds(380,290,130,20);
				this.addActionListener(this);	
				this.setFocusable(false);
				this.setContentAreaFilled(false);
				this.setForeground(Color.black);
				this.setCursor(new Cursor(Cursor.HAND_CURSOR));
				this.setBorder(new EmptyBorder(10,20,10,20));
			}
			
			public void paint(Graphics g) {
				int w = getWidth();
				int h = getHeight();
				BufferedImage img = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2D = img.createGraphics();
				g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				//g2D.setColor(Color.red);
				GradientPaint gp = new GradientPaint(0,0,color1,w,0,color2);
				g2D.setPaint(gp);
				g2D.fillRoundRect(0, 0, w, h, h, h);		
				g.drawImage(img, 0, 0, null);
				
				super.paintComponent(g);
				
			}

		@Override
		public void actionPerformed(ActionEvent e) {
			Actualizar act = new Actualizar();
			Eliminar eliminar = new Eliminar();
			eliminar.init();
			act.init();
			
		}
	}
	
	
	
	
	
	
	
	
	
}
