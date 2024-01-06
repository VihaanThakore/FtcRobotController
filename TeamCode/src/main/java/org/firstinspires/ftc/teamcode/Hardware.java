package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import  com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware {
    public OpMode opMode;
    public final DcMotor frontLeft;
    public final DcMotor frontRight;
    public final DcMotor backLeft;
    public final DcMotor backRight;
    public final DcMotor swivelArm;
    //public final DcMotor linearExtension;
    public final Servo bucketClaw;
    public final Servo bucketClaw1;
    public final DcMotor intakeOne;
    //public final DcMotor secondLinearExtension;

    //public final DcMotor intakeTwo;

    public Hardware(OpMode opMode) {
        HardwareMap hwMap = opMode.hardwareMap;
        this.opMode = opMode;

        // Get hardware from Hardware Map
        this.frontLeft = hwMap.get(DcMotor.class, "frontLeftMotor");
        this.frontRight = hwMap.get(DcMotor.class, "frontRightMotor");
        this.backLeft = hwMap.get(DcMotor.class, "backLeftMotor");
        this.backRight = hwMap.get(DcMotor.class, "backRightMotor");
        this.swivelArm = hwMap.get(DcMotor.class, "swivelMotor");
        //this.linearExtension = hwMap.get(DcMotor.class, "linearExtensionMotor");
        //this.secondLinearExtension = hwMap.get(DcMotor.class, "secondLinearExtensionMotor");

        this.bucketClaw = hwMap.get(Servo.class, "bucketClaw");
        this.bucketClaw1 = hwMap.get(Servo.class, "bucketClaw1");

        this.intakeOne = hwMap.get(DcMotor.class, "intakeOneMotor");
        //this.intakeTwo = hwMap.get(DcMotor.class, "intakeTwoMotor");

        // Reverse right motors to ensure robot moves forward
        this.frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        this.backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        //this.linearExtension.setDirection(DcMotorSimple.Direction.REVERSE);

        bucketClaw1.setDirection(Servo.Direction.REVERSE);

        // Set braking behavior on motors
        this.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Encoders babyyy
        this.frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);



    }
    // Function to easily move robot
    public void moveAllMotors(double motorSpeed) {
        this.frontLeft.setPower(motorSpeed);
        this.frontRight.setPower(motorSpeed);
        this.backLeft.setPower(motorSpeed);
        this.backRight.setPower(motorSpeed);
    }

    // The code necessary to move the wheels of the robot in a holonomic drive
    public void moveDriveTrain(Double lY, Double lX, Double rX) {
        double vertical;
        double horizontal;
        double pivot;
        vertical = -lY;
        horizontal = lX;
        pivot = rX;

        this.frontRight.setPower(pivot + (-vertical + horizontal));
        this.backRight.setPower(pivot + (-vertical - horizontal));
        this.frontLeft.setPower(-pivot + (-vertical - horizontal));
        this.backLeft.setPower(-pivot + (-vertical + horizontal));

    }

}

