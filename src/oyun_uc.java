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


public class oyun_uc extends JPanel implements KeyListener,Runnable{
	static JFrame f;
	boolean left,right,up,down;
	Image zemin,engel,bizimki,yem;
	static JPanel panel;
	static int hareket;
	int x,y;
	int engelx1,engely1,engelx2,engely2,engelx3,engely3,yemx,yemy,yemx2,yemy2,yemx3,yemy3;
	int harekety1=-4,harekety2=4,harekety3=-4;//engellerimizin hareket gücü
	static int widht,height;
	public oyun_uc() {
		hareket=2;//kontrolümüzdeki nesnemizin hareketi gücü
		x=76;//nesnemizin x konumu
		y=115;//nesnemizin y konumu
		zemin=Toolkit.getDefaultToolkit().getImage("seviye3.png");//resim atadýk
		widht=500;//zemin boyutu
		height=275;//zemin boyutu
		engelx1=155;//1.engel x konumu
		engelx2=engelx1+48;
		engelx3=engelx2+48;//2. engel x konumu
		engely1=120;//1.engel y konumu
		engely2=engely1;//2.engel y konumu
		engely3=engely1;
		yemx=170;
		yemy=143;
		yemx2=270;
		yemy2=143;
		yemx3=220;
		yemy3=93;
		engel=Toolkit.getDefaultToolkit().getImage("mavi.png");//resim atadýk
		bizimki=Toolkit.getDefaultToolkit().getImage("kirmizi.png");//resim atadýk
		yem=Toolkit.getDefaultToolkit().getImage("yem.png");
		this.setBounds(0, 0, widht, height);
	
		panel=this;
		new Thread(this).start();
	}
	public static void main() {
	
		f =new JFrame();
		
		f.addKeyListener(new oyun_uc());
		f.add(panel);
		f.setVisible(true);
		f.setBounds(300, 50, widht, height);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(zemin, 0, 0, zemin.getWidth(null), zemin.getHeight(null), null);//zemin çizimi yaptýk
	
		g.drawImage(engel, engelx1,engely1, engel.getWidth(null), engel.getHeight(null), null);//1. engel çizimi yaptýk
		
		g.drawImage(engel, engelx1+24,engely1, engel.getWidth(null), engel.getHeight(null), null);//2. engel çizimi yaptýk
		
		g.drawImage(engel, engelx2,engely2, engel.getWidth(null), engel.getHeight(null), null);//3. engel çizimi yaptýk
		
		g.drawImage(engel, engelx2+24,engely2, engel.getWidth(null), engel.getHeight(null), null);//4. engel çizimi yaptýk
	
		g.drawImage(engel, engelx3,engely3, engel.getWidth(null), engel.getHeight(null), null);//3. engel çizimi yaptýk
		
		g.drawImage(engel, engelx3+24,engely3, engel.getWidth(null), engel.getHeight(null), null);
		
		g.drawImage(bizimki, x,y, bizimki.getWidth(null), bizimki.getHeight(null), null);//Kontrol ettiðimiz nesnemizi çizdik
	
		g.drawImage(yem, yemx, yemy, yem.getWidth(null), yem.getHeight(null), null);
		g.drawImage(yem, yemx2, yemy2, yem.getWidth(null), yem.getHeight(null), null);
		g.drawImage(yem, yemx3, yemy3, yem.getWidth(null), yem.getHeight(null), null);
	

		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {//sol tuþu basýldýðýnda left true yaptýk
			left=true;
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {//sað tuþu basýldýðýnda right true yaptýk
			right=true;
		}
		if (e.getKeyCode()==KeyEvent.VK_UP) {//yukarý tuþu basýldýðýnda up true yaptýk
			up=true;
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {//aþaðý tuþu basýldýðýnda down true yaptýk
			down=true;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {//sol tuþu býraktýðýmýzda left'i false yaptýk
			left=false;
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {//sað tuþu býraktýðýmýzda right false yaptýk
			right=false;
		}
		if (e.getKeyCode()==KeyEvent.VK_UP) {//yukarý tuþu býraktýðýmýzda up false yaptýk
			up=false;
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {//aþaðý tuþu býraktýðýmýzda down false yaptýk
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
			Thread.sleep(18);//hamleler arasý bekleme süresi girdik
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (engely1 > 180 || engely1 <52) {//1. engelin izlediði yol sýnýrlarý
			harekety1=-harekety1;//1.engelimizin hareket gücü ve yönü
		}
		if (engely2 > 180 || engely2 <52) {//2. engelimizin izlediði yol sýnýrlarý
			harekety2=-harekety2;//2.engelimizin hareket gücü ve yönü
		}
		if (engely3 > 180 || engely3 <52) {//2. engelimizin izlediði yol sýnýrlarý
			harekety3=-harekety3;//2.engelimizin hareket gücü ve yönü
		}
		engely1+=harekety1;//1.engelimizin hareket gücü ve yönü
		engely2+=harekety2;//2.engelimizin hareket gücü ve yönü
		engely3+=harekety3;
		repaint();
		
		Rectangle r =new Rectangle(x,y,bizimki.getWidth(null),bizimki.getHeight(null));//çarpýþmalar için konum ve boyut tanýmlama
		Rectangle r1 =new Rectangle(engelx1,engely1,engel.getWidth(null),engel.getHeight(null));
		Rectangle r2 =new Rectangle(engelx1+24,engely1,engel.getWidth(null),engel.getHeight(null));
		Rectangle r3 =new Rectangle(engelx2,engely2,engel.getWidth(null),engel.getHeight(null));
		Rectangle r4 =new Rectangle(engelx2+24,engely2,engel.getWidth(null),engel.getHeight(null));
		Rectangle r5 =new Rectangle(engelx3,engely3,engel.getWidth(null),engel.getHeight(null));
		Rectangle r6 =new Rectangle(engelx3+24,engely3,engel.getWidth(null),engel.getHeight(null));
		Rectangle r7 =new Rectangle(yemx,yemy,yem.getWidth(null),yem.getHeight(null));
		Rectangle r8 =new Rectangle(yemx2,yemy2,yem.getWidth(null),yem.getHeight(null));
		Rectangle r9 =new Rectangle(yemx3,yemy3,yem.getWidth(null),yem.getHeight(null));
		
		if (r.intersects(r1) ||r.intersects(r2) ||r.intersects(r3) ||r.intersects(r4)||r.intersects(r5)||r.intersects(r6) ) {//çarpýþma kontrol
			x=76;
			y=115;		
			yemx=170;
			yemy=143;
			yemx2=270;
			yemy2=143;
			yemx3=220;
			yemy3=93;//çarpýþma olursa nesnemiz baþlangýç noktasýna dönecek
		}
		if (r.intersects(r7)||r.intersects(r8)||r.intersects(r9)) {//çarpýþma kontrol
			if (r.intersects(r7)){
				yemx=0;
				yemy=0;
			}
			if (r.intersects(r8)){
				yemx2=0;
				yemy2=0;
			}
			if (r.intersects(r9)){
				yemx3=0;
				yemy3=0;
			}
		}
		Rectangle bolumgec =new Rectangle(350, 80, 1, 90);
		if (bolumgec.intersects(r)) {
			if((yemx==0)&(yemx2==0)){
			f.setVisible(false);
			
			oyun_dort.main();
			break;
			}
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
		g.drawRect(50	, 48, 1, 150);
		g.drawRect(400	, 48, 1, 150);
		g.drawRect(150	, 48, 150, 1);
		g.drawRect(150	, 198, 150, 1);
		g.drawRect(50	, 48, 100, 24);
		g.drawRect(50	, 173, 100, 24);
		g.drawRect(300	, 48, 100, 24);
		g.drawRect(300	, 173, 100, 24);
		*/
		Rectangle [] r=new Rectangle[8];//harita sýnýrlarýmýz
		r[0]=new Rectangle(50	, 48, 1, 150);//harita sýnýrlarýmýz
		r[1]=new Rectangle(400	, 48, 1, 150);//harita sýnýrlarýmýz
		r[2]=new Rectangle(150	, 48, 150, 1);//harita sýnýrlarýmýz
		r[3]=new Rectangle(150	, 198, 150, 1);//harita sýnýrlarýmýz
		r[4]=new Rectangle(50	, 48, 100, 24);//harita sýnýrlarýmýz
		r[5]=new Rectangle(50	, 173, 100, 24);//harita sýnýrlarýmýz
		r[6]=new Rectangle(300	, 48, 100, 24);//harita sýnýrlarýmýz
		r[7]=new Rectangle(300	, 173, 100, 24);//harita sýnýrlarýmýz
		Rectangle r1 =new Rectangle(x, y, bizimki.getWidth(null), bizimki.getHeight(null));
		for (int i = 0; i <r.length; i++) {
			if (r1.intersects(r[i])) {//sýnýr ihlali yoksa, hareket etme durumunu onaylýyoruz
			durum=true;					//eðer ihlal varsa hareket etme onayý verilmiyor ve nesne hareket etmiyor
			}
		}
		
		return durum;

	}
}
