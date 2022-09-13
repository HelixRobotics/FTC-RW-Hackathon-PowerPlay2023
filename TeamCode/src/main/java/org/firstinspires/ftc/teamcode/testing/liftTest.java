package org.firstinspires.ftc.teamcode.testing;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.libs.systems.Lift;

@TeleOp
public class liftTest extends LinearOpMode {

    Motor lift;

    @Override
    public void runOpMode() throws InterruptedException {
        try {

            lift = new Motor(hardwareMap,"lift");

            lift.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

            lift.setRunMode(Motor.RunMode.RawPower);

            lift.resetEncoder();

            waitForStart();

            while(!isStopRequested()){
                lift.set((gamepad1.right_trigger-gamepad1.left_trigger)*0.8);

                telemetry.addData("Pos",lift.getCurrentPosition());
                telemetry.update();
            }

            throw new InterruptedException();
        }
        catch (InterruptedException e){

        }
    }
}
