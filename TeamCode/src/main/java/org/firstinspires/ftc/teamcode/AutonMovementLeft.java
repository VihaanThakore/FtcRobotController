package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(name = "Autonomous Moves Left")
public class AutonMovementLeft extends LinearOpMode {

    Hardware hardware;

    @Override
    public void runOpMode() throws InterruptedException {

        hardware = new Hardware(this);

        hardware.bucketClaw.setPosition(0.5);

        waitForStart();

        sleep(8000);

        hardware.backLeft.setPower(-0.2);
        hardware.frontRight.setPower(-0.2);
        hardware.frontLeft.setPower(0.2);
        hardware.backRight.setPower(0.2);

        sleep(6000);

        hardware.moveAllMotors(0);
        hardware.intakeOne.setPower(-1);
        //hardware.intakeTwo.setPower(-1);
        sleep(5000);

        hardware.intakeOne.setPower(0);
        //hardware.intakeTwo.setPower(0);

    }
}
