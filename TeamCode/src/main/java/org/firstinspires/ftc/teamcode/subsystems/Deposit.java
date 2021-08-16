package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Deposit implements Subsystem{

    public enum DepositMode{

        COLLECT,
        DEPLOY
    }

    public static double COLLECT_POSITION = 0.3; //trebuie modificat
    public static double DEPLOY_POSITION = 0.8; //trebuie modificat

    public DepositMode depositMode;
    private Servo rightServo, leftServo;
    private Robot robot;
    private boolean isAutonomous;

    Deposit(HardwareMap hardwareMap, Robot robot, boolean isAutonomous){
        this.robot = robot;
        this.isAutonomous = isAutonomous;

        rightServo = hardwareMap.get(Servo.class,"rightServo");
        leftServo = hardwareMap.get(Servo.class,"leftServo");

        depositMode = DepositMode.DEPLOY;

    }

    @Override
    public void update() {
        switch (depositMode){

            case COLLECT:
                rightServo.setPosition(COLLECT_POSITION);
                leftServo.setPosition(COLLECT_POSITION);
                break;
            case DEPLOY:
                rightServo.setPosition(DEPLOY_POSITION);
                leftServo.setPosition(DEPLOY_POSITION);
                break;
        }
    }
}
