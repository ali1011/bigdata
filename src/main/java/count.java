public class count {

    public static void main(String[]args){
        int i = 0;
        int j = 0;
        i = 10;
        j = 11;
        int ij = multi(i,j);
        System.out.println("test");
        i = 0;
        j = 1;
        System.out.println("ok");
        System.out.println("a*b= "+ ij);
        System.out.println("test");
    }

    private static int multi(int a, int b) {
        return a * b;
    }

}
