package org.firstinspires.ftc.teamcode.stable;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.libs.systems.KDrive;
import org.firstinspires.ftc.teamcode.libs.systems.Lift;

@TeleOp
public class ControlTeleghidat extends LinearOpMode {

    KDrive drive;
    Lift lift;

    boolean stateA1=false,stateY1=false,
            stateB1=false;

    int liftPos=0;

    public void initHW(){
        drive = new KDrive(hardwareMap);
        drive.init();

        lift = new Lift(hardwareMap);
        lift.init();
    }

    @Override
    public void runOpMode() throws InterruptedException {

        try{
            initHW();

            waitForStart();

            while(!isStopRequested()){

                boolean a1 = gamepad1.a;
                boolean y1 = gamepad1.y;
                boolean b1 = gamepad1.b;

                if(!stateA1 && a1){
                    liftPos--;
                    liftPos = Math.max(liftPos,0);
                }
                if(!stateY1 && y1){
                    liftPos++;
                    liftPos = Math.min(liftPos,2);
                }
                if(!stateB1 && b1){
                    liftPos = 0;
                }

                lift.setPosition(lift.pos[liftPos]);

                stateY1 = y1;
                stateA1 = a1;

                telemetry.addData("A1",stateA1);
                telemetry.addData("Y1",stateY1);
                telemetry.addData("Pos",liftPos);
                telemetry.update();

                drive.move(gamepad1.left_stick_y,
                            -gamepad1.left_stick_x,
                            -gamepad1.right_stick_x);
            }

            throw new InterruptedException();
        }
        catch(InterruptedException e){

        }

    }
}
