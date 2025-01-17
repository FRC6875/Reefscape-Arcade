package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

//import com.kauailabs.navx.frc.AHRS;

//import edu.wpi.first.wpilibj.SPI;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;



public class DriveSubsystem extends SubsystemBase {



// declare all of our motors. use the constants for the port

  SparkMax frontLeftDriveMotor = new SparkMax(Constants.DriveConstants.kFrontLeftDrivePort, MotorType.kBrushless);
  SparkMax backLeftDriveMotor = new SparkMax(Constants.DriveConstants.kBackLeftDrivePort, MotorType.kBrushless);
  SparkMax frontRightDriveMotor = new SparkMax(Constants.DriveConstants.kFrontRightDrivePort, MotorType.kBrushless);
  SparkMax backRightDriveMotor = new SparkMax(Constants.DriveConstants.kBackRightDrivePort, MotorType.kBrushless);

  SparkMaxConfig configInvertTrue = new SparkMaxConfig();
  SparkMaxConfig configInvertFalse = new SparkMaxConfig();



   //declare Encorders
  RelativeEncoder frontLeftEncoder;
  RelativeEncoder frontRightEncoder;
  RelativeEncoder backLeftEncoder;
  RelativeEncoder backRightEncoder;
  RelativeEncoder leftShootEncoder;
  RelativeEncoder rightShootEncoder;

  // delcare gyro
  //AHRS gyro = new AHRS(SPI.Port.kMXP);

  DifferentialDrive m_robotDrive;


  /** Creates a new ExampleSubsystem. */
  public DriveSubsystem() {

   
    // set motor idle mode to brake
  
    configInvertTrue
      .inverted(true)
      .idleMode(IdleMode.kBrake);
    configInvertTrue.encoder
      .positionConversionFactor(Constants.EncoderConstants.kDriveEncoderConvFact);
      configInvertTrue.closedLoop
      .pid(0,0,1);
      

   // hello

    configInvertFalse
      .inverted(false)
      .idleMode(IdleMode.kBrake);
    configInvertTrue.encoder
      .positionConversionFactor(Constants.EncoderConstants.kDriveEncoderConvFact);
    configInvertTrue.closedLoop
    .pid(0,0,1);
    
frontLeftDriveMotor.configure(ResetMode.kResetSafeParameters, PersistMode.kPersistParameters,configInvertTrue, null, null);
backLeftDriveMotor.configure(configInvertTrue, null, null);
frontRightDriveMotor.configure(configInvertFalse, null, null);
backRightDriveMotor.configure(configInvertFalse, null, null);

   
   
    

    // initialize encoders
    backLeftEncoder = backLeftDriveMotor.getEncoder(Type.kHallSensor, 42);
    backRightEncoder = backRightDriveMotor.getEncoder(Type.kHallSensor, 42);
    frontLeftEncoder = frontLeftDriveMotor.getEncoder(Type.kHallSensor, 42);
    frontRightEncoder = frontRightDriveMotor.getEncoder(Type.kHallSensor, 42);

    
    
  
      // set leader/followers - this connects the front and back motors to drive together
    backLeftDriveMotor.follow(frontLeftDriveMotor);
    backRightDriveMotor.follow(frontRightDriveMotor);
    //.

      // burn settings into memory
    frontLeftDriveMotor.burnFlash();
    frontRightDriveMotor.burnFlash();
    backRightDriveMotor.burnFlash();
    backLeftDriveMotor.burnFlash();
        
    m_robotDrive = new DifferentialDrive(frontLeftDriveMotor,frontRightDriveMotor); //all motors connected

 
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
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
  public void drive(double forward, double rotation){
    m_robotDrive.arcadeDrive(transformY(forward), rotation);
  }

  // we may not actually need to use this:
  //  public void driveDistance(double speed){ // removed sitance argument
  //   // double initialPostion = frontRightEncoder.getPosition();
  //   // targetDistance = targetDistance + initialPostion;
  //   // while (Math.abs(frontRightEncoder.getPosition()) < Math.abs(targetDistance)) { //while the encoder (starting at 0 distance) is less than the target distance
  //   //   m_robotDrive.arcadeDrive(speed*-1,0); // drive forward at given speed
  //   //   SmartDashboard.putNumber("Front right Distance", Math.abs(frontRightEncoder.getPosition()));
  //   // }
  //   // m_robotDrive.arcadeDrive(0,0); 
  //    m_robotDrive.arcadeDrive(speed*-0.8,0);
  // } // end driveDistance

  public double transformY(double forward) {
    return forward*0.8;
    // if (forward < 0) {
    //   return -1*(Math.pow(forward,2)*0.8);
    // }
    // else {
    //   return Math.pow(forward,4);
    // }
  }


  public void resetEncoders(){
     backLeftEncoder.setPosition(0);
     backRightEncoder.setPosition(0);
     frontLeftEncoder.setPosition(0);
     frontRightEncoder.setPosition(0);
  }

  public void stopDriving() {
    m_robotDrive.arcadeDrive(0,0);
  }

  // public void resetGyro(){
  //   gyro.reset();
  // }

  public double getEncoderPosition(){
    return frontLeftEncoder.getPosition();
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