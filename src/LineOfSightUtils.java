package lineofsightutils;

/**
 * These methods are based on a spherical representation of earth with radius R
 */
public class LineOfSightUtils {

    // earth radius in meters
    private static final int R = 6378100;
    
    
    
    /**
     * Determines whether the two objects have line of sight to each other. 
     */
    public static boolean isWithinLos(double x1, double y1, double z1, double x2, double y2, double z2){
        double altitude1 = getAltitudeAboveEarth(x1, y1, z1);
        double altitude2 = getAltitudeAboveEarth(x2, y2, z2);
        double maxLosDistance = getMaxStraightLineLosDistance(altitude1, altitude2);
        double actualDistance = distance(x1, y1, z1, x2, y2, z2);
        
        return actualDistance < maxLosDistance;
    }
    
    
    /**
     * Determines whether objects of a certain altitude and straight line 
     * distance apart have line of sight
     */
    public static boolean isWithinLos(double straightLineDistanceApart, double altitude1, double altitude2){
        double maxLosDistance = getMaxStraightLineLosDistance(altitude1, altitude2);
        return straightLineDistanceApart < maxLosDistance;
        
    }
    
    /*
     * Returns the maximum straight line distance apart two objects of specified 
     * altitude can be to maintain a line of sight
     */
    public static double getMaxStraightLineLosDistance(double height1, double height2){
        return Math.sqrt(Math.pow(height1,2) + 2*height1*R) + Math.sqrt(Math.pow(height2,2) + 2*height2*R);
    }
    
    /*
     * Returns the maximum curved distance along the spheres surface two objects of specified 
     * altitude can be to maintain a line of sight
     */
    public static double getMaxGreatCircleLosDistance(double height1, double height2){
        return R*Math.acos(R/(R+height1)) + R*Math.acos(R/(R+height2));
    }
    
    public static double getAltitudeAboveEarth(double x1, double y1, double z1){
        return (-R + Math.sqrt(Math.pow(x1 , 2) +  Math.pow(y1 , 2)  +  Math.pow(z1, 2)) );
    }
    
    public static double distance(double x1, double y1, double z1, double x2, double y2, double z2){
        return Math.sqrt( Math.pow(x1 - x2, 2) +  Math.pow(y1 - y2, 2)  +  Math.pow(z1 - z2, 2)  );
    }
}