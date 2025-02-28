package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		// TODO - START
		
		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;

		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUT

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START
		
		double[] latitudes = new double[gpspoints.length];
		
		for(int i = 0; i < gpspoints.length; i++) {
			latitudes[i] = gpspoints[i].getLatitude();
		}
		return latitudes;
		
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START
		
		double[] longitudes = new double[gpspoints.length];
		
		for(int i = 0; i < gpspoints.length; i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		}
		return longitudes;

		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		// TODO - START
		latitude1 = gpspoint1.getLatitude();
		latitude2 = gpspoint2.getLatitude();
		longitude1 = gpspoint1.getLongitude();
		longitude2 = gpspoint2.getLongitude();
		
		double phi1 = toRadians(latitude1);
		double phi2 = toRadians(latitude2);
		double deltaPhi = toRadians(latitude2 - latitude1);
		double deltaLambda = toRadians(longitude2 - longitude1);
		double a = pow(sin(deltaPhi/2), 2) + cos(phi1)*cos(phi2)*pow(sin(deltaLambda/2), 2);
		double c = 2*atan2(sqrt(a), sqrt(1 - a));
		d = R*c;
		
		return d;

		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		// TODO - START
		secs = gpspoint2.getTime() - gpspoint1.getTime();
		speed = (distance(gpspoint1, gpspoint2)/secs)*3.6;
		
		return speed;

		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		// TODO - START
		
		int h = secs/(60*60);
		int m = secs/60 - h*60;
		int s = secs - m*60 - h*60*60;
		
		String hstr = String.format("%02d", h);
		String mstr = String.format("%02d", m);
		String sstr = String.format("%02d", s);
		
		String str = hstr + TIMESEP + mstr + TIMESEP + sstr;
		timestr =String.format("%" + TEXTWIDTH + "s", str);
		
		
		return timestr;

		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		// TODO - START
		
		double avrundet = Math.round(d*100.0)/100.0;
		str = Double.toString(avrundet);
		str = String.format("%" + TEXTWIDTH + "s", str);
		
		return str;

		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
	}
}
