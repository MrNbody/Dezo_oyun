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
	int hareketx1=-4,hareketx2=4;//engellerimizin hareket g�c�
	static int widht,height;
	public oyun() {
		hareket=2;//kontrol�m�zdeki nesnemizin hareketi g�c�
		x=50;//nesnemizin x konumu
		y=100;//nesnemizin y konumu
		zemin=Toolkit.getDefaultToolkit().getImage("seviye1.png");//resim atad�k
		widht=500;//zemin boyutu
		height=275;//zemin boyutu
		engelx1=235;//1.engel x konumu
		engelx2=engelx1;//2. engel x konumu
		engely1=95;//1.engel y konumu
		engely2=123;//2.engel y konumu
		engel=Toolkit.getDefaultToolkit().getImage("mavi.png");//resim atad�k
		bizimki=Toolkit.getDefaultToolkit().getImage("kirmizi.png");//resim atad�k
		this.setBounds(0, 0, widht, height);
	
		panel=this;
		new Thread(this).start();
	}
	public static void main(String[] args) {
	
		f =new JFrame();
		
		f.addKeyListener(new oyun());
		f.add(panel);//Olu�turulan nesnelerin g�r�nebilmesi i�in pencereye ya da pencere �zerine daha �nceden eklenmi� bir nesneye eklenmesi gerekir. Bu ifadeyle ben, paneli frame nesnesine ekledim.
		f.setVisible(true);//Frame g�r�n�rl���n� ayarlar
		f.setBounds(300, 50, widht, height);// Nesne koordinatlar�n� ve boyutlar�
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Pencereyi kapatt���n�zda program�n da kapanmas�n� sa�lar, Aksi takdirde program arka planda �al���yor olacakt�r
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(zemin, 0, 0, zemin.getWidth(null), zemin.getHeight(null), null);//zemin �izimi yapt�k
	
		g.drawImage(engel, engelx1,engely1, engel.getWidth(null), engel.getHeight(null), null);//1. engel �izimi yapt�k
		
		g.drawImage(engel, engelx2,engely2, engel.getWidth(null), engel.getHeight(null), null);//2. engel �izimi yapt�k
		
		g.drawImage(engel, engelx1,engely1+48, engel.getWidth(null), engel.getHeight(null), null);//3. engel �izimi yapt�k
		
		g.drawImage(engel, engelx2,engely2+48, engel.getWidth(null), engel.getHeight(null), null);//4. engel �izimi yapt�k
	
		g.drawImage(bizimki, x,y, bizimki.getWidth(null), bizimki.getHeight(null), null);//Kontrol etti�imiz nesnemizi �izdik
		
		
		
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
			// Hatalar� burda yakal�ycaz
            // ve hatalar� f�rlat�caz catch bloklar� ilede yakal�ycaz.
			Thread.sleep(18);//hamleler aras� bekleme s�resi girdik
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// Exception e t�m hatay� yakalar ve blogundaki kodu �al��t�r.
			e.printStackTrace();
            // bu kodla hatay� ilgilendiren exception u
            // ve hatan�n bulundugu sat�r� g�sterir.
		}
		if (engelx1 > 350 || engelx1 <120) {//1. engelin izledi�i yol s�n�rlar�
			hareketx1=-hareketx1;//1.engelimizin hareket g�c� ve y�n�
		}
		if (engelx2 > 350 || engelx2 <120) {//2. engelimizin izledi�i yol s�n�rlar�
			hareketx2=-hareketx2;//2.engelimizin hareket g�c� ve y�n�
		}
		engelx1+=hareketx1;//1.engelimizin hareket g�c� ve y�n�
		engelx2+=hareketx2;//2.engelimizin hareket g�c� ve y�n�
		repaint();//s�rekli �izmesi i�in
		
		Rectangle r =new Rectangle(x,y,bizimki.getWidth(null),bizimki.getHeight(null));//�arp��malar i�in konum ve boyut tan�mlama
		Rectangle r1 =new Rectangle(engelx1,engely1,engel.getWidth(null),engel.getHeight(null));
		Rectangle r2 =new Rectangle(engelx2,engely2,engel.getWidth(null),engel.getHeight(null));
		Rectangle r3 =new Rectangle(engelx1,engely1+48,engel.getWidth(null),engel.getHeight(null));
		Rectangle r4 =new Rectangle(engelx2,engely2+48,engel.getWidth(null),engel.getHeight(null));
		
		if (r.intersects(r1) ||r.intersects(r2) ||r.intersects(r3) ||r.intersects(r4) ) {//�arp��ma kontrol
			x=50;
			y=100;							//�arp��ma olursa nesnemiz ba�lang�� noktas�na d�necek
		}
		Rectangle bolumgec =new Rectangle(390, 65, 1, 30);
		if (bolumgec.intersects(r)) {
			f.setVisible(false);
			
			oyun_iki.main();
			break;
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
		g.drawRect(16	, 50, 0, 216);
		g.drawRect(92	, 50, 23, 138);
		g.drawRect(367	, 90, 23, 138);
		g.drawRect(16	, 48, 450, 16);
		g.drawRect(16	, 215, 450, 16);
		
		g.drawRect(110	, 72, 230, 16);
		g.drawRect(142	, 190, 250, 20);*/
		Rectangle [] r=new Rectangle[7];//harita s�n�rlar�m�z
		r[0]=new Rectangle(16	, 50, 1, 216);//harita s�n�rlar�m�z
		r[1]=new Rectangle(92	, 50, 23, 138);//harita s�n�rlar�m�z
		r[2]=new Rectangle(367	, 90, 23, 138);//harita s�n�rlar�m�z
		r[3]=new Rectangle(16	, 48, 450, 16);//harita s�n�rlar�m�z
		r[4]=new Rectangle(16	, 215, 450, 16);//harita s�n�rlar�m�z
		r[5]=new Rectangle(110	, 72, 230, 16);//harita s�n�rlar�m�z
		r[6]=new Rectangle(142	, 190, 250, 20);//harita s�n�rlar�m�z
		Rectangle r1 =new Rectangle(x, y, bizimki.getWidth(null), bizimki.getHeight(null));
		for (int i = 0; i <r.length; i++) {
			if (r1.intersects(r[i])) {//s�n�r ihlali yoksa, hareket etme durumunu onayl�yoruz
			durum=true;					//e�er ihlal varsa hareket etme onay� verilmiyor ve nesne hareket etmiyor
			}
		}
		
		return durum;

	}
}
