package org.firstinspires.ftc.teamcode.libs.systems;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lift {

    public DcMotor liftMotor;
    public HardwareMap hardwareMap;

    public int[] pos = {0,-1700,-8100,-13000};


    public Lift(HardwareMap hMap){
        hardwareMap = hMap;
    }

    public void init(){
        liftMotor = hardwareMap.get(DcMotor.class,"lift");

        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void setPosition(int position){
        liftMotor.setTargetPosition(position);
        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftMotor.setPower(1);
    }

}
