package z1_lab5.oneThread;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class MandelbrotTask implements Callable {
    private int taskNo;
    private int taskID;
    private int start;
    private int stop;
    private final int WIDTH;
    private final int HEIGHT;
    private final double ZOOM;
    private final int MAX_ITER;

    public MandelbrotTask(int taskNo, int taskID, int start, int stop, int width, int height, double zoom, int max_iter) {
        this.taskNo = taskNo;
        this.taskID = taskID;
        this.start = start;
        this.stop = stop;
        WIDTH = width;
        HEIGHT = height;
        ZOOM = zoom;
        MAX_ITER = max_iter;
    }

    @Override
    public ArrayList<Pixel> call() throws Exception{
        ArrayList<Pixel> pixels = new ArrayList<>();
        //System.out.println(taskID + " " + start + " "  +stop);
        for(int y = start; y < stop && y < HEIGHT; y++){
            for(int x = 0; x < WIDTH; x++){
                double zy;
                double zx = zy = 0;
                double cX = (x - 400) / ZOOM;
                double cY = (y - 300) / ZOOM;
                int iter = MAX_ITER;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    double tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                pixels.add(new Pixel(x, y, iter));
            }
        }
        return pixels;
    }


    /*@Override
    public ArrayList<Pixel> call() throws  Exception{
        System.out.println(taskID);
        ArrayList<Pixel> pixels = new ArrayList<>();
        for(int i = taskID; i<HEIGHT*WIDTH; i+=taskNo){
            int x = i%HEIGHT;
            int y = i/WIDTH;
            double zy;
            double zx = zy = 0;
            double cX = (x - 400) / ZOOM;
            double cY = (y - 300) / ZOOM;
            int iter = MAX_ITER;
            while (zx * zx + zy * zy < 4 && iter > 0) {
                double tmp = zx * zx - zy * zy + cX;
                zy = 2.0 * zx * zy + cY;
                zx = tmp;
                iter--;
            }
            pixels.add(new Pixel(x, y, iter));
        }
        return pixels;
    }*/

}
