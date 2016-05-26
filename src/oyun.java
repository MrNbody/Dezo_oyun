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


public class oyun extends JPanel implements KeyListener,Runnable{
	static JFrame f;
	boolean left,right,up,down;
	Image zemin,engel,bizimki;
	static JPanel panel;
	static int hareket;
	int x,y;
	int engelx1,engely1,engelx2,engely2;
	int hareketx1=-4,hareketx2=4;//engellerimizin hareket gücü
	static int widht,height;
	public oyun() {
		hareket=2;//kontrolümüzdeki nesnemizin hareketi gücü
		x=50;//nesnemizin x konumu
		y=100;//nesnemizin y konumu
		zemin=Toolkit.getDefaultToolkit().getImage("seviye1.png");//resim atadýk
		widht=500;//zemin boyutu
		height=275;//zemin boyutu
		engelx1=235;//1.engel x konumu
		engelx2=engelx1;//2. engel x konumu
		engely1=95;//1.engel y konumu
		engely2=123;//2.engel y konumu
		engel=Toolkit.getDefaultToolkit().getImage("mavi.png");//resim atadýk
		bizimki=Toolkit.getDefaultToolkit().getImage("kirmizi.png");//resim atadýk
		this.setBounds(0, 0, widht, height);
	
		panel=this;
		new Thread(this).start();
	}
	public static void main(String[] args) {
	
		f =new JFrame();
		
		f.addKeyListener(new oyun());
		f.add(panel);//Oluþturulan nesnelerin görünebilmesi için pencereye ya da pencere üzerine daha önceden eklenmiþ bir nesneye eklenmesi gerekir. Bu ifadeyle ben, paneli frame nesnesine ekledim.
		f.setVisible(true);//Frame görünürlüðünü ayarlar
		f.setBounds(300, 50, widht, height);// Nesne koordinatlarýný ve boyutlarý
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Pencereyi kapattýðýnýzda programýn da kapanmasýný saðlar, Aksi takdirde program arka planda çalýþýyor olacaktýr
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(zemin, 0, 0, zemin.getWidth(null), zemin.getHeight(null), null);//zemin çizimi yaptýk
	
		g.drawImage(engel, engelx1,engely1, engel.getWidth(null), engel.getHeight(null), null);//1. engel çizimi yaptýk
		
		g.drawImage(engel, engelx2,engely2, engel.getWidth(null), engel.getHeight(null), null);//2. engel çizimi yaptýk
		
		g.drawImage(engel, engelx1,engely1+48, engel.getWidth(null), engel.getHeight(null), null);//3. engel çizimi yaptýk
		
		g.drawImage(engel, engelx2,engely2+48, engel.getWidth(null), engel.getHeight(null), null);//4. engel çizimi yaptýk
	
		g.drawImage(bizimki, x,y, bizimki.getWidth(null), bizimki.getHeight(null), null);//Kontrol ettiðimiz nesnemizi çizdik
		
		
		
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
			// Hatalarý burda yakalýycaz
            // ve hatalarý fýrlatýcaz catch bloklarý ilede yakalýycaz.
			Thread.sleep(18);//hamleler arasý bekleme süresi girdik
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// Exception e tüm hatayý yakalar ve blogundaki kodu çalýþtýr.
			e.printStackTrace();
            // bu kodla hatayý ilgilendiren exception u
            // ve hatanýn bulundugu satýrý gösterir.
		}
		if (engelx1 > 350 || engelx1 <120) {//1. engelin izlediði yol sýnýrlarý
			hareketx1=-hareketx1;//1.engelimizin hareket gücü ve yönü
		}
		if (engelx2 > 350 || engelx2 <120) {//2. engelimizin izlediði yol sýnýrlarý
			hareketx2=-hareketx2;//2.engelimizin hareket gücü ve yönü
		}
		engelx1+=hareketx1;//1.engelimizin hareket gücü ve yönü
		engelx2+=hareketx2;//2.engelimizin hareket gücü ve yönü
		repaint();//sürekli çizmesi için
		
		Rectangle r =new Rectangle(x,y,bizimki.getWidth(null),bizimki.getHeight(null));//çarpýþmalar için konum ve boyut tanýmlama
		Rectangle r1 =new Rectangle(engelx1,engely1,engel.getWidth(null),engel.getHeight(null));
		Rectangle r2 =new Rectangle(engelx2,engely2,engel.getWidth(null),engel.getHeight(null));
		Rectangle r3 =new Rectangle(engelx1,engely1+48,engel.getWidth(null),engel.getHeight(null));
		Rectangle r4 =new Rectangle(engelx2,engely2+48,engel.getWidth(null),engel.getHeight(null));
		
		if (r.intersects(r1) ||r.intersects(r2) ||r.intersects(r3) ||r.intersects(r4) ) {//çarpýþma kontrol
			x=50;
			y=100;							//çarpýþma olursa nesnemiz baþlangýç noktasýna dönecek
		}
		Rectangle bolumgec =new Rectangle(390, 65, 1, 30);
		if (bolumgec.intersects(r)) {
			f.setVisible(false);
			
			oyun_iki.main();
			break;
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
		g.drawRect(16	, 50, 0, 216);
		g.drawRect(92	, 50, 23, 138);
		g.drawRect(367	, 90, 23, 138);
		g.drawRect(16	, 48, 450, 16);
		g.drawRect(16	, 215, 450, 16);
		
		g.drawRect(110	, 72, 230, 16);
		g.drawRect(142	, 190, 250, 20);*/
		Rectangle [] r=new Rectangle[7];//harita sýnýrlarýmýz
		r[0]=new Rectangle(16	, 50, 1, 216);//harita sýnýrlarýmýz
		r[1]=new Rectangle(92	, 50, 23, 138);//harita sýnýrlarýmýz
		r[2]=new Rectangle(367	, 90, 23, 138);//harita sýnýrlarýmýz
		r[3]=new Rectangle(16	, 48, 450, 16);//harita sýnýrlarýmýz
		r[4]=new Rectangle(16	, 215, 450, 16);//harita sýnýrlarýmýz
		r[5]=new Rectangle(110	, 72, 230, 16);//harita sýnýrlarýmýz
		r[6]=new Rectangle(142	, 190, 250, 20);//harita sýnýrlarýmýz
		Rectangle r1 =new Rectangle(x, y, bizimki.getWidth(null), bizimki.getHeight(null));
		for (int i = 0; i <r.length; i++) {
			if (r1.intersects(r[i])) {//sýnýr ihlali yoksa, hareket etme durumunu onaylýyoruz
			durum=true;					//eðer ihlal varsa hareket etme onayý verilmiyor ve nesne hareket etmiyor
			}
		}
		
		return durum;

	}
}
