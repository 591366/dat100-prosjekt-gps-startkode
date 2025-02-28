package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {

	private GPSPoint[] gpspoints;

	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO - START
		//hadde ikke trengt if setningen i løkken, kunne heller skrevet gpspoints.length - 1 i løkken.
		//dette gjelder alle oppgavene nedover, men det fungerer likt.

		for (int i = 0; i < gpspoints.length; i++) {
			if (i != gpspoints.length - 1) {
				distance = distance + GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
			}
		}

		return distance;

		// throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		// TODO - START
		
		for (int i = 0; i < gpspoints.length; i++) {
			if (i != gpspoints.length - 1) {
				if (gpspoints[i + 1].getElevation() > gpspoints[i].getElevation()) {
					elevation = elevation + (gpspoints[i + 1].getElevation() - gpspoints[i].getElevation());
				}
			}
		}

		return elevation;

		// throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {

		int time = 0;
		
		for (int i = 0; i < gpspoints.length; i++) {
			if (i != gpspoints.length - 1) {
				time = time + (gpspoints[i + 1].getTime() - gpspoints[i].getTime());
			}
		}
		
		//alt.
		//time = gpspoints[gpspoints.length-1].getTime() - gpspoints[0].getTime();
		
		return time;
		//throw new UnsupportedOperationException(TODO.method());

	}

	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {

		// TODO - START // OPPGAVE - START
		
		double[] speeds = new double[gpspoints.length -1];
		
		for (int i = 0; i < gpspoints.length; i++) {
			if (i != gpspoints.length - 1) {
				speeds[i] = GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
			}
		}
		
		return speeds;

		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	public double maxSpeed() {

		double maxspeed = 0;

		// TODO - START

		for (int i = 0; i < gpspoints.length; i++) {
			if (i != gpspoints.length - 1) {
				if(GPSUtils.speed(gpspoints[i], gpspoints[i+1]) > maxspeed) {
					maxspeed = GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
				}
			}
		}
		
		return maxspeed;
		
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	public double averageSpeed() {

		double average = 0;

		// TODO - START
		/*
		for (int i = 0; i < gpspoints.length; i++) {
			if (i != gpspoints.length - 1) {
				average = average + GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
			}
		}
		*/
		average = (totalDistance()/totalTime())*3.6;
		
		//average = average/(gpspoints.length -1);
		
		return average;

		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling, general
	 * 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0 bicycling,
	 * 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9 mph, racing or
	 * leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph, racing/not drafting
	 * or >19 mph drafting, very fast, racing general 12.0 bicycling, >20 mph,
	 * racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;
		double speedmph = speed * MS;

		// TODO - START

		double hours = secs/Math.pow(60, 2);
		
		if(speedmph < 10.0) {
			met = 4.0;
		}else if(speedmph >= 10.0 && speedmph < 12.0) {
			met = 6.0;
		}else if(speedmph >= 12.0 && speedmph < 14.0) {
			met = 8.0;
		}else if(speedmph >= 14.0 && speedmph < 16.0) {
			met = 10.0;
		}else if(speedmph >= 16.0 && speedmph < 20.0) {
			met = 12.0;
		}else if(speedmph >= 20.0) {
			met = 16.0;
		}
		
		kcal = met*weight*hours;
		
		return kcal;
		
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO - START
		
		for (int i = 0; i < gpspoints.length - 1; i++) {
			totalkcal = totalkcal + kcal(weight, (gpspoints[i+1].getTime() - gpspoints[i].getTime()), GPSUtils.speed(gpspoints[i], gpspoints[i+1]));
		}
		
		return totalkcal;

		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	public static double WEIGHT = 80.0;

	public void displayStatistics() {

		System.out.println("==============================================");

		// TODO - START

		System.out.println("Total Time     :" + GPSUtils.formatTime(totalTime()));
		System.out.println("Total distance :" + GPSUtils.formatDouble(totalDistance()));
		System.out.println("Total elevation:" + GPSUtils.formatDouble(totalElevation()));
		System.out.println("Max speed      :" + GPSUtils.formatDouble(maxSpeed()));
		System.out.println("Average speed  :" + GPSUtils.formatDouble(averageSpeed()));
		System.out.println("Energy         :" + GPSUtils.formatDouble(totalKcal(WEIGHT)));
		
		System.out.println("==============================================");
		
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

}
