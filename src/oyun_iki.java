import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class oyun_iki extends JPanel implements KeyListener,Runnable{
	static JFrame f;
	boolean left,right,up,down;
	Image zemin,engel,bizimki,yem;
	static JPanel panel;
	static int hareket;
	int x,y,engelx1,engely1,engelx2,engely2,yemx,yemx2,yemy,yemy2;
	int hareketx1=-5,hareketx2=5;
	static int widht,height;
	public oyun_iki() {
		
		hareket=2;
		x=75;
		y=40;
		zemin=Toolkit.getDefaultToolkit().getImage("saviye2.png");
		widht=500;
		height=275;
		engelx1=223;
		engelx2=engelx1;
		engely1=80;
		engely2=129;
		yemx=223;
		yemx2=yemx;
		yemy=93;
		yemy2=143;
		engel=Toolkit.getDefaultToolkit().getImage("mavi.png");
		bizimki=Toolkit.getDefaultToolkit().getImage("kirmizi.png");
		yem=Toolkit.getDefaultToolkit().getImage("yem.png");
		this.setBounds(0, 0, widht, height);
		panel=this;
		new Thread(this).start();
		
	}
	

	public static void main() {
		// TODO Auto-generated method stub
		
		/*g.setColor(Color.RED);
		g.drawRect(16	, 50, 0, 216);
		g.drawRect(92	, 50, 23, 138);
		g.drawRect(367	, 90, 23, 138);
		g.drawRect(16	, 48, 450, 16);
		g.drawRect(16	, 215, 450, 16);
		
		g.drawRect(110	, 72, 230, 16);
		g.drawRect(142	, 190, 250, 20);*/
		
		f=new JFrame();
		f.addKeyListener(new oyun_iki());
		f.add(panel);
		f.setVisible(true);
		f.setBounds(300, 50, widht, height);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(zemin, 0, 0, zemin.getWidth(null), zemin.getHeight(null), null);
		g.drawImage(engel, engelx1,engely1,engel.getWidth(null),engel.getHeight(null), null);
		g.drawImage(engel,engelx1,engely1+24,engel.getWidth(null),engel.getHeight(null), null);
		
		g.drawImage(engel, engelx2,engely2,engel.getWidth(null),engel.getHeight(null), null);
		g.drawImage(engel, engelx2,engely2+24,engel.getWidth(null),engel.getHeight(null), null);
		g.drawImage(bizimki, x, y, bizimki.getWidth(null), bizimki.getHeight(null), null);
		g.drawImage(yem, yemx, yemy, yem.getWidth(null), yem.getHeight(null), null);
		g.drawImage(yem, yemx2, yemy2, yem.getWidth(null), yem.getHeight(null), null);
		
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try{
				Thread.sleep(18);
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (engelx1 > 385 || engelx1 <50) {
				hareketx1=-hareketx1;
			}
			if (engelx2 > 385 || engelx2 <50) {
				hareketx2=-hareketx2;
			}
			engelx1+=hareketx1;
			engelx2+=hareketx2;
			repaint();
			
			Rectangle r =new Rectangle(x,y,bizimki.getWidth(null),bizimki.getHeight(null));//çarpýþmalar için konum ve boyut tanýmlama
			Rectangle r1 =new Rectangle(engelx1,engely1,engel.getWidth(null),engel.getHeight(null));
			Rectangle r2 =new Rectangle(engelx1,engely1+24,engel.getWidth(null),engel.getHeight(null));
			Rectangle r3 =new Rectangle(engelx2,engely2,engel.getWidth(null),engel.getHeight(null));
			Rectangle r4 =new Rectangle(engelx2,engely2+24,engel.getWidth(null),engel.getHeight(null));
			Rectangle r5 =new Rectangle(yemx,yemy,yem.getWidth(null),yem.getHeight(null));
			Rectangle r6 =new Rectangle(yemx2,yemy2,yem.getWidth(null),yem.getHeight(null));
			
			if (r.intersects(r1) ||r.intersects(r2) ||r.intersects(r3) ||r.intersects(r4) ) {//çarpýþma kontrol
				x=75;
				y=40;
				yemx=223;
				yemx2=223;
				yemy=98;
				yemy2=148;//çarpýþma olursa nesnemiz baþlangýç noktasýna dönecek
			}
			if (r.intersects(r5)||r.intersects(r6)) {//çarpýþma kontrol
				if (r.intersects(r5)){
					yemx=0;
					yemy=0;
				}
				if (r.intersects(r6)){
					yemx2=0;
					yemy2=0;
				}
			}
			Rectangle bolumgec =new Rectangle(328	, 172, 68, 1);
			if (bolumgec.intersects(r)) {
				if((yemx==0)&(yemx2==0)){
				f.setVisible(false);
				
				oyun_uc.main();
				break;}
			}
			
			
			if (left && up) {
				if (CakismaKotrol(x-hareket, 	y-hareket)) { //sýnýr ihlali kontrol et
					if (!CakismaKotrol(x, 	y-hareket)) {
						y-=hareket;//		y'de sýnýr ihlali yoksa y'de hareket et
						continue;
					}
					if (!CakismaKotrol(x-hareket, 	y)) {
						x-=hareket;//		x'de sýnýr ihlali yoksa x'de hareket et
						continue;
					}
					continue;
				}
				
				x-=hareket;//	sýnýr ihlali yoksa hareket et
				y-=hareket;//	sýnýr ihlali yoksa hareket et
				continue;
			}
			if (left && down) {
				
				if (CakismaKotrol(x-hareket, 	y+hareket)) {//sýnýr ihlali kontrol et
					
						if (!CakismaKotrol(x, 	y+hareket)) {
							y+=hareket;//	//		y'de sýnýr ihlali yoksa y'de hareket et
							continue;
						}
						if (!CakismaKotrol(x-hareket, 	y)) {
							x-=hareket;//		x'de sýnýr ihlali yoksa x'de hareket et
							continue;
						}
						continue;
					}
				x-=hareket;//	sýnýr ihlali yoksa hareket et
				y+=hareket;//	sýnýr ihlali yoksa hareket et
				continue;
			}
			if (right && up) {
				if (CakismaKotrol(x+hareket, 	y-hareket)) {//sýnýr ihlali kontrol et
					
					if (!CakismaKotrol(x, 	y-hareket)) {//		y'de sýnýr ihlali yoksa y'de hareket et
						y-=hareket;
						continue;
					}
					if (!CakismaKotrol(x+hareket, 	y)) {//		x'de sýnýr ihlali yoksa x'de hareket et
						x+=hareket;
						continue;
					}
					continue;
				}
				x+=hareket;//	sýnýr ihlali yoksa hareket et
				y-=hareket;//	sýnýr ihlali yoksa hareket et
				continue;
			}
			if (right && down) {
				if (CakismaKotrol(x+hareket, 	y+hareket)) {//sýnýr ihlali kontrol et
					
					
					if (!CakismaKotrol(x, 	y+hareket)) {//		y'de sýnýr ihlali yoksa y'de hareket et
						y+=hareket;
						continue;
					}
					if (!CakismaKotrol(x+hareket, 	y)) {//		x'de sýnýr ihlali yoksa x'de hareket et
						x+=hareket;
						continue;
					}
					continue;
				}
				x+=hareket;//	sýnýr ihlali yoksa hareket et
				y+=hareket;//	sýnýr ihlali yoksa hareket et
				continue;
			}
			if (left) {
				if (CakismaKotrol(x-hareket, 	y)) {	//sýnýr ihlali varsa hareket etme
					continue;
				}
				x-=hareket;//	sýnýr ihlali yoksa hareket et
			}
			if (right) {
				if (CakismaKotrol(x+hareket, 	y)) {	//sýnýr ihlali varsa hareket etme
					continue;
				}
				x+=hareket;//	sýnýr ihlali yoksa hareket et
			}
			if (up) {
				if (CakismaKotrol(x , 	y-hareket)) {	//sýnýr ihlali varsa hareket etme
					continue;
				}
				y-=hareket;//	sýnýr ihlali yoksa hareket et
			}
			if (down) {
				if (CakismaKotrol(x, 	y+hareket)) {	//sýnýr ihlali varsa hareket etme
					continue;
				}
				y+=hareket;//	sýnýr ihlali yoksa hareket et
			}
			
		}
		
		
		
	}
	private boolean CakismaKotrol(int x,int y) {
		boolean durum=false;
		/*	g.setColor(Color.RED);
		g.drawRect(48	, 23, 1, 155);
		g.drawRect(48	, 18, 80, 4);
		g.drawRect(400	, 72, 3, 150);
		g.drawRect(123	, 18, 280, 55);
		g.drawRect(45	, 173, 280, 55);*/
		Rectangle [] r=new Rectangle[5];//harita sýnýrlarýmýz
		r[0]=new Rectangle(48	, 23, 1, 155);//harita sýnýrlarýmýz
		r[1]=new Rectangle(48	, 18, 80, 4);//harita sýnýrlarýmýz
		r[2]=new Rectangle(400	, 72, 3, 150);//harita sýnýrlarýmýz
		r[3]=new Rectangle(123	, 18, 280, 55);//harita sýnýrlarýmýz
		r[4]=new Rectangle(45	, 173, 280, 55);//harita sýnýrlarýmýz
		Rectangle r1 =new Rectangle(x, y, bizimki.getWidth(null), bizimki.getHeight(null));
		for (int i = 0; i <r.length; i++) {
			if (r1.intersects(r[i])) {//sýnýr ihlali yoksa, hareket etme durumunu onaylýyoruz
			durum=true;					//eðer ihlal varsa hareket etme onayý verilmiyor ve nesne hareket etmiyor
			}
		}
		return durum;

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
			left=true;
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
			right=true;
		if(e.getKeyCode()==KeyEvent.VK_UP)
			up=true;
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
			down=true;
		
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
			left=false;
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
			right=false;
		if(e.getKeyCode()==KeyEvent.VK_UP)
			up=false;
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
			down=false;
			
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	

}
