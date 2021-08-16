package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Robot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOP", group = "TeleOP")
public class TeleOp extends OpMode {

    public enum DriveMode{

        FAST,
        MEDIUM,
        SLOW
    }


    Robot robot;
    DriveMode driveMode;
    SampleMecanumDrive drive;


    @Override
    public void init(){

        robot = new Robot(this, false);
        drive = new SampleMecanumDrive(hardwareMap);

        driveMode = DriveMode.FAST;

    }

    @Override
    public void init_loop(){

    }

    @Override
    public void start(){

        robot.start();

    }

    @Override
    public void loop(){

        switch (driveMode){
            case FAST:
                drive.JoyStickDrive(gamepad1, 0.9);
                break;
            case MEDIUM:
                drive.JoyStickDrive(gamepad1, 0.6);
                break;
            case SLOW:
                drive.JoyStickDrive(gamepad1, 0.3);
                break;
        }


    }


    @Override
    public void stop(){

        robot.stop();

    }


}
