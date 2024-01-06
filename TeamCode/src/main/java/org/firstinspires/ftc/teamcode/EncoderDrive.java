package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class EncoderDrive extends OpMode {

    Hardware hwMap;
    double speed = 0.5;
    double ticks = 288;

    double newTarget;

    int executeCommand = 0;

    @Override
    public void init() {

        hwMap = new Hardware(this);
        hwMap.swivelArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hwMap.swivelArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        telemetry.addLine("Speed is" + speed);

    }

    @Override
    public void loop() {

        // Movement code
        hwMap.moveDriveTrain(
                speed * gamepad1.left_stick_y,
                speed * gamepad1.left_stick_x,
                speed * gamepad1.right_stick_x
        );

        // Speed adjustment
        if (gamepad1.right_bumper) {
            speed = speed + 0.05;
            telemetry.addLine("Speed is" + speed);
        }

        if (gamepad1.left_bumper) {
            speed -= 0.05;
            telemetry.addLine("Speed is" + speed);
        }

        // Ensures speed does not go below 0 or above 1
        speed = Math.max(0, Math.min(speed, 1));

        // Moves swiveling arm 0.5 rotations
        if (gamepad1.a) {
            encoder(0.5);
        }
        // Moves swiveling arm back to the bottom of rotation
        if (gamepad1.b) {
            encoder(0);
        }

        // Moves servo to deposit pixel as long as x is pressed
        if (gamepad1.x) {
            hwMap.bucketClaw.setPosition(1);
            hwMap.bucketClaw1.setPosition(1);
        } else {
            hwMap.bucketClaw.setPosition(0);
            hwMap.bucketClaw1.setPosition(0);
        }

        if (gamepad1.y) {
            // Alternates directions of intakes
            // Eats pixels when pressed once
            // Spits pixels out when pressed twice
            // Stops moving when pressed 3 times
            if (executeCommand % 3 == 0) {
                hwMap.intakeOne.setPower(0.5);
                //hwMap.intakeTwo.setPower(0.5);
                executeCommand = 1;
                stop();
            } else if (executeCommand % 3 == 1) {
                hwMap.intakeOne.setPower(-0.5);
                //hwMap.intakeTwo.setPower(-0.5);
                executeCommand = 2;
            } else if (executeCommand % 3 == 2) {
                hwMap.intakeOne.setPower(0);
                //hwMap.intakeOne.setPower(0);
                executeCommand = 0;
            }
            telemetry.addLine("Execute Command is " + executeCommand);
        }
    }

    // Sets swivelArm to work based on encoders
    // Double revolutionPosition - parameter uses number of revolutions as measurement
    public void encoder(double revolutionPosition) {
        hwMap.swivelArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        newTarget = revolutionPosition * ticks;
        hwMap.swivelArm.setTargetPosition((int)newTarget);
        hwMap.swivelArm.setPower(0.05);
        hwMap.swivelArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
