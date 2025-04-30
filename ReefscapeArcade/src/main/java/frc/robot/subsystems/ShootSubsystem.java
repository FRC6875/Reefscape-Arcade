
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.RelativeEncoder;

public class ShootSubsystem extends SubsystemBase {


// declare all of our motors. use the constants for the port

  // declare shooter motors
  SparkMax leftShoot = new SparkMax(Constants.ShootConstants.kLeftShootPort, MotorType.kBrushless);
  SparkMax rightShoot = new SparkMax(Constants.ShootConstants.kRightShootPort, MotorType.kBrushless);
  SparkMax rightShoot2 = new SparkMax(Constants.ShootConstants.kRightShootPort2, MotorType.kBrushed);
  SparkMax leftShoot2 = new SparkMax(Constants.ShootConstants.kLeftshootPort2, MotorType.kBrushed);

//declare shoot encoders
  RelativeEncoder leftShootEncoder;
  RelativeEncoder rightShootEncoder;

   SparkMaxConfig configShoot = new SparkMaxConfig();
   SparkMaxConfig rightconfigShoot = new SparkMaxConfig();
   SparkMaxConfig configShoot2 = new SparkMaxConfig();


 

  /** Creates a new ExampleSubsystem. */
  public ShootSubsystem() {
     
    //rightShootEncoder = rightShoot.getEncoder(Type.kHallSensor, 42);
    //leftShootEncoder = leftShoot.getEncoder(Type.kHallSensor, 42);

//set motor inversions
    configShoot
      .inverted(true)
      .idleMode(IdleMode.kCoast);
     
      rightconfigShoot
      .inverted(false)
      .idleMode(IdleMode.kCoast);

      configShoot2
      .inverted(true)
      .idleMode(IdleMode.kCoast);

     rightShoot2.configure(configShoot2,ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    leftShoot2.configure(configShoot2,ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    leftShoot.configure(configShoot,ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    rightShoot.configure(rightconfigShoot,ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

  }

  
  

  /**
   * Example command factory method.
   *
   * @return a command
   */

  public boolean getShootSpeed(double threshold){
    return (leftShoot.get() >= threshold) && (rightShoot.get() >= threshold);
  }

  public void runSims(double speed){
    leftShoot2.set(speed);
    rightShoot2.set(speed);
  }
 
  public void runNeos(double speed){
    leftShoot.set(speed);
    rightShoot.set(speed);

  }


  public void stopShoot() {

    leftShoot.set(0);
    rightShoot.set(0);
    leftShoot2.set(0);
    rightShoot2.set(0);

  }

  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}

