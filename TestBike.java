public class TestBike {
    
    public static void main(String[] args) {
        Bicicleta bicicleta;
        bicicleta = new Bicicleta();
        bicicleta.guidao = "Guidão extenso";
        bicicleta.cambio = "Cambio manual";
        bicicleta.trocaMarcha();
        bicicleta.pedalar();

        System.out.println("Esse é o meu guidão: " + bicicleta.guidao);
        System.out.println("Esse é o meu cambio: " + bicicleta.cambio);
    }
}
