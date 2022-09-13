package org.firstinspires.ftc.teamcode.libs.helixcv;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class OpenCVDetector extends OpenCvPipeline {
    Telemetry telemetry;
    Mat mat = new Mat();
    public enum Beacon {
        ONE,
        TWO,
        THREE,
        NULL
    }
    public Beacon beacon;

    static final Rect ROI = new Rect(
            new Point(60, 80),
            new Point(90, 140));

    public OpenCVDetector(Telemetry t) { telemetry = t; }

    @Override
    public Mat processFrame(Mat input) {
        /*Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);

        Core.inRange(mat, lowHSV, highHSV, mat);

        Mat left = mat.submat(LEFT_ROI);
        Mat mid = mat.submat(MID_ROI);
        Mat right = mat.submat(RIGHT_ROI);

        double leftValue = Core.sumElems(left).val[0] / LEFT_ROI.area() / 255;
        double midValue = Core.sumElems(mid).val[0] / MID_ROI.area() / 255;
        double rightValue = Core.sumElems(right).val[0] / RIGHT_ROI.area() / 255;

        left.release();
        mid.release();
        right.release();
        mat.release();

        telemetry.addData("Left raw value", (int) Core.sumElems(left).val[0]);
        telemetry.addData("Mid raw value", (int) Core.sumElems(mid).val[0]);
        telemetry.addData("Right raw value", (int) Core.sumElems(right).val[0]);
        telemetry.addData("Left percentage", Math.round(leftValue * 100) + "%");
        telemetry.addData("Mid percentage", Math.round(midValue * 100) + "%");
        telemetry.addData("Right percentage", Math.round(rightValue * 100) + "%");

        boolean markLeft = leftValue > PERCENT_COLOR_THRESHOLD;
        boolean markMid = midValue > PERCENT_COLOR_THRESHOLD;
        boolean markRight = rightValue > PERCENT_COLOR_THRESHOLD;

        location = Location.NULL;

        if (markMid) {
            location = Location.MID;
            telemetry.addData("Team Marker Location", "MID");
        }
        else if (markRight) {
            location = Location.RIGHT;
            telemetry.addData("Team Marker Location", "RIGHT");
        }
        else if(markLeft){
            location = Location.LEFT;
            telemetry.addData("Team Marker Location", "LEFT");
        }
        telemetry.update();

        Scalar colorNone = new Scalar(255, 0, 0);
        Scalar colorMark = new Scalar(0, 255, 0);

        Imgproc.rectangle(input, LEFT_ROI, markLeft? colorMark:colorNone);
        Imgproc.rectangle(input, MID_ROI, markMid? colorMark:colorNone);
        Imgproc.rectangle(input, RIGHT_ROI, markRight? colorMark:colorNone);*/
        beacon = Beacon.NULL;

        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);

        Mat green = mat.submat(ROI);
        Mat orange = mat.submat(ROI);
        Mat white = mat.submat(ROI);

        Scalar lowHSV1 = new Scalar(55, 50, 70);
        Scalar highHSV1 = new Scalar(100, 255, 255);

        Scalar lowHSV2 = new Scalar(10, 50, 70);
        Scalar highHSV2 = new Scalar(28, 255, 255);

        Scalar lowHSV3 = new Scalar(0, 0, 150);
        Scalar highHSV3 = new Scalar(255, 50, 255);

        Core.inRange(green, lowHSV1, highHSV1, green);
        Core.inRange(orange, lowHSV2, highHSV2, orange);
        Core.inRange(white, lowHSV3, highHSV3, white);

        double greenPer = Core.sumElems(green).val[0] / ROI.area() / 255;
        double orangePer = Core.sumElems(orange).val[0] / ROI.area() / 255;
        double whitePer = Core.sumElems(white).val[0] / ROI.area() / 255;

        boolean isGreen = false;
        boolean isOrange = false;
        boolean isWhite = false;

        if(greenPer > 0.5) isGreen = true;
        if(orangePer > 0.5) isOrange = true;
        if(whitePer > 0.5) isWhite = true;

        Scalar colorMark = new Scalar(0, 0, 255);
        Scalar greenCol = new Scalar(0, 255, 0);
        Scalar redCol = new Scalar(255, 0, 0);
        Scalar whiteCol = new Scalar(255, 255, 255);
        Imgproc.rectangle(input, ROI, isGreen?greenCol:isOrange?redCol:isWhite?whiteCol:colorMark);

        if(isGreen) beacon = Beacon.ONE;
        if(isOrange) beacon = Beacon.TWO;
        if(isWhite) beacon = Beacon.THREE;

        telemetry.addData("Is green",isGreen);
        telemetry.addData("Is orange",isOrange);
        telemetry.addData("Is white",isWhite);

        telemetry.addData("Green",greenPer);
        telemetry.addData("Orange",orangePer);
        telemetry.addData("White",whitePer);
        telemetry.update();

        mat.release();
        green.release();

        return input;
    }

    public Beacon getPos() {
        return beacon;
    }
}
