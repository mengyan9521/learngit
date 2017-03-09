package add.main;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

/////ó\ñÒéûä‘ÇÃê›íË
public class SetClock {
	private int needHour=0;
	private int needMinutes=0;
	Timer time=new Timer();
	public SetClock(int h,int m){
		needHour=h;
		needMinutes=m;
	}
	public void countTime(){
		time.schedule(new RemindTask(),0,1000);
	}
	public String getTime1(){
		String time=null;
		Calendar c=Calendar.getInstance();
		Date date=(Date)c.getTime();
		SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
		time=format.format(date);
		return time;
	}
	private class RemindTask extends TimerTask{
		public void run(){
			String timeStr=getTime1();
			String timerSet=needHour+":"+needMinutes+":00";
			System.out.println(timeStr);
			if(timeStr.equals(timerSet)){
				time.cancel();
				try {
					new AddGoods().addGoods();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
