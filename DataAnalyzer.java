package z1_lab5.oneThread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.DoubleStream;

public class DataAnalyzer {
    private ArrayList<Double> timesDataSet = new ArrayList<Double>();
    private int threadNo;
    private int taskNo;

    public DataAnalyzer(int threadsNo, int taskNo){

        this.threadNo = threadsNo;
        this.taskNo = taskNo;
    }

    public void addTime(Double time){timesDataSet.add(time);}

    public double getAvg(){return (this.timesDataSet.stream().mapToDouble(aDouble ->
        aDouble).sum() / timesDataSet.size()) ;}

    public String getAvgString(){ return String.format("%.5g", this.timesDataSet.stream().mapToDouble(aDouble ->
            aDouble).sum() / timesDataSet.size());}

    public double getStandardDeviation(){
        double var = 0.0;
        double avg = this.getAvg();
        for( double time : timesDataSet){
            var += Math.pow(time - avg, 2);
        }
        var = var/timesDataSet.size();
        return Math.sqrt(var);
    }
    public String getStandardDeviationString() { return String.format("%.5g", getStandardDeviation());}


    public int getThreadNo(){return threadNo;};
    public int getTaskNo(){return taskNo;}
}
