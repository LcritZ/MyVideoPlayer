package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	EmbeddedMediaPlayerComponent playerComponent;
	private JPanel panel;
	private JButton btnPlay;
	private JButton btnPause;
	private JButton btnExit;
	private JPanel controlPanel;
	private JProgressBar progressBar;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmOpen;
	private JMenuItem mntmExit;
	private JMenu mnPlay;
	private JMenuItem mntmPause;
	private JMenuItem mntmSpeed;
	private JMenuItem mntmVoice;
	private JMenuItem mntmVoice_1;
	private JMenu mnAbout;
	private JMenuItem mntmSoftwareinfo;
	private JMenuItem mntmHelp;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 380);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmOpen = new JMenuItem("OPEN");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				int v = chooser.showOpenDialog(null);
				if(v == chooser.APPROVE_OPTION){
					File file = chooser.getSelectedFile();
					playerComponent.getMediaPlayer().playMedia(file.getAbsolutePath());
				}
			}
		});
		mnFile.add(mntmOpen);
		
		mntmExit = new JMenuItem("EXIT");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		mnPlay = new JMenu("Play");
		menuBar.add(mnPlay);
		
		mntmPause = new JMenuItem("pause");
		mnPlay.add(mntmPause);
		
		mntmSpeed = new JMenuItem("speed");
		mnPlay.add(mntmSpeed);
		
		mntmVoice = new JMenuItem("voice+");
		mnPlay.add(mntmVoice);
		
		mntmVoice_1 = new JMenuItem("voice-");
		mnPlay.add(mntmVoice_1);
		
		mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		mntmSoftwareinfo = new JMenuItem("SoftwareInfo");
		mnAbout.add(mntmSoftwareinfo);
		
		mntmHelp = new JMenuItem("Help");
		mnAbout.add(mntmHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel videopane = new JPanel();
		contentPane.add(videopane, BorderLayout.CENTER);
		videopane.setLayout(new BorderLayout(0, 0));
		
		playerComponent = new EmbeddedMediaPlayerComponent();
		videopane.add(playerComponent,BorderLayout.CENTER);
		
		panel = new JPanel();
		videopane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		controlPanel = new JPanel();
		panel.add(controlPanel, BorderLayout.CENTER);
		
		btnPlay = new JButton("play");
		controlPanel.add(btnPlay);
		
		btnPause = new JButton("pause");
		controlPanel.add(btnPause);
		
		btnExit = new JButton("exit");
		controlPanel.add(btnExit);
		
		progressBar = new JProgressBar();
		progressBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				long settime = (long)(((float)x/progressBar.getWidth())*playerComponent.getMediaPlayer().getLength());
				playerComponent.getMediaPlayer().setTime(settime);
			}
			
		});
		progressBar.setStringPainted(true);
		panel.add(progressBar, BorderLayout.NORTH);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().stop();
			}
		});
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().pause();
			}
		});
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getMediaPlayer().play();
			}
		});
	}
	
	public EmbeddedMediaPlayer getMediaPlayer() {
		return playerComponent.getMediaPlayer();
		
	}
	public JProgressBar getprogressBar(){
		return progressBar;
	}

}
