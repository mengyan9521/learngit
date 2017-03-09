package add.main;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class AddGUI extends JFrame{
	private static final int DEFAULT_WIDTH=300;
	private static final int DEFAULT_HEIGHT=200;
	private static final int DEFAULT_LOCAL_WIDTH=700;
	private static final int DEFAULT_LOCAL_HEIGHT=300;
	JButton bt1=new JButton("予約");
	JButton bt2=new JButton("開始");
	JTextField jt_1=new JTextField(10);
	JTextField jt_2=new JTextField(10);
	JLabel jl_1=new JLabel("     時");
	JLabel jl_2=new JLabel("     分");
	JPanel jp_1=new JPanel();
	JPanel jp_2=new JPanel();
	JPanel jp_3=new JPanel();
	Font f1=new Font("メイリオ", Font.BOLD, 20);
	public AddGUI(){
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		setLocation(DEFAULT_LOCAL_WIDTH, DEFAULT_HEIGHT);
		setTitle("再出品");
		mainFrame();
		mainPanel();
		bt1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int h=Integer.parseInt(jt_1.getText());
				int m=Integer.parseInt(jt_2.getText());;
				SetClock sc=new SetClock(h,m);
				sc.countTime();
			}
			
		});
		bt2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					try {
						new AddGoods().addGoods();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		setVisible(true);
	}
	public void mainPanel(){
		FlowLayout fl=new FlowLayout(FlowLayout.CENTER,10,10);
		jl_1.setFont(f1);
		jp_1.setLayout(fl);
		jp_1.add(jl_1);
		jp_1.add(jt_1);

		jl_2.setFont(f1);
		jp_2.setLayout(fl);
		jp_2.add(jl_2);
		jp_2.add(jt_2);
		
		jp_3.setLayout(fl);
		jp_3.add(bt1);
		jp_3.add(bt2);
	}
	public void mainFrame(){
		GridLayout gl=new GridLayout(3,1);
		setLayout(gl);
		add(jp_1);
		add(jp_2);
		add(jp_3);
	}
		
}