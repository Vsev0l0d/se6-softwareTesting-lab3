package utils;

import nu.pattern.OpenCV;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;

public class AverageColorChecker {
    public static boolean isAverageColorDark(File scrFile){
        OpenCV.loadShared();
        Mat img = Imgcodecs.imread(scrFile.getPath());
        Scalar scalar = Core.mean(img);
        return 1 - (0.299 * scalar.val[0] + 0.587 * scalar.val[1] + 0.114 * scalar.val[2]) / 255 > 0.5;
    }
}
