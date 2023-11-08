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

        waitForStart();
        hardware.moveAllMotors(0.5);

        sleep(1000);

        hardware.backLeft.setPower(-0.5);
        hardware.frontRight.setPower(-0.5);
        hardware.frontLeft.setPower(0.5);
        hardware.backRight.setPower(0.5);

        sleep(3000);

        hardware.moveAllMotors(0);


    }
}
