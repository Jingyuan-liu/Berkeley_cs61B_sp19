public class main {
    public static void main(String[] args){
        Bird bird = new Falcon();
        Falcon falcon = (Falcon) bird;
        System.out.print(bird.gulgate(bird));
    }
}
