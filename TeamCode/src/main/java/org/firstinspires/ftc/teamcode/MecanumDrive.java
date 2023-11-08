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


        // Configures the speed of the robot based on the bumpers pressed
        if (gamepad1.right_bumper) {
            speed += 0.05;
        }
        if (gamepad1.left_bumper) {
            speed -= 0.05;
        }
        speed = Math.max(0, Math.min(speed, 1));

        if (gamepad1.a) {
            hardware.swivelArm.setPower(1);
        }

        if (gamepad1.b) {
            hardware.swivelArm.setPower(0);
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
        }

        if (gamepad1.y) {
            hardware.bucketClaw.setPosition(0);
        }

    }

}
