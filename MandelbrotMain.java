package z1_lab5.oneThread;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MandelbrotMain extends JFrame {
    private int threadsNo;
    private int tasksNo;
    private final int MAX_ITER = 570;
    private final double ZOOM = 150;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private BufferedImage I;
    private ExecutorService executorService;
    private ArrayList<Future<ArrayList<Pixel>>> pixelsArray = new ArrayList<>();
    private DataAnalyzer statistics;
    private int iterations = 10;

    public MandelbrotMain(int tasksNo, int threadsNumber, DataAnalyzer dataAnalyzer) throws ExecutionException, InterruptedException {
        super("Mandelbrot Set");
        this.threadsNo = threadsNumber;
        this.tasksNo = tasksNo;
        this.statistics = dataAnalyzer;
        this.executorService = Executors.newFixedThreadPool(this.threadsNo);
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setTitle(tasksNo + " " + threadsNumber);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

        int step = (HEIGHT * WIDTH)/ tasksNo;
        //System.out.println("STEP: " + step);
        for(int i = 0; i <10; i++){
            double startTime = System.nanoTime();
            for (int task = 0; task < tasksNo; task++){
                int start = step * task;
                int stop = start + step;
                Future<ArrayList<Pixel>> futurePixels =
                        this.executorService.submit(new MandelbrotTask(tasksNo, task, start, stop, WIDTH, HEIGHT, ZOOM, MAX_ITER));
                pixelsArray.add(futurePixels);
            }
            for(Future<ArrayList<Pixel>> pixelArray: pixelsArray){
                ArrayList<Pixel> pixels = pixelArray.get();
                for(Pixel p : pixels){
                    I.setRGB(p.getX(), p.getY(), p.getIter()| (iterations << 8));
                }
            }
            long endTime = System.nanoTime();
            double time = (double)(endTime - startTime)/1_000_000_000;
            statistics.addTime(time);
        }


    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, this);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ArrayList<DataAnalyzer> dataset = new ArrayList<>();
        int[] threadsNo = {1, 8, 16};
        for(int thread: threadsNo) {
            try {

                DataAnalyzer d0 = new DataAnalyzer(thread, thread);
                new MandelbrotMain(thread, thread, d0).setVisible(false);
                dataset.add(d0);

                DataAnalyzer d1 = new DataAnalyzer(thread, thread * 2);
                new MandelbrotMain(thread * 2, thread, d1).setVisible(false);
                dataset.add(d1);

                DataAnalyzer d2 = new DataAnalyzer(thread, 480000);
                new MandelbrotMain(48000, thread, d2).setVisible(false);
                dataset.add(d2);

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }
        String leftAlignFormat = "| %-6d | %-7d | %-10s | %-16s |%n";

        System.out.format("+---------+--------+-----------+-------------------+%n");
        System.out.format("| Threads | Tasks   |  Average  | Standard deviation %n");
        System.out.format("+---------+--------+-----------+-------------------+%n");
        for (int i = 0; i < dataset.size(); i++) {
            System.out.format(leftAlignFormat, dataset.get(i).getThreadNo(), dataset.get(i).getTaskNo(), dataset.get(i).getAvgString(), dataset.get(i).getStandardDeviationString());
        }
        System.out.format("+---------+-------+-----------+-------------------+%n");
    }
}
