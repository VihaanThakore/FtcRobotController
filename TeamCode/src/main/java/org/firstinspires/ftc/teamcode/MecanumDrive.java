package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Mecanum Drive")
public class MecanumDrive extends OpMode {
    Hardware hardware;
    double speed = 0.5;

    @Override
    public void init() {

        // Initializes hardware class in this class
        hardware = new Hardware(this);

    }

    @Override
    public void loop() {

        // Calls moveDriveTrain function from Hardware class
        hardware.moveDriveTrain(
                speed * gamepad1.left_stick_y,
                speed * gamepad1.left_stick_x,
                speed * gamepad1.right_stick_x
        );

        while (gamepad1.right_stick_x == -1) {
            hardware.frontLeft.setPower(1);
            hardware.backLeft.setPower(1);
            hardware.frontRight.setPower(-1);
            hardware.backRight.setPower(-1);
        } while (gamepad1.right_stick_x == 1) {
            hardware.frontLeft.setPower(-1);
            hardware.backLeft.setPower(-1);
            hardware.frontRight.setPower(1);
            hardware.backRight.setPower(1);
        }


        // Configures the speed of the robot based on the bumpers pressed
        if (gamepad1.right_bumper) {
            speed += 0.05;
            telemetry.addLine("Speed is " + speed);
        }
        if (gamepad1.left_bumper) {
            speed -= 0.05;
            telemetry.addLine("Speed is " + speed);
        }
        speed = Math.max(0, Math.min(speed, 1));

        if (gamepad1.a) {
            hardware.swivelArm.setPower(0.1);
        }

        if (gamepad1.b) {
            hardware.swivelArm.setPower(0);
        }

        if (gamepad1.y) {
            hardware.swivelArm.setPower(-0.4);
        }

        if (gamepad1.dpad_up) {
            hardware.linearExtension.setPower(0.5);
        } else if (gamepad1.dpad_down) {
            hardware.linearExtension.setPower(-0.5);
        } else {
            hardware.linearExtension.setPower(0);
        }

        if (gamepad1.x) {
            hardware.bucketClaw.setPosition(1);
        } else {
            hardware.bucketClaw.setPosition(0);
        }

        if (gamepad1.dpad_right) {
            hardware.intake.setPower(1);
        } else if (gamepad1.dpad_left) {
            hardware.intake.setPower(-1);
        } else {
            hardware.intake.setPower(0);
        }

    }

}