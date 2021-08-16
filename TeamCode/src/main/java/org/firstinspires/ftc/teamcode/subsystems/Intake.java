package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake implements Subsystem{

    public enum IntakeMode {
        IN,
        IDLE,
        OUT,
    }

    public static double INTAKE_IN_SPEED = 1; // trebuie modificat
    public static double INTAKE_IDLE_SPEED = 0;
    public static double INTAKE_OUT_SPEED = -0.8; // trebuie modificat

    public IntakeMode intakeMode;
    private DcMotorEx firstIntake, secondIntake;
    private Robot robot;
    private boolean isAutonomous;

    Intake(HardwareMap hardwareMap, Robot robot, boolean isAutonomous) {
        this.robot = robot;
        this.isAutonomous = isAutonomous;

        firstIntake = hardwareMap.get(DcMotorEx.class, "intakeFirst");
        secondIntake = hardwareMap.get(DcMotorEx.class, "intakeSecond");

        // trebuie setata directia motoarelor

        intakeMode = IntakeMode.IDLE;
    }

    @Override
    public void update() {
        switch (intakeMode) {
            case IN:
                firstIntake.setPower(INTAKE_IN_SPEED);
                secondIntake.setPower(INTAKE_IN_SPEED);
                break;
            case IDLE:
                firstIntake.setPower(INTAKE_IDLE_SPEED);
                secondIntake.setPower(INTAKE_IDLE_SPEED);
                break;
            case OUT:
                firstIntake.setPower(INTAKE_OUT_SPEED);
                secondIntake.setPower(INTAKE_OUT_SPEED);
                break;

        }
    }
}
