import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.tools.Tool;


public class oyun_dort extends JPanel implements KeyListener,Runnable{
	static JFrame f;
	boolean left,right,up,down;
	Image zemin,engel,bizimki,yem;
	static JPanel panel;
	static int hareket;
	int x,y;
	int engelx1,engely1,engelx2,engely2,yemx,yemy;
	int hareketx1=-3,hareketx2=3;//engellerimizin hareket g�c�
	static int widht,height;
	public oyun_dort() {
		hareket=2;//kontrol�m�zdeki nesnemizin hareketi g�c�
		x=40;//nesnemizin x konumu
		y=105;//nesnemizin y konumu
		zemin=Toolkit.getDefaultToolkit().getImage("seviye4.png");//resim atad�k
		widht=500;//zemin boyutu
		height=275;//zemin boyutu
		engelx1=75;//1.engel x konumu
		engelx2=250;//2. engel x konumu
		engely1=95;//1.engel y konumu
		engely2=engely1+24;//2.engel y konumu
		yemx=440;
		yemy=107;
		engel=Toolkit.getDefaultToolkit().getImage("mavi.png");//resim atad�k
		bizimki=Toolkit.getDefaultToolkit().getImage("kirmizi.png");//resim atad�k
		yem=Toolkit.getDefaultToolkit().getImage("yem.png");
		this.setBounds(0, 0, widht, height);
	
		panel=this;
		new Thread(this).start();
	}
	public static void main() {
	
		f =new JFrame();
		
		f.addKeyListener(new oyun_dort());
		f.add(panel);
		f.setVisible(true);
		f.setBounds(300, 50, widht, height);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(zemin, 0, 0, zemin.getWidth(null), zemin.getHeight(null), null);//zemin �izimi yapt�k
	
		g.drawImage(engel, engelx1,engely1, engel.getWidth(null), engel.getHeight(null), null);//1. engel �izimi yapt�k
		
		g.drawImage(engel, engelx1,engely2, engel.getWidth(null), engel.getHeight(null), null);//2. engel �izimi yapt�k
		
		g.drawImage(engel, engelx2,engely1, engel.getWidth(null), engel.getHeight(null), null);//3. engel �izimi yapt�k
		
		g.drawImage(engel, engelx2,engely2, engel.getWidth(null), engel.getHeight(null), null);//4. engel �izimi yapt�k
	
		g.drawImage(bizimki, x,y, bizimki.getWidth(null), bizimki.getHeight(null), null);//Kontrol etti�imiz nesnemizi �izdik
		
		g.drawImage(yem, yemx,yemy, yem.getWidth(null), yem.getHeight(null), null);
		
		
		
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {//sol tu�u bas�ld���nda left true yapt�k
			left=true;
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {//sa� tu�u bas�ld���nda right true yapt�k
			right=true;
		}
		if (e.getKeyCode()==KeyEvent.VK_UP) {//yukar� tu�u bas�ld���nda up true yapt�k
			up=true;
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {//a�a�� tu�u bas�ld���nda down true yapt�k
			down=true;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {//sol tu�u b�rakt���m�zda left'i false yapt�k
			left=false;
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {//sa� tu�u b�rakt���m�zda right false yapt�k
			right=false;
		}
		if (e.getKeyCode()==KeyEvent.VK_UP) {//yukar� tu�u b�rakt���m�zda up false yapt�k
			up=false;
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {//a�a�� tu�u b�rakt���m�zda down false yapt�k
			down=false;
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
		while (true) {
		try {
			Thread.sleep(18);//hamleler aras� bekleme s�resi girdik
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (engelx1 > 225 || engelx1 <75) {//1. engelin izledi�i yol s�n�rlar�
			hareketx1=-hareketx1;//1.engelimizin hareket g�c� ve y�n�
		}
		if (engelx2 > 400 || engelx2 <250) {//2. engelimizin izledi�i yol s�n�rlar�
			hareketx2=-hareketx2;//2.engelimizin hareket g�c� ve y�n�
		}
		engelx1+=hareketx1;//1.engelimizin hareket g�c� ve y�n�
		engelx2+=hareketx2;//2.engelimizin hareket g�c� ve y�n�
		repaint();
		
		Rectangle r =new Rectangle(x,y,bizimki.getWidth(null),bizimki.getHeight(null));//�arp��malar i�in konum ve boyut tan�mlama
		Rectangle r1 =new Rectangle(engelx1,engely1,engel.getWidth(null),engel.getHeight(null));
		Rectangle r2 =new Rectangle(engelx1,engely2,engel.getWidth(null),engel.getHeight(null));
		Rectangle r3 =new Rectangle(engelx2,engely1,engel.getWidth(null),engel.getHeight(null));
		Rectangle r4 =new Rectangle(engelx2,engely2,engel.getWidth(null),engel.getHeight(null));
		Rectangle r5 =new Rectangle(yemx,yemy,yem.getWidth(null),yem.getHeight(null));
		
		if (r.intersects(r1) ||r.intersects(r2) ||r.intersects(r3) ||r.intersects(r4) ) {//�arp��ma kontrol
			x=40;
			y=105;
			yemx=440;
			yemy=107;//�arp��ma olursa nesnemiz ba�lang�� noktas�na d�necek
		}
		if (r.intersects(r5)){
				yemx=0;
				yemy=0;
		}
		Rectangle bolumgec =new Rectangle(68	, 92, 1, 43);
		if (bolumgec.intersects(r)) {
			if((yemx==0)&(yemy==0)){
			f.setVisible(false);
			
			oyun_iki.main();
			break;
			}
		}
		
		
		if (left && up) {
			if (CakismaKotrol(x-hareket, 	y-hareket)) { //s�n�r ihlali kontrol et
				if (!CakismaKotrol(x, 	y-hareket)) {
					y-=hareket;//		y'de s�n�r ihlali yoksa y'de hareket et
					continue;
				}
				if (!CakismaKotrol(x-hareket, 	y)) {
					x-=hareket;//		x'de s�n�r ihlali yoksa x'de hareket et
					continue;
				}
				continue;
			}
			
			x-=hareket;//	s�n�r ihlali yoksa hareket et
			y-=hareket;//	s�n�r ihlali yoksa hareket et
			continue;
		}
		if (left && down) {
			
			if (CakismaKotrol(x-hareket, 	y+hareket)) {//s�n�r ihlali kontrol et
				
					if (!CakismaKotrol(x, 	y+hareket)) {
						y+=hareket;//	//		y'de s�n�r ihlali yoksa y'de hareket et
						continue;
					}
					if (!CakismaKotrol(x-hareket, 	y)) {
						x-=hareket;//		x'de s�n�r ihlali yoksa x'de hareket et
						continue;
					}
					continue;
				}
			x-=hareket;//	s�n�r ihlali yoksa hareket et
			y+=hareket;//	s�n�r ihlali yoksa hareket et
			continue;
		}
		if (right && up) {
			if (CakismaKotrol(x+hareket, 	y-hareket)) {//s�n�r ihlali kontrol et
				
				if (!CakismaKotrol(x, 	y-hareket)) {//		y'de s�n�r ihlali yoksa y'de hareket et
					y-=hareket;
					continue;
				}
				if (!CakismaKotrol(x+hareket, 	y)) {//		x'de s�n�r ihlali yoksa x'de hareket et
					x+=hareket;
					continue;
				}
				continue;
			}
			x+=hareket;//	s�n�r ihlali yoksa hareket et
			y-=hareket;//	s�n�r ihlali yoksa hareket et
			continue;
		}
		if (right && down) {
			if (CakismaKotrol(x+hareket, 	y+hareket)) {//s�n�r ihlali kontrol et
				
				
				if (!CakismaKotrol(x, 	y+hareket)) {//		y'de s�n�r ihlali yoksa y'de hareket et
					y+=hareket;
					continue;
				}
				if (!CakismaKotrol(x+hareket, 	y)) {//		x'de s�n�r ihlali yoksa x'de hareket et
					x+=hareket;
					continue;
				}
				continue;
			}
			x+=hareket;//	s�n�r ihlali yoksa hareket et
			y+=hareket;//	s�n�r ihlali yoksa hareket et
			continue;
		}
		if (left) {
			if (CakismaKotrol(x-hareket, 	y)) {	//s�n�r ihlali varsa hareket etme
				continue;
			}
			x-=hareket;//	s�n�r ihlali yoksa hareket et
		}
		if (right) {
			if (CakismaKotrol(x+hareket, 	y)) {	//s�n�r ihlali varsa hareket etme
				continue;
			}
			x+=hareket;//	s�n�r ihlali yoksa hareket et
		}
		if (up) {
			if (CakismaKotrol(x , 	y-hareket)) {	//s�n�r ihlali varsa hareket etme
				continue;
			}
			y-=hareket;//	s�n�r ihlali yoksa hareket et
		}
		if (down) {
			if (CakismaKotrol(x, 	y+hareket)) {	//s�n�r ihlali varsa hareket etme
				continue;
			}
			y+=hareket;//	s�n�r ihlali yoksa hareket et
		}
		
		
		
		
		}
		
	}
	private boolean CakismaKotrol(int x,int y) {
		boolean durum=false;
		/*	g.setColor(Color.RED);
		g.drawRect(20	, 62, 400, 1);
		g.drawRect(20	, 163, 400, 1);
		
		g.drawRect(20	, 88, 1, 50);
		g.drawRect(470	, 88, 1, 50);
		
		g.drawRect(20	, 62, 99, 25);
		g.drawRect(146	, 62, 124, 25);
		g.drawRect(297	, 62, 170, 25);
		
		g.drawRect(20	, 139, 174, 25);
		g.drawRect(222	, 139, 121, 25);
		g.drawRect(372	, 139, 99, 25);*/
		Rectangle [] r=new Rectangle[10];//harita s�n�rlar�m�z
		r[0]=new Rectangle(20	, 62, 400, 1);//harita s�n�rlar�m�z
		r[1]=new Rectangle(20	, 163, 400, 1);//harita s�n�rlar�m�z
		r[2]=new Rectangle(20	, 88, 1, 50);//harita s�n�rlar�m�z
		r[3]=new Rectangle(470	, 88, 1, 50);//harita s�n�rlar�m�z
		r[4]=new Rectangle(20	, 62, 99, 25);//harita s�n�rlar�m�z
		r[5]=new Rectangle(146	, 62, 124, 25);//harita s�n�rlar�m�z
		r[6]=new Rectangle(297	, 62, 170, 25);//harita s�n�rlar�m�z
		r[7]=new Rectangle(20	, 139, 174, 25);
		r[8]=new Rectangle(222	, 139, 121, 25);
		r[9]=new Rectangle(372	, 139, 99, 25);
		Rectangle r1 =new Rectangle(x, y, bizimki.getWidth(null), bizimki.getHeight(null));
		for (int i = 0; i <r.length; i++) {
			if (r1.intersects(r[i])) {//s�n�r ihlali yoksa, hareket etme durumunu onayl�yoruz
			durum=true;					//e�er ihlal varsa hareket etme onay� verilmiyor ve nesne hareket etmiyor
			}
		}
		
		return durum;

	}
}
