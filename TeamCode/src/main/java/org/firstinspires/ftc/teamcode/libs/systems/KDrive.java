package org.firstinspires.ftc.teamcode.libs.systems;

import com.arcrobotics.ftclib.drivebase.HDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class KDrive {

    public Motor left,right,slide;
    public HDrive drive;

    public HardwareMap hardwareMap;

    public KDrive(HardwareMap hMap){
        hardwareMap = hMap;
    }

    public void init(){

        left = new Motor(hardwareMap, "left");
        right = new Motor(hardwareMap, "right");
        slide = new Motor(hardwareMap, "slide");

        left.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        slide.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        drive = new HDrive(left,right,slide);
    }

    public void move(double x,double y,double r){
        drive.driveRobotCentric(x,y,r - y * 0.25);
    }

    public void iaSiCrapa(){
        drive.driveRobotCentric(0,0,0);
    }
}
