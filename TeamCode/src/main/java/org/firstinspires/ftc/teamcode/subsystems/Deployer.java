package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Deployer implements Subsystem{

    public enum DeployerMode{

        DEPLOY,
        IDLE
    }

    public static double DEPLOY_POSITION = 1; // trebuie modificat
    public static double IDLE_POSITION = 0; // trebuie modificat
    public static double CURRENT_POSITION = 0;

    public void deploy(double rings){

        while (rings <= 3) {
            deployer.setPosition(DEPLOY_POSITION);
            CURRENT_POSITION = deployer.getPosition();
            if (CURRENT_POSITION == DEPLOY_POSITION) {
                deployer.setPosition(IDLE_POSITION);
                rings = rings - 1;
            }
        }
    }

    public DeployerMode deployerMode;
    private Servo deployer;
    private Robot robot;
    private boolean isAutonomous;

    Deployer(HardwareMap hardwareMap, Robot robot, boolean isAutonomous){

        this.robot = robot;
        this.isAutonomous = isAutonomous;

        deployer = hardwareMap.get(Servo.class,"deployer");

        deployerMode = DeployerMode.IDLE;
    }

    @Override
    public void update(){
        switch (deployerMode){
            case IDLE:
                deployer.setPosition(IDLE_POSITION);
                break;
            case DEPLOY:
                deploy(3);
                break;
        }
    }
}
