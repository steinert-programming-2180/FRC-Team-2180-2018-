/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2180.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2180.robot.commands.Spot1OneCube;
import org.usfirst.frc.team2180.robot.commands.Spot2OneCube;
import org.usfirst.frc.team2180.robot.commands.Spot3OneCube;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Robot extends TimedRobot {
		
	public static Command oneCubeAutonCommand;
	public static SendableChooser<Command> oneCubeAuton;
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
	public static DigitalInput limitTop, limitBottom;
	
	@Override
	public void robotInit() {
		
		left = new Joystick(0);
		right = new Joystick(1);
		
		regTalon1 = new WPI_TalonSRX(11);
		regTalon2 = new WPI_TalonSRX(22);
		regTalon3 = new WPI_TalonSRX(33);
		
		elevatorTalon = new WPI_TalonSRX(44);
		grabberFlipperTalon = new WPI_TalonSRX(55);
		
		grabberTalon1 = new WPI_TalonSRX(40);
		grabberTalon2 = new WPI_TalonSRX(50);
		 
		regTalon2.follow(regTalon1);
		regTalon3.follow(regTalon1);
		
		oneCubeAuton = new SendableChooser<>();
		SmartDashboard.putData("One Cube Auton", oneCubeAuton);
		
		gyro = new ADXRS450_Gyro();
		
		limitTop = new DigitalInput(0);
		limitBottom = new DigitalInput(1);
		
		setupDrivetrainPID();
		setupGyroPID();
		setupElevatorPID();
		
		oneCubeAuton.addDefault("Spot 1", new Spot1OneCube());
		oneCubeAuton.addDefault("Spot 2", new Spot2OneCube());
		oneCubeAuton.addDefault("Spot 3", new Spot3OneCube());
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
		oneCubeAutonCommand = oneCubeAuton.getSelected();

		if (oneCubeAutonCommand != null) {
			oneCubeAutonCommand.start();
		}

		talon1.setSelectedSensorPosition(0, 0, 10);
		
//		if (auton != null) {
//			auton.start();
//		}
	}

	@Override
	public void autonomousPeriodic() {
		
		SmartDashboard.putNumber("Position", talon1.getSelectedSensorPosition(0));
		
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {

		if (oneCubeAutonCommand != null) {
			oneCubeAutonCommand.cancel();
		}
		
		gyro.reset();
		
		talon1.configPeakOutputForward(1.0, 10);
		talon1.configPeakOutputReverse(-1.0, 10);
	}

	@Override
	public void teleopPeriodic() {
		
		talon1.set(-left.getRawAxis(1));
		regTalon1.set(-right.getRawAxis(1));
		
		SmartDashboard.putNumber("Gyro factor", gyro.getAngle()*Constants.gyroKP);
		
		SmartDashboard.putNumber("Teleop Talon Speed", regTalon1.getMotorOutputPercent());
		
		position = talon1.getSelectedSensorPosition(0);
		
		SmartDashboard.putNumber("Position", position);
		
		if (limitTop.get() || limitBottom.get()) {
			elevatorTalon.set(0.0);
		} else {
			elevatorTalon.set(payload.getRawAxis(1));
		}
		
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
		return (int) ((inches / (Constants.wheelDiameter * Math.PI)) * Constants.ticksPerRev);
	}
}