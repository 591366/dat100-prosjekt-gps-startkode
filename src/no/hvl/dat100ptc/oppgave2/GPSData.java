package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {

		// TODO - START
		
		gpspoints = new GPSPoint[n];
		antall = 0;
		
		//throw new UnsupportedOperationException(TODO.construtor("GPSData"));

		// TODO - SLUTT
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = false;

		// TODO - START
		if(this.antall < this.gpspoints.length) {
			this.gpspoints[this.antall] = gpspoint;
			inserted = true;
			antall++;
		} 
		
		return inserted;
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		GPSPoint gpspoint;

		// TODO - START
		
		gpspoint = GPSDataConverter.convert(time, latitude, longitude, elevation);
		
		return insertGPS(gpspoint);
		
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
	}

	public void print() {

		System.out.println("====== Konvertert GPS Data - START ======");

		// TODO - START
		
		for(int i = 0; i < this.gpspoints.length; i++) {
			String timestr = Integer.toString(this.gpspoints[i].getTime());
			String latitudestr = Double.toString(this.gpspoints[i].getLatitude());
			String longitudestr = Double.toString(this.gpspoints[i].getLongitude());
			String elevationstr = Double.toString(this.gpspoints[i].getElevation());
			
			System.out.println(timestr + " (" + latitudestr + "," + longitudestr + ") " + elevationstr);
			
			//burde bare brukt toString fra GPSPoint
			
			
		}

		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
		 System.out.println("====== Konvertert GPS Data - SLUTT ======");

	}
}
