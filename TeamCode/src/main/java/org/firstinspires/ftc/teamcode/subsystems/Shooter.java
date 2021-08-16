package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Shooter implements Subsystem{

    public enum ShooterMode{

        PREPARING,
        IDLE,

    }

    public static double IDLE_POWER = 0;
    public static double targetVelocity = 1600; // trebuie modificat

    public static PIDCoefficients pidCoefficients = new PIDCoefficients(0, 0, 0); // trebuie modificat
    public PIDCoefficients pidGains = new PIDCoefficients(0, 0, 0); //trebuie modificat

    ElapsedTime PIDTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    double lastError = 0;
    double integral = 0;

    private boolean isAutonomous;
    private Robot robot;
    public ShooterMode shooterMode;
    private DcMotorEx shooterMotor;

    public void goBaby(double targetVelocity){
        PIDTimer.reset();

        double currentVelocity = shooterMotor.getVelocity();
        double error = targetVelocity - currentVelocity;

        double deltaError = error - lastError;
        double derivative = deltaError / PIDTimer.time();

        integral += error * PIDTimer.time();


        pidGains.p = error * pidCoefficients.p;
        pidGains.i = integral * pidCoefficients.i;
        pidGains.d = derivative * pidCoefficients.d;

        shooterMotor.setVelocity(pidGains.p + pidGains.i + pidGains.d + targetVelocity);

        lastError = error;

    }

    Shooter(HardwareMap hardwareMap, Robot robot, boolean isAutonomous){

        this.robot = robot;
        this.isAutonomous = isAutonomous;

        shooterMotor = hardwareMap.get(DcMotorEx.class,"shooterMotor");

        shooterMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        shooterMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        shooterMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void update(){

        switch (shooterMode){

            case PREPARING:
                goBaby(targetVelocity);
                break;
            case IDLE:
                shooterMotor.setPower(IDLE_POWER);
        }
    }

}
