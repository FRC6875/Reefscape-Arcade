// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
//testing push to github
public final class Constants {
  public static class OperatorConstants {
  }
    public static class DriveConstants {
      public static final int kFrontLeftDrivePort = 1;
      public static final int kBackLeftDrivePort = 3;
      public static final int kFrontRightDrivePort = 2;
      public static final int kBackRightDrivePort = 4;
    }//***the numbers needs to be changed later

    public static class ControllerConstants {
      public static final int kXboxController1Port = 0;
      public static final int kXboxController2Port = 1;
    }
    
  
    public static class EncoderConstants{
      public static final int kDriveEncoderConvFact = (int) (Math.PI*6/8.45);
    }
  }

