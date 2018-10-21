package gg;

import java.awt.Color;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.event.*;
import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		JFrame JF=new JFrame();
		igrA igra=new igrA();
		JF.setBounds(100, 100, 700, 610);
		JF.setTitle("BrickBracker");
		JF.setResizable(false);
		JF.setVisible(true);
		JF.setDefaultCloseOperation(JF.EXIT_ON_CLOSE);
		JF.add(igra);
		JMenuBar MeniBar;
		JMenu HighScore;
		JMenu Exit;
		MeniBar=new JMenuBar();
		HighScore=new JMenu("HighScores");
		MeniBar.add(HighScore);
		JF.setJMenuBar(MeniBar);
		JF.add(igra);
		HighScore.addMenuListener(new MenuListener() {
				public void menuCanceled(MenuEvent e) {	
				}
				public void menuDeselected(MenuEvent e) {
				}
				public void menuSelected(MenuEvent e) {
						try {
							Desktop.getDesktop().open(new File("HighScores.txt"));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JFrame S=new JFrame();
						JPanel Sc=new JPanel();
						S.setBounds(100, 100, 200, 500);
						S.setTitle("HighScores");
						S.setResizable(false);
						S.setVisible(true);
						S.setDefaultCloseOperation(S.DISPOSE_ON_CLOSE);	
						S.add(Sc);
						Sc.setBackground(Color.white);
						Sc.setSize(200, 500);
						Sc.setLocation(100, 100);
						Sc.setVisible(true);
						int niz[]=new int[10];
						try {
							niz = NizTop();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						for(int i=0;i<10;i++)
						{
							JLabel L1=new JLabel();
							L1.setText(Integer.toString(niz[i]));
							Sc.add(L1);
							L1.setLocation(50,50*(i+1));
						}
					
				}
		}
		);
		
	}
	public static int[] NizTop() throws IOException
	{
		int[]niz=new int[10];
		FileReader fr=new FileReader("HighScores.txt");
		BufferedReader br=new BufferedReader(fr);
		int i=0;
		String a=br.readLine();
		while(a != null)
		{
			niz[i]=Integer.parseInt(a);
			i++;
			a=br.readLine();
		}
		br.close();
		return niz;
	}

}
