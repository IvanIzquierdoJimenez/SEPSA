package dmi;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Velocidad extends JLabel {
	Pantalla p;
	int r1 = 210;
	int r2 = 193;
	int r3 = 176;
	public Velocidad(Pantalla p)
	{
		this.p = p;
		scale = p.scale;
		setOpaque(false);
	}
	float scale;
	int getScale(double f)
	{
		return (int)Math.round(f*scale);
	}
	void drawArc(Graphics2D g, float cx, float cy, float rad, int a0, int fin)
	{
		int x = (int) (cx-rad);
		int y = (int) (cy-rad);
		int w = (int) (2*rad);
		int h = w;
		g.drawArc(x, y, w, h, 180-a0, -fin);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		BasicStroke bs = new BasicStroke(3);
		g2d.setStroke(bs);
		g.setColor(Color.WHITE);
		drawArc(g2d, getScale(p.centx), getScale(p.centy), getScale(r1), p.AngIni, p.Ang);
		drawArc(g2d, getScale(p.centx), getScale(p.centy), getScale(r2), p.AngIni, p.Ang);
		drawArc(g2d, getScale(p.centx), getScale(p.centy), getScale(r3), p.AngIni, p.Ang);
		//drawArc(g2d, getScale(p.centx), getScale(p.centy), getScale(r1+1), p.AngIni, p.Ang);
		//drawArc(g2d, getScale(p.centx), getScale(p.centy), getScale(r2+1), p.AngIni, p.Ang);
		//drawArc(g2d, getScale(p.centx), getScale(p.centy), getScale(r3+1), p.AngIni, p.Ang);
		BasicStroke bs2 = new BasicStroke(2.0f);
		BasicStroke bs3 = new BasicStroke(0.65f);
		BasicStroke bs4 = new BasicStroke(0.25f);
		
		if(p.displayType == 1)
		{
			g2d.setStroke(bs2);
			g.setColor(Color.WHITE);
			drawArc(g2d, getScale(p.centx), getScale(p.centy), getScale(r3-16.6f), (int)(p.Ang/(float)p.v_max*15.0f+p.AngIni), (int)(p.Ang/(float)p.v_max*(p.v_max-35.0f)));
			//System.out.println((int)Math.toDegrees(Math.toRadians(p.Ang/(float)p.v_max*p.div))+(p.v_max));
		}
		
		//Graphics2D g2d = (Graphics2D)g;
		g2d.translate(getScale(p.centx), getScale(p.centy));
		g2d.rotate(Math.toRadians(p.AngIni-90));
		AffineTransform transf = g2d.getTransform();
		
		if(p.displayType == 1)
		{
			g2d.setTransform(transf);
			g.setColor(Color.RED);
			Rectangle spdPrf = new Rectangle(getScale(1), getScale(1-r3), getScale(8), getScale(15));
			g2d.rotate(Math.toRadians(p.Ang*15/(float)p.v_max*1));
			for(int i=15; i<(p.v_max-21); i+=1)
			{        
				g2d.fill(spdPrf);
				g2d.rotate(Math.toRadians(p.Ang/(float)(p.v_max)*1));
			}
			g2d.setTransform(transf);
			g.setColor(Color.RED);
			Rectangle lineRed = new Rectangle(getScale(-1), getScale(-r2-2), getScale(3.5f), getScale(r1-r2+30));
			g2d.rotate(Math.toRadians(p.Ang/(float)p.v_max*(p.v_max-15)));
			g2d.fill(lineRed);
			
			g2d.setStroke(bs2);
			g2d.setTransform(transf);
			g.setColor(Color.WHITE);
			Rectangle linePref = new Rectangle(getScale(+1), getScale(-r3-0.5), getScale(3.0f), getScale(r1-r2+1));
			g2d.rotate(Math.toRadians(p.Ang/(float)p.v_max*15f));
			g2d.fill(linePref);
			g2d.setTransform(transf);
			g.setColor(Color.WHITE);
			Rectangle line = new Rectangle(getScale(-1), getScale(-r1-2), getScale(1.51f), getScale(r1-r3+25));
			for(int i=0; i<=p.v_max; i+=10)
			{
				g2d.fill(line);
				g2d.rotate(Math.toRadians(p.Ang/(float)p.v_max*10));
			}
		
		}
		
		/*if(p.displayType == 1)
		{
			Rectangle line = new Rectangle(getScale(-1), getScale(-r1-2), getScale(1.51f), getScale(r1-r3+25));
			for(int i=0; i<=p.v_max; i+=10)
			{
				g2d.fill(line);
				g2d.rotate(Math.toRadians(p.Ang/(float)p.v_max*10));
			}
		}*/
		else if(p.displayType == 2)
		{
			g2d.setTransform(transf);
			g.setColor(Color.WHITE);
			Rectangle line = new Rectangle(getScale(-1), getScale(-r1-2), getScale(1.51f), getScale(r1-r3+10));
			for(int i=0; i<=p.v_max; i+=10)
			{
				g2d.fill(line);
				g2d.rotate(Math.toRadians(p.Ang/(float)p.v_max * 10));
			}
		}
		
		g2d.setStroke(bs3);
		g2d.setTransform(transf);
		g.setColor(p.prefColor);
		//Rectangle pref = new Rectangle(getScale(1), getScale(1-r1), getScale(6), getScale(15.9f));
		if(p.displayType == 1)
		{
			Rectangle pref = new Rectangle(getScale(1), getScale(0.6f-r1), getScale(7), getScale(16));
			for(int i=0; i+0.8<p.v_pref; i+=1)
			{
				g2d.fill(pref);
				g2d.rotate(Math.toRadians(p.Ang/(float)p.v_max * 1));
			}
		}
		else if(p.displayType == 2)
		{
			if(p.div == 1)
			{
				Rectangle pref = new Rectangle(getScale(1), getScale(1-r1), getScale(4), getScale(15f));
				for(int i=0; i<p.v_pref; i+=1)
				{
					g2d.fill(pref);
					g2d.rotate(Math.toRadians(p.Ang/(float)p.v_max * 1));
				}
			}
			else {
				if(p.UT449 != false) {
					Rectangle pref = new Rectangle(getScale(1), getScale(1 - r1), getScale(5.9), getScale(15f));
					for (int i = 0; i < p.v_pref; i += 2) {
						g2d.fill(pref);
						g2d.rotate(Math.toRadians(p.Ang / (float) p.v_max * 2));
					}
				}
				else
				{
					Rectangle pref = new Rectangle(getScale(1), getScale(1 - r1), getScale(8), getScale(15f));
					for (int i = 0; i < p.v_pref; i += 2) {
						g2d.fill(pref);
						g2d.rotate(Math.toRadians(p.Ang / (float) p.v_max * 2));
					}
				}
			}
		}
		
		g2d.setStroke(bs4);
		g2d.setTransform(transf);
		g.setColor(p.spdColor);
		if(p.displayType == 1)
		{
			Rectangle spd = new Rectangle(getScale(1), getScale(0.6f-r2), getScale(7), getScale(16));
			for(int i=0; i+0.8<p.v_act; i+=1)
			{
				g2d.fill(spd);
				g2d.rotate(Math.toRadians(p.Ang/(float)p.v_max*1));
			}
		}
		else if(p.displayType == 2)
		{
			if(p.div == 1)
			{
				Rectangle spd = new Rectangle(getScale(1), getScale(0.5f-r2), getScale(4), getScale(14f));
				for(int i=0; i<p.v_act; i+=1)
				{
					g2d.fill(spd);
					g2d.rotate(Math.toRadians(p.Ang/(float)p.v_max*1));
				}
			}
			else
			{
				if(p.UT449 != false)
				{
					Rectangle spd = new Rectangle(getScale(1), getScale(0.5f-r2), getScale(5.9f), getScale(14f));
					for(int i=0; i<p.v_act; i+=2)
					{
						g2d.fill(spd);
						g2d.rotate(Math.toRadians(p.Ang/(float)p.v_max*2));
					}
				}
				else
				{
					Rectangle spd = new Rectangle(getScale(1), getScale(0.5f-r2), getScale(8), getScale(14f));
					for(int i=0; i<p.v_act; i+=2)
					{
						g2d.fill(spd);
						g2d.rotate(Math.toRadians(p.Ang/(float)p.v_max*2));
					}
				}

			}
		}
		
	}
}
