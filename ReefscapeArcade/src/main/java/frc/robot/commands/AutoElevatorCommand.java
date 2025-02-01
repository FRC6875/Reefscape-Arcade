// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class AutoElevatorCommand extends Command {
  /** Creates a new ElevatorPositionCommand. */
  ElevatorSubsystem m_elevatorSubsystem;
  double m_dist;
  String m_direction;

  public AutoElevatorCommand(ElevatorSubsystem elevatorSubsystem, double dist, String direction) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_elevatorSubsystem = elevatorSubsystem;
    m_dist = dist;
    m_direction = direction;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_elevatorSubsystem.resetEncoder();
  //reset encoders
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_elevatorSubsystem.setSpeed(0.5, m_direction);
    //run motor in specified direction
  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_elevatorSubsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    //check if encoder value is greater than or equal to distance inputted
    
    //without tolerance
    if(m_elevatorSubsystem.getEncoderValue()>=m_dist)return true;
    else return false;

    // 2 is the tolerance
   // if(Math.abs(m_elevatorSubsystem.getEncoderValue() - m_dist) < 0.5) return true;
   // else return false;
 
  }
}