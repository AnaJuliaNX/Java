// Classe de teste usando polimorfismo por interface
// Vantagens do polimorfismo por interface:
// 1. A capacidade de adicionar novos tipos de animais sem alterar o método emitirSom.
// 2. Alterar animais existentes sem alterar o método emitirSom.
// 3. A capacidade de adicionar novas classes de animais sem alterar o método emitirSom.

import java.util.ArrayList;
import java.util.List;

public class TesteSonsInterface {
    public static void reproduzirSons(List<FazSom> emissores) {
        for (FazSom emissor : emissores) {
            // Chama em tempo de execução o método emitirSom() 
            emissor.emitirSom();
        }
    }

    public static void main(String[] args) {
        List<FazSom> emissores = new ArrayList<>();
        emissores.add(new Alarme());
        emissores.add(new Robo());
        emissores.add(new Telefone());

        // Chama métodos das classes Robo, Alarme e Cachorro
        reproduzirSons(emissores); 
    }
}