package main;

import java.awt.EventQueue;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import javax.swing.SwingWorker;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import view.MainWindow;

public class PlayMain {
	 static MainWindow frame;

	public static void main(String[] args) {
		System.out.println("hello"+465);
		if (RuntimeUtil.isWindows()) {
			NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "D:\\Program Files\\VideoLAN\\VLC");
		}
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					try {
						frame = new MainWindow();
						frame.setVisible(true);
						frame.getMediaPlayer().prepareMedia("E:\\1.mkv");
						frame.getMediaPlayer().setVolume(50);
						new SwingWorker<String, Integer>() {

							@Override
							protected String doInBackground() throws Exception {
								while(true){
								long total = frame.getMediaPlayer().getLength();
								long cur = frame.getMediaPlayer().getTime();
								float percent = (float)cur/total;
								publish((int)(percent*100));
								Thread.sleep(100);
								}
								
							}
							@Override
							protected void process(java.util.List<Integer> chunks)
							{for(int i:chunks){
									frame.getprogressBar().setValue(i);
								}};
								
						}.execute();
					
					
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	}
	public static void play() {
		frame.getMediaPlayer().play();
	}
	public static void pause() {
		frame.getMediaPlayer().pause();
	}

}
