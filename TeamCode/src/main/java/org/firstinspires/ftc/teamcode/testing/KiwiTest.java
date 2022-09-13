package org.firstinspires.ftc.teamcode.testing;

import com.arcrobotics.ftclib.drivebase.HDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.regex.Matcher;

@TeleOp
public class KiwiTest extends LinearOpMode {

    public Motor left,right,slide;
    public HDrive drive;
    public GamepadEx g1;
    public RevIMU imu;

    public void initHW(){

        left = new Motor(hardwareMap, "left");
        right = new Motor(hardwareMap, "right");
        slide = new Motor(hardwareMap, "slide");

        left.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        slide.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        drive = new HDrive(left,right,slide);

        g1 = new GamepadEx(gamepad1);

        imu = new RevIMU(hardwareMap);
        imu.init();
    }

    public void move(double x,double y,double r){
        drive.driveRobotCentric(x,y,r - y * 0.25);
    }

    public void iaSiCrapa(){
        drive.driveRobotCentric(0,0,0);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        try{

            initHW();

            waitForStart();

            while(!isStopRequested()){
                move(
                    -g1.getLeftY(),
                    -g1.getLeftX(),
                    -g1.getRightX()
                );

            }

            throw new InterruptedException();
        }
        catch(InterruptedException e){
            iaSiCrapa();
        }
    }
}
