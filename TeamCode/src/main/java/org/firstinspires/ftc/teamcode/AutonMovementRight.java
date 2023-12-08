package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(name = "Autonomous Moves Right")
public class AutonMovementRight extends LinearOpMode {

    Hardware hardware;

    @Override
    public void runOpMode() throws InterruptedException {

        hardware = new Hardware(this);

        hardware.bucketClaw.setPosition(0.5);

        waitForStart();

        hardware.backLeft.setPower(0.5);
        hardware.frontRight.setPower(0.5);
        hardware.frontLeft.setPower(-0.5);
        hardware.backRight.setPower(-0.5);

        sleep(2000);

        hardware.moveAllMotors(0);
        hardware.intake.setPower(-1);

        sleep(5000);

        hardware.intake.setPower(0);
    }
}
