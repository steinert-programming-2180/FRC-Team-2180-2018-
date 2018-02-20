/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2180.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2180.robot.commands.BaselineAuto;
import org.usfirst.frc.team2180.robot.commands.OneCubeAuto;
import org.usfirst.frc.team2180.robot.commands.ThreeCubeAuto;
import org.usfirst.frc.team2180.robot.commands.TwoCubeAuto;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Robot extends TimedRobot {
	
	public static SendableChooser<String> autoCommandChooser;
	public static SendableChooser<Integer> robotPositionChooser;
	public static String autoCommand;
	public static int robotPosition;
	public static WPI_TalonSRX talon1, talon2, talon3;
	public static WPI_TalonSRX regTalon1, regTalon2, regTalon3;
	public static WPI_TalonSRX elevatorTalon, grabberFlipperTalon;
	public static WPI_TalonSRX grabberTalon1, grabberTalon2;
	public static boolean sensorInPhase, motorInverted;
	public static double position;
	public static ADXRS450_Gyro gyro;
	public static PIDController gyroPID;
	public static PIDOutput gyroPIDOutput;
	public static Joystick left, right, payload;
	public static DigitalInput limitBottom;
	public static double elevatorOutput;
	
	@Override
	public void robotInit() {
		
		left = new Joystick(0);
		right = new Joystick(1);
		
		regTalon1 = new WPI_TalonSRX(11);
		regTalon2 = new WPI_TalonSRX(22);
		regTalon3 = new WPI_TalonSRX(33);
		
		elevatorTalon = new WPI_TalonSRX(40);
		grabberFlipperTalon = new WPI_TalonSRX(55);
		
		grabberTalon1 = new WPI_TalonSRX(44);
		grabberTalon2 = new WPI_TalonSRX(50);
		 
		regTalon2.follow(regTalon1);
		regTalon3.follow(regTalon1);
		
		gyro = new ADXRS450_Gyro();
		limitBottom = new DigitalInput(0);
		
		autoCommandChooser = new SendableChooser<>();
		autoCommandChooser.addDefault("One Cube", "One");
		autoCommandChooser.addObject("Two Cubes", "Two");
		autoCommandChooser.addObject("Three Cubes", "Three");
		autoCommandChooser.addObject("Cross Baseline", "Zero");
		autoCommandChooser.addObject("Do Nothing", null);
		SmartDashboard.putData("Pick An Autonomous Routine", autoCommandChooser);
		
		robotPositionChooser = new SendableChooser<>();
		robotPositionChooser.addDefault("1", new Integer(1));
		robotPositionChooser.addObject("2", new Integer(2));
		robotPositionChooser.addObject("3", new Integer(3));
		SmartDashboard.putData("Pick The Bot's Position", robotPositionChooser);
		
		setupDrivetrainPID();
		setupGyroPID();
		setupElevatorPID();
		
		elevatorOutput = 0.0;
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		
		talon1.setSelectedSensorPosition(0, 0, 10);
		
		robotPosition = robotPositionChooser.getSelected().intValue();
		autoCommand = autoCommandChooser.getSelected();
		
		if (autoCommand != null) {
			if (autoCommand == "One") {
				(new OneCubeAuto(robotPosition)).start();
			} else if (autoCommand == "Two") {
				(new TwoCubeAuto(robotPosition)).start();
			} else if (autoCommand ==  "Three") {
				(new ThreeCubeAuto(robotPosition)).start();
			} else if (autoCommand == "Zero") {
				(new BaselineAuto()).start();
			}
		}
	}

	@Override
	public void autonomousPeriodic() {
		
		SmartDashboard.putNumber("Position", talon1.getSelectedSensorPosition(0));
		
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		
		gyro.reset();
		
		talon1.configPeakOutputForward(1.0, 10);
		talon1.configPeakOutputReverse(-1.0, 10);
	}

	@Override
	public void teleopPeriodic() {
		
		talon1.set(-left.getRawAxis(1));
		regTalon1.set(-right.getRawAxis(1));
		
		if (right.getRawButton(1)) {
			elevatorOutput = -0.5;
			elevatorTalon.set(elevatorOutput);
		} else if (right.getRawButton(2)) {
			elevatorOutput = -1.0;
			elevatorTalon.set(elevatorOutput);
		} else if (right.getRawButton(3)) {
			elevatorOutput = 0.5;
			elevatorTalon.set(elevatorOutput);
		} else if (right.getRawButton(4)) {
			elevatorOutput = 1.0;
			elevatorTalon.set(elevatorOutput);
		} else if (right.getRawButton(11)) {
			elevatorTalon.configPeakOutputForward(0.4, 10);
			elevatorTalon.configPeakOutputReverse(-0.4, 10);
			elevatorTalon.set(ControlMode.Position, inchesToTicks(30));
		} else {
			elevatorTalon.configPeakOutputForward(1.0, 10);
			elevatorTalon.configPeakOutputReverse(-1.0, 10);
			elevatorTalon.set(ControlMode.PercentOutput, 0.0);
		}
		
//		if (limitBottom.get()) {
//			elevatorOutput = Math.max(elevatorOutput, 0);
//		} else if (elevatorTalon.getSelectedSensorPosition(0) > 12000) {
//			elevatorOutput = Math.min(elevatorOutput, 0);
//		}
		
//		if (right.getRawButton(5)) {
//			grabberTalon1.set(-1.0);
//			grabberTalon2.set(0.7);
//		} else {
//			grabberTalon1.set(0.0);
//			grabberTalon2.set(0.0);
//		}
		
		if (right.getRawButton(5)) {
			grabberTalon1.set(-1.0);
			grabberTalon2.set(0.7);
		} else if (right.getRawButton(6)) {
			grabberTalon1.set(1.0);
			grabberTalon2.set(-1.0);
		} else {
			grabberTalon1.set(0.0);
			grabberTalon2.set(0.0);
		}
		
		if (left.getRawButton(1)) {
			grabberFlipperTalon.set(0.4);
		} else {
			grabberFlipperTalon.set(0.0);
		}
		
		
		
		SmartDashboard.putNumber("Elevator Position", elevatorTalon.getSelectedSensorPosition(0));
		
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
	}
	
	public void setupDrivetrainPID() {
		
		sensorInPhase = false; // don't change these
		motorInverted = true; // don't change these
		
		// FOR CURIOSITY
		talon1 = new WPI_TalonSRX(10); // master
		talon2 = new WPI_TalonSRX(20);
		talon3 = new WPI_TalonSRX(30);
		
		talon2.follow(talon1); // a slave of talon1
		talon3.follow(talon1); // a slave of talon1
		
		talon1.setInverted(motorInverted);
		talon2.setInverted(motorInverted);
		talon3.setInverted(motorInverted);
		
		talon1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		talon1.setSensorPhase(sensorInPhase);
		
		talon1.configAllowableClosedloopError(0, Constants.allowableAutonPositionError, 10);
		
		talon1.configNominalOutputForward(0, 10);
		talon1.configNominalOutputReverse(0, 10);
		talon1.configPeakOutputForward(Constants.autonSpeed, 10);
		talon1.configPeakOutputReverse(-Constants.autonSpeed, 10);
		
		talon1.config_kF(0, 0.0, 10);
		talon1.config_kP(0, Constants.kP, 10);
		talon1.config_kI(0, Constants.kI, 10);
		talon1.config_kD(0, Constants.kD, 10);
	}
	
	public void setupGyroPID() {
		gyroPIDOutput = new PIDOutput() {
			public void pidWrite(double output) {
				// an empty trick!
			}
		};
		
		gyroPID = new PIDController(Constants.gyroKP, Constants.gyroKI, Constants.gyroKD, gyro, gyroPIDOutput);
//		gyroPID.setContinuous(true);
		gyroPID.setAbsoluteTolerance(Constants.allowableGyroError);
	}
	
	public void setupElevatorPID() {
		sensorInPhase = false;
		motorInverted = true;
		
		elevatorTalon.setInverted(motorInverted);
		
		elevatorTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		elevatorTalon.setSensorPhase(sensorInPhase);
		
		elevatorTalon.configAllowableClosedloopError(0, Constants.allowableElevatorError, 10);
		
		elevatorTalon.configNominalOutputForward(0, 10);
		elevatorTalon.configNominalOutputReverse(0, 10);
		elevatorTalon.configPeakOutputForward(1.0, 10);
		elevatorTalon.configPeakOutputReverse(-1.0, 10);
		
		elevatorTalon.config_kF(0, 0.0, 10);
		elevatorTalon.config_kP(0, Constants.elevatorKP, 10);
		elevatorTalon.config_kI(0, Constants.elevatorKI, 10);
		elevatorTalon.config_kD(0, Constants.elevatorKD, 10);
	}
	
	public static int inchesToTicks(double inches) {
		return (int) (((inches / (Constants.wheelDiameter * Math.PI)) * Constants.ticksPerRev));
	}
}