package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.libs.helixcv.HelixCV;
import org.firstinspires.ftc.teamcode.libs.helixcv.OpenCVDetector;

@Autonomous
public class AutoOpenCV extends LinearOpMode {

    public HelixCV helixCV = null;

    public void initHW(){
        helixCV = new HelixCV(telemetry, hardwareMap);
        helixCV.start();
    }

    @Override
    public void runOpMode() throws InterruptedException {

        try{

            initHW();

            waitForStart();

            if(helixCV.detector.beacon == OpenCVDetector.Beacon.ONE){
                telemetry.addData("Pos","1");
            }
            else if(helixCV.detector.beacon == OpenCVDetector.Beacon.TWO){
                telemetry.addData("Pos","2");
            }
            else if(helixCV.detector.beacon == OpenCVDetector.Beacon.THREE){
                telemetry.addData("Pos","3");
            }

            telemetry.update();

            while(!isStopRequested() && helixCV.isRunning());

            throw new InterruptedException();
        }
        catch (InterruptedException e){
            helixCV.kill();
        }

    }
}
