package dmi;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.RepaintManager;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;

import java.awt.*;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class Pantalla extends JFrame{

	int AngIni = -29;
	int Ang = 238;
	int div = 2/*1.025f*/;
	/*
	 * Parametros configurables
	 */
	int divLinVel = 10;
	boolean modeActive = true;
	boolean kmActive = true;
	int displayType = 1;
	Color prefColor = Color.GREEN;
	Color spdColor = Color.YELLOW;
	static int v_max = 160;
	float scale = /*1.3f*/ 1.45f;
	boolean UT449 = false;

	boolean UT446 = false;

	/*------------------------*/
	JLabel Modo;
	float v_pref = 0;
	static float v_act = 0;
	int centx = 225;
	int centy = 175+40;
	Velocidad vel;
	JLabel spdBorder;
	JLabel km;
	JLabel spd;
	JLabel modo;
	String parameter;
	String serieAnterior;
	
	JPanel panel1;
	JPanel panel2;
	
	//float scale = /*1.3f*/ 1.2f;
	public int value=-1;
	JLabel digit1 = new JLabel();
	JLabel digit2 = new JLabel();;
	JLabel digit3 = new JLabel();;
	boolean LeadingZeros=false;
	int getScale(double val)
	{
		return (int)Math.round(val*scale);
	}
	public Pantalla() {
		 setTitle("VELOCIMETRO");
		 setSize(getScale(450), getScale(345));
		 setUndecorated(true);
		 setVisible(true);
		 setResizable(false);
		 getContentPane().setBackground(Color.BLACK);
		 setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void DisplayType1() {
		panel1 = new JPanel();
		panel1.setBackground(Color.BLACK);
		panel1.setLayout(null);
		getContentPane().add(panel1);
		vel = new Velocidad(this);
		vel.setBounds(0, 0, getScale(450), getScale(350));
		panel1.add(vel);
		 for(int i=0; i<=v_max; i+=divLinVel)
		 {
			 JLabel j = new JLabel(Integer.toString(i));
			 j.setFont(new Font("coolvetica rg", Font.PLAIN, getScale(20))); 
			 j.setForeground(Color.WHITE);
			 j.setHorizontalAlignment(SwingConstants.CENTER);
			 int r = 135;
			 if(v_max >= 180)
			 {
				 if(i<140) r = 135;
				 if(i > 90 && i<130) r = 140;
				 //if(i<100) r = 140;
				 if(i==0) r = 140;
			 }
			 
			 if(i<100) r = 140;
			 if(i==0) r = 140;
			 double cx = centx - r*Math.cos(Math.toRadians(AngIni+i/(float)v_max*Ang));
			 double cy = centy - r*Math.sin(Math.toRadians(AngIni+i/(float)v_max*Ang));
			 panel1.add(j);
			 j.setBounds(getScale(cx-25), getScale(cy-10), getScale(50), getScale(20));
		 }
		 
		 //velocidad 3 digitos 
		 digit1.setFont(new Font("RetrorelicSlab", Font.PLAIN, getScale(30)));
		 digit1.setForeground(Color.WHITE);
		 digit1.setHorizontalAlignment(SwingConstants.CENTER);
		 panel1.add(digit1);
		 digit1.setBounds(getScale(centx-1), getScale(centy-85), getScale(33), getScale(40));
		 
		 digit2.setFont(new Font("RetrorelicSlab", Font.PLAIN, getScale(30)));
		 digit2.setForeground(Color.WHITE);
		 digit2.setHorizontalAlignment(SwingConstants.CENTER);
		 panel1.add(digit2);
		 digit2.setBounds(getScale(centx-23), getScale(centy-85), getScale(33), getScale(40));
		 
		 digit3.setFont(new Font("RetrorelicSlab", Font.PLAIN, getScale(30)));
		 digit3.setForeground(Color.WHITE);
		 digit3.setHorizontalAlignment(SwingConstants.CENTER);
		 panel1.add(digit3);
		 digit3.setBounds(getScale(centx-43), getScale(centy-85), getScale(33), getScale(40));
		 
		 //spd = new JLabel(Integer.toString(Math.round(v_act)));
		 spd = new JLabel();
		 spd.setFont(new Font("RetrorelicSlab", Font.PLAIN, getScale(28)));
		 spd.setForeground(Color.WHITE);
		 spd.setHorizontalAlignment(SwingConstants.CENTER);
		 //Border b = BorderFactory.createLineBorder(Color.WHITE);
		 Border b = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE);
		 spd.setBorder(b);
		 panel1.add(spd);
		 spd.setBounds(getScale(centx-55), getScale(centy-85), getScale(100), getScale(40));
		 
		 JLabel unit = new JLabel("Km/h");
		 unit.setFont(new Font("coolvetica rg", Font.PLAIN, getScale(20)));
		 unit.setForeground(Color.WHITE);
		 unit.setHorizontalAlignment(SwingConstants.CENTER);
		 panel1.add(unit);
		 unit.setBounds(getScale(centx-45), getScale(centy-50), getScale(80), getScale(30));
		 panel1.setVisible(true);
	}
	
	public void DisplayType2() 
	{
		panel2 = new JPanel();
		panel2.setBackground(Color.BLACK);
		panel2.setLayout(null);
		this.getContentPane().add(panel2);
		 vel = new Velocidad(this);
		 vel.setBounds(0, 0, getScale(450), getScale(350));
		 for(int i=0; i<=v_max; i+=divLinVel)
		 {
			 JLabel j = new JLabel(Integer.toString(i));
			 j.setFont(new Font("SepsaCivia", Font.PLAIN, getScale(20)));
			 j.setForeground(Color.YELLOW);
			 j.setHorizontalAlignment(SwingConstants.CENTER);
			 int r = 145;
			 if(v_max >= 180)
			 {
				 if(i==100) r=155;
				 if(i>100 && i<120) r = 155;
				 if(i==120) r=153;
			 }
			 else if(v_max<180) if(i==100) r=150;
			 if(i<100) r = 155;
			 if(i==0) r = 155;
			 double cx = centx - r*Math.cos(Math.toRadians(AngIni+i/(float)v_max*Ang));
			 double cy = centy - r*Math.sin(Math.toRadians(AngIni+i/(float)v_max*Ang));
			 panel2.add(j);
			 j.setBounds(getScale(cx-25), getScale(cy-10), getScale(50), getScale(20));
		 }

		Border b = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED);
		 if(UT446 != false)
		 {
			 spd = new JLabel(Integer.toString(Math.round(v_act)));
			 spd.setFont(new Font("RetrorelicSlab", Font.PLAIN, getScale(26)));
			 spd.setForeground(Color.GREEN);
			 spd.setHorizontalAlignment(SwingConstants.RIGHT);
			 panel2.add(spd);
			 spd.setBounds(getScale(centx-38.5f), getScale(centy-59.5), getScale(60), getScale(35));

			 spdBorder = new JLabel();
			 spdBorder.setFont(new Font("Arial", Font.PLAIN, getScale(26)));
			 spdBorder.setForeground(Color.GREEN);
			 spdBorder.setHorizontalAlignment(SwingConstants.RIGHT);

			 spdBorder.setBorder(b);
			 panel2.add(spdBorder);
			 spdBorder.setBounds(getScale(centx-30), getScale(centy-55), getScale(60), getScale(30));

			 JLabel unit = new JLabel("Km/h");
			 unit.setFont(new Font("Arial", Font.BOLD, getScale(20)));
			 unit.setForeground(Color.BLUE);
			 unit.setHorizontalAlignment(SwingConstants.CENTER);
			 panel2.add(unit);
			 unit.setBounds(getScale(centx-30), getScale(centy-25), getScale(60), getScale(30));
		 }
		 else
		 {
			 spd = new JLabel(Integer.toString(Math.round(v_act)));
			 spd.setFont(new Font("RetrorelicSlab", Font.PLAIN, getScale(26)));
			 spd.setForeground(Color.GREEN);
			 spd.setHorizontalAlignment(SwingConstants.RIGHT);
			 panel2.add(spd);
			 spd.setBounds(getScale(centx-38.5f), getScale(centy-101.5), getScale(60), getScale(35));

			 spdBorder = new JLabel();
			 spdBorder.setFont(new Font("Arial", Font.PLAIN, getScale(26)));
			 spdBorder.setForeground(Color.GREEN);
			 spdBorder.setHorizontalAlignment(SwingConstants.RIGHT);
			 spdBorder.setBorder(b);
			 panel2.add(spdBorder);
			 spdBorder.setBounds(getScale(centx-30), getScale(centy-97), getScale(60), getScale(30));

			 JLabel unit = new JLabel("Km/h");
			 unit.setFont(new Font("Arial", Font.PLAIN, getScale(20)));
			 unit.setForeground(Color.BLUE);
			 unit.setHorizontalAlignment(SwingConstants.CENTER);
			 panel2.add(unit);
			 unit.setBounds(getScale(centx-30), getScale(centy-68), getScale(60), getScale(30));
		 }
		 
		 if(kmActive == true)
		 {
			 km = new JLabel();
			 km.setHorizontalAlignment(SwingConstants.RIGHT);
			 Border b2 = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED);
			 km.setBorder(b2);
			 panel2.add(km);
			 km.setBounds(getScale(centx-70), getScale(centy+35), getScale(140), getScale(30));
			 
			 JLabel kmUnit = new JLabel("km");
			 kmUnit.setFont(new Font("Arial", Font.PLAIN, getScale(18)));
			 kmUnit.setForeground(Color.BLUE);
			 kmUnit.setHorizontalAlignment(SwingConstants.CENTER);
			 panel2.add(kmUnit);
			 kmUnit.setBounds(getScale(centx+35), getScale(centy+35), getScale(30), getScale(30));

		 }

		 if(modeActive != false)
		 {
			 modo = new JLabel();
			 modo.setFont(new Font("Arial", Font.PLAIN, getScale(14)));
			 modo.setForeground(Color.YELLOW);
			 modo.setHorizontalAlignment(SwingConstants.CENTER);
			 modo.setBorder(b);
			 updateModo(0);
			 panel2.add(modo);
			 modo.setBounds(getScale(centx-80), getScale(centy-30), getScale(160), getScale(30));

			 JLabel modeCond = new JLabel("MODO COND.");
			 modeCond.setFont(new Font("Arial", Font.PLAIN, getScale(20)));
			 modeCond.setForeground(Color.BLUE);
			 modeCond.setHorizontalAlignment(SwingConstants.CENTER);
			 panel2.add(modeCond);
			 modeCond.setBounds(getScale(centx-68), getScale(centy), getScale(140), getScale(30));
		 }
		 /*modo = new JLabel();
		 modo.setFont(new Font("Arial", Font.PLAIN, getScale(14)));
		 modo.setForeground(Color.YELLOW);
		 modo.setHorizontalAlignment(SwingConstants.CENTER);
		 modo.setBorder(b);
		 updateModo(0);
		 panel2.add(modo);
		 modo.setBounds(getScale(centx-80), getScale(centy-30), getScale(160), getScale(30));
		 
		 JLabel modeCond = new JLabel("MODO COND.");
		 modeCond.setFont(new Font("Arial", Font.PLAIN, getScale(20)));
		 modeCond.setForeground(Color.BLUE);
		 modeCond.setHorizontalAlignment(SwingConstants.CENTER);
		 panel2.add(modeCond);
		 modeCond.setBounds(getScale(centx-68), getScale(centy), getScale(140), getScale(30));*/
		 
		 JLabel vigil = new JLabel();
		 vigil.setIcon(new ImageIcon(getClass().getResource("/dmi/vigilancia.JPG")));
		 panel2.add(vigil);
		 vigil.setBounds(getScale(10), getScale(10), getScale(50), getScale(44));
		 panel2.add(vel);
		 panel2.setVisible(true);
	}
	
	public void updateReal(float curr)
	{
		v_act = curr;
		
		if(displayType == 1) 
		{
			int v1 = (int)v_act / 100;
	        int v2 = ((int)v_act / 10) % 10;
	        int v3 = (int)v_act % 10;
	        
			if (v1==0 && v2>0)
			{
				digit1.setText(Integer.toString(Math.round(v3)));
				digit2.setText(Integer.toString(Math.round(v2)));
				digit3.setText("");
				
			}if (v1==0 && v2 == 0)
	    	{
				digit1.setText(Integer.toString(Math.round(v3)));
				digit2.setText("");
				digit3.setText("");
	    	}
			if (v1>0) 
			{
				digit3.setText(Integer.toString(Math.round(v1)));
				digit2.setText(Integer.toString(Math.round(v2)));	
				digit1.setText(Integer.toString(Math.round(v3)));
			}
			repaint();
		}
		else if(displayType == 2)
		{
			spd.setText(Integer.toString(Math.round(v_act)));
			repaint();
		}
	}
	public void updatePrefijada(float curr)
	{
		v_pref = curr;
		revalidate();
		repaint();
	}
	String modosCivia[] = {"MANUAL","VEL. PREFIJADA", "TALLER", "ACOPLE"};
	String modos449[] = {"MANUAL","VEL. PREF.", "TALLER", "ACOPLE"};
	public void updateModo(int curr)
	{
		if(modo != null) 
		{
			if(kmActive == false)
			{
				modo.setText(modosCivia[curr]);
			}
			else if(kmActive == true)
			{
				modo.setText(modos449[curr]);
			}
		}
	}
	public boolean TestInit(Pantalla p) throws InterruptedException
	{
		for(int i = 0; i <= v_max; i++)
		{
			p.updateReal(i);
			p.updatePrefijada(i);
			Thread.sleep(6);
		}
		for(int i = v_max; i >= 0; i--)
		{
			p.updateReal(i);
			p.updatePrefijada(i);
			Thread.sleep(6);
		}
		if(modeActive == true)
		{
			for(int y = 0; y <= modosCivia.length - 1; y++)
			{
				p.updateModo(y);
				Thread.sleep(250);
			}
		}
		return true;
	}
	public static void main(String[] args) throws InterruptedException
	{
		Pantalla p = new Pantalla();
		Client c = new Client();
		c.sendData("register(speed)");
		c.sendData("register(cruise_speed)");
		c.sendData("register(mode)");
		c.sendData("register(master_key)");
		c.sendData("register(serie)");
		Thread.sleep(100);
		
		while(true)
		{
			String s = c.readData();
			if(s==null) return;
			if(s.startsWith("speed="))
			{
				p.updateReal(Float.parseFloat(s.substring(6).replace(',', '.')));
			}
			else if(s.startsWith("cruise_speed="))
			{	
				p.updatePrefijada(Float.parseFloat(s.substring(13).replace(',', '.')));
			}
			else if(s.startsWith("mode="))
			{
				p.updateModo(Integer.parseInt(s.substring(5)));
			}
			else if(s.startsWith("serie="))
			{
				p.parameter = s.substring(6);
				if(p.parameter.equals("447"))
				{	
					p.divLinVel = 20;
					p.modeActive = false;
					p.kmActive = false;
					p.UT449 = false;
					p.UT446 = false;
					p.displayType = 1;
					p.prefColor = Color.GREEN;
					p.spdColor = Color.YELLOW;
					p.v_max = 140;
				}
				else if(p.parameter.equals("450"))
				{
					p.divLinVel = 10;
					p.modeActive = false;
					p.kmActive = false;
					p.UT449 = false;
					p.UT446 = false;
					p.displayType = 1;
					p.prefColor = Color.GREEN;
					p.spdColor = Color.YELLOW;
					p.v_max = 160;
				}
				else if(p.parameter.equals("Civia"))
				{
					p.divLinVel = 10;
					p.modeActive = true;
					p.kmActive = false;
					p.UT449 = false;
					p.UT446 = false;
					p.displayType = 2;
					p.div = 2;
					p.prefColor = Color.GREEN;
					p.spdColor = Color.YELLOW;
					p.v_max = 150;
				}
				else if(p.parameter.equals("446"))
				{
					p.divLinVel = 10;
					p.modeActive = false;
					p.kmActive = false;
					p.UT449 = false;
					p.UT446 = true;
					p.displayType = 2;
					p.div = 1;
					p.prefColor = Color.RED;
					p.spdColor = Color.GREEN;
					p.v_max = 120;
				}
				else if(p.parameter.equals("449"))
				{
					p.divLinVel = 10;
					p.modeActive = true;
					p.kmActive = true;
					p.UT449 = true;
					p.UT446 = false;
					p.displayType = 2;
					p.div = 2;
					p.prefColor = Color.GREEN;
					p.spdColor = Color.YELLOW;
					p.v_max = 180;
				}
			}
			else if(s.startsWith("master_key="))
			{
				//p.updateModo(Integer.parseInt(s.substring(10)));
				if(Integer.parseInt(s.substring(11)) == 1) 
				{
					if(p.displayType == 1)
					{
						p.DisplayType1();
						//p.panel1.setVisible(true);
						//p.repaint();
						if(p.TestInit(p) == true);
						else return;
					}
					else if(p.displayType == 2)
					{
						p.DisplayType2();
						//p.panel2.setVisible(true);
						//p.repaint();
						if(p.TestInit(p) == true);
						else return;
					}
				}
				if(Integer.parseInt(s.substring(11)) == 0)
				{
					p.getContentPane().removeAll();
					p.repaint();
				}
			}
		}
	}
}
