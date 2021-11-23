package z1_lab5.oneThread;

public class Pixel {
    private int x;
    private int y;
    private int iter;//Future<Integer> iter;


    public Pixel(int x, int y, int iter){//Future<Integer> iter){
        this.x =x;
        this.y =y;
        this.iter =iter;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getIter() { return iter; }

}
