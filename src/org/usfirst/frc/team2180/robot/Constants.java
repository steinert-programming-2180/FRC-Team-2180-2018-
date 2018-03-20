package org.usfirst.frc.team2180.robot;

public class Constants {
	
	public static final double kP = 10.0;
	public static final double kI = 0.00001;
	public static final double kD = 320.0;
	public static final double autonSpeed = 0.3;
	public static final int allowableAutonPositionError = 40;
	
//	public static final double gyroKP = 0.015; //for competition
	public static final double gyroKP = 0.018;
	public static final double gyroKI = 0.00001;
	public static final double gyroKD = 0.0;
	public static final double allowableGyroError = 2;
	
	public static final double elevatorKP = 0.42625;
	public static final double elevatorKI = 0.0;
	public static final double elevatorKD = 0.0;
	public static final int allowableElevatorError = 13;
	public static final double elevatorAutonSpeed = 1.0;
	
	public static final double wheelDiameter = 4;
	public static final int ticksPerRev = 1440;
}
