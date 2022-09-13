package org.firstinspires.ftc.teamcode.testing;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.libs.systems.Lift;

@TeleOp
public class LiftControl extends LinearOpMode {

    public Lift lift;
    public GamepadEx g1;

    @Override
    public void runOpMode() throws InterruptedException {
        try {

            lift = new Lift(hardwareMap);
            lift.init();

            g1 = new GamepadEx(gamepad1);

            waitForStart();

            while(!isStopRequested()){

                if(gamepad1.y){
                    lift.liftMotor.setTargetPosition(lift.pos[1]);
                    lift.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }
                if(gamepad1.a){
                    lift.liftMotor.setTargetPosition(lift.pos[0]);
                    lift.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }

                lift.liftMotor.setPower(1);
            }

            throw new InterruptedException();
        }
        catch (InterruptedException e){


        }
    }
}
