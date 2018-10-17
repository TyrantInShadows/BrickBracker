package gg;

import javax.swing.JFrame;
public class Main {

	public static void main(String[] args) {
		JFrame JF=new JFrame();
		igrA igra=new igrA();
		JF.setBounds(100, 100, 700, 600);
		JF.setTitle("BrickBracker");
		JF.setResizable(false);
		JF.setVisible(true);
		JF.setDefaultCloseOperation(JF.EXIT_ON_CLOSE);
		JF.add(igra);

	}

}
